/**
 * 
 */
package gov.pnnl.prosser.api;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FilenameUtils;

/**
 * @author happ546
 *
 */
public class Ns3ExperimentMain {
	
	public static void main(final String...args) throws Exception {
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
        final Class<? extends Ns3Simulator> ns3SimulatorClass = compiledClass.asSubclass(Ns3Simulator.class);
        final Ns3Simulator ns3Simulator = ns3SimulatorClass.getConstructor().newInstance();

        Ns3SimulatorWriter.getInstance().writeNs3Simulator(outPath.resolve("ns3.cc"), ns3Simulator);
        System.out.println("Written!");
		
	}

}
