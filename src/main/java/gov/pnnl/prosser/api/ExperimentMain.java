/**
* Arion
* Copyright © 2016, Battelle Memorial Institute
* All rights reserved.
* 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
*    lawfully obtaining a copy of this software and associated documentation files (hereinafter “the Software”)
*    to redistribute and use the Software in source and binary forms, with or without modification.  Such person
*    or entity may use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
*    and may permit others to do so, subject to the following conditions:
*    •  Redistributions of source code must retain the above copyright notice, this list of conditions and
*       the following disclaimers.
*    •  Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
*       the following disclaimer in the documentation and/or other materials provided with the distribution.
*    •  Other than as used herein, neither the name Battelle Memorial Institute or Battelle may be used in any
*       form whatsoever without the express written consent of Battelle.
* 2. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
*    WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
*    PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BATTELLE OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
*    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
*    OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
*    ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
*    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*                                PACIFIC NORTHWEST NATIONAL LABORATORY
*                                            operated by
*                                              BATTELLE
*                                              for the
*                                  UNITED STATES DEPARTMENT OF ENERGY
*                                   under Contract DE-AC05-76RL01830
*/
package gov.pnnl.prosser.api;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.io.FilenameUtils;

import gov.pnnl.prosser.api.ns3.AbstractNs3SimulatorV2;

/**
 * Main class referenced by the manifest for this Package
 * This class will take a java file and an output directory and compile the java file to the output directory
 * It will then reference that compiled file and output the simulator files to the output directory
 *
 * @author nord229
 */
public abstract class ExperimentMain {

    /**
     * Main method, takes two args, a java file and a output directory
     * This compiles the java file and then references it to write the experiment to the output directory
     *
     * @param args
     *            args[0] is the input java file and args[1] is the output directory
     */
    public static void main(final String... args) {
        try {
        if (args.length != 2) {
            throw new Exception("Requires two arguments");
        }
        final String input = args[0];
        final String output = args[1];

        final Path inPath = Paths.get(input).toRealPath();
        final Path outPath = Paths.get(output).toAbsolutePath();
        if (!Files.exists(outPath)) {
            Files.createDirectories(outPath);
        }

        final Path jarPath = Paths.get(ExperimentMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toRealPath();
        final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new Exception("Please run with a JDK");
        }

        final int result = compiler.run(null, null, null, "-classpath", jarPath.toString(), inPath.toString(), "-d", outPath.toString());
        if (result != 0) {
            throw new Exception("Unable to compile");
        }


        System.out.println("Compiled!");
        final String name = FilenameUtils.getBaseName(inPath.toString());
        // final Path compiledClassPath = inPath.resolveSibling(name + ".class");
        final URLClassLoader child = new URLClassLoader(new URL[] { outPath.toUri().toURL() }, ExperimentMain.class.getClassLoader());

        final Class<?> compiledClass = Class.forName(name, true, child);
        final Class<? extends Experiment> experimentClass = compiledClass.asSubclass(Experiment.class);
        final Experiment experiment = experimentClass.getConstructor().newInstance();
        experiment.experiment();
        experiment.setName(name);
        Path sharedPath = outPath.resolve(experiment.getSharedFolderName());
        Files.createDirectories(sharedPath);

        for (final GldSimulator sim : experiment.getGldSimulators()) {
            final Path simPath = outPath.resolve(sim.getName());
            Files.createDirectories(simPath);
            Files.createDirectories(simPath.resolve(sim.getOutputFolderName()));
            GldSimulatorWriter.writeGldSimulator(simPath, sim, sharedPath);
        }

        if (experiment.getNs3Simulator() != null) {
            final String ns3Name = experiment.getNs3Simulator().getName() + ".cc";
            // Ns3SimulatorWriter.writeNs3Simulator(outPath.resolve(ns3Name), experiment.getNs3Simulator());
            final Path simPath = outPath.resolve(ns3Name);
            Files.createDirectory(simPath);
            Ns3SimulatorWriter.getInstance().writeNs3Simulator(simPath.resolve(ns3Name), experiment.getNs3Simulator());
        }
        for (final ThirdPartySimulator sim : experiment.getThirdPartySimulator()){
        	final String simName = sim.getName();
        	final Path simPath = outPath.resolve(simName);
        	Files.createDirectories(simPath);
        	ThirdPartySimulatorWriter.writeThirdPartySimulator(simPath, sim);
        }
        for(final AbstractNs3SimulatorV2 sim: experiment.getNs3SimulatorV2()) {
            final Path simPath = outPath.resolve(sim.getName());
            Files.createDirectories(simPath);
            sim.writeSimulator(simPath);
        }
        experiment.getExtraExperimentFiles().forEach((f) -> {
            try {
                Files.copy(f, outPath.resolve(f.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException("Unable to copy extra files", e);
            }
        });
        if (experiment.fncsSimulator() != null) {
            FncsSimulatorWriter.writeSimulator(outPath, experiment.fncsSimulator(), experiment.getGldSimulators().size());
        }
        HeatTemplateWriter.writeHeatTemplate(outPath, experiment);
        // Create the tarball
        try (TarArchiveOutputStream tar = new TarArchiveOutputStream(new GzipCompressorOutputStream(new BufferedOutputStream(
                Files.newOutputStream(outPath.resolve(experiment.getName() + ".tar.gz")))))) {
            Files.walkFileTree(outPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    try {
                        final String fileName = outPath.relativize(file).toString();
                        if (!fileName.contains(".class") && !fileName.contains(".tar.gz")) {
                            final TarArchiveEntry entry = new TarArchiveEntry(file.toFile(), fileName);
                            tar.putArchiveEntry(entry);
                            Files.copy(file, tar);
                            tar.closeArchiveEntry();
                        }
                        return FileVisitResult.CONTINUE;
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to write tar file", e);
                    }
                }
            });
        }
        // TODO FNCS simulator writer
        System.out.println("Written!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
