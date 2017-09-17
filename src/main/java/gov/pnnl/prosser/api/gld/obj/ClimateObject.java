/*******************************************************************************
 * Arion
 * Copyright © 2016, Battelle Memorial Institute
 * All rights reserved.
 * 1. Battelle Memorial Institute (hereinafter Battelle) hereby grants permission to any person or entity
 *    lawfully obtaining a copy of this software and associated documentation files (hereinafter "the Software")
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
 *******************************************************************************/
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * GridLabD Climate Object
 *
 * @author nord229
 */
public class ClimateObject extends AbstractGldObject {

    /**
     * the TMY file name
     */
    private Path tmyFile;

    /**
     * the file reader to use when loading data, can be null
     */
    private CsvReader reader;

    public ClimateObject(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureClimateModule();
    }

    /**
     * Get the TMY file name
     *
     * @return the tmyFile
     */
    public Path getTmyFile() {
        return tmyFile;
    }

    /**
     * Set the TMY file name
     *
     * @param tmyFile
     *            the tmyFile to set
     */
    public void setTmyFile(final Path tmyFile) {
        this.tmyFile = tmyFile;
    }

    /**
     * Get the file reader to use when loading data, can be null
     *
     * @return the reader
     */
    public CsvReader getReader() {
        return reader;
    }

    /**
     * Set the file reader to use when loading data, can be null
     *
     * @param reader
     *            the reader to set
     */
    public void setReader(final CsvReader reader) {
        this.reader = reader;
    }

    /**
     * Sets up a CSV reader for this object, will take the current tmy file for the file in the reader
     *
     * @param name
     *            the CSV reader name
     * @return the csv reader
     */
    public CsvReader addCsvReader(final String name) {
        this.reader = this.simulator.csvReader(name);
        this.reader.setFilename(this.tmyFile.getFileName().toString());
        return this.reader;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.tmyFile);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClimateObject other = (ClimateObject) obj;
        return Objects.equals(this.tmyFile, other.tmyFile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "climate";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "tmyfile", "../" + this.simulator.getExperiment().getSharedFolderName() + "/" + this.tmyFile.getFileName().toString());
        writeProperty(sb, "reader", reader);
    }

    @Override
    public void writeSharedFiles(Path path) throws IOException {
        if (tmyFile != null) {
            final Path tmyFilePath = path.resolve(tmyFile.getFileName());
            if(!Files.exists(tmyFilePath)) {
                Files.copy(tmyFile, tmyFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

}
