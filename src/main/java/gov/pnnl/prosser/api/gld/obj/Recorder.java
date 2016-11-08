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
package gov.pnnl.prosser.api.gld.obj;

import gov.pnnl.prosser.api.GldSimulator;
import gov.pnnl.prosser.api.gld.AbstractGldObject;
import gov.pnnl.prosser.api.sql.SqlFile;
import gov.pnnl.prosser.api.sql.SqlTableDef;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Tape recorder
 *
 * @author nord229
 */
public class Recorder extends AbstractGldObject {

    /**
     * the sampling interval to use (0 means every pass, -1 means only transients)
     */
    private Long interval;

    /**
     * the file to use when recording values
     */
    private String file;

    /**
     * the properties to record from the parent
     */
    private final List<String> properties = new ArrayList<>();

    /**
     * the maximum length limit for the number of samples taken
     */
    private Integer limit;

    private boolean usingSql;

    public Recorder(final GldSimulator simulator) {
        super(simulator);
        simulator.ensureTapeModule();
    }

    /**
     * Get the sampling interval to use (0 means every pass, -1 means only transients)
     *
     * @return the interval
     */
    public Long getInterval() {
        return interval;
    }

    /**
     * Set the sampling interval to use (0 means every pass, -1 means only transients)
     *
     * @param interval
     *            the interval to set
     */
    public void setInterval(final Long interval) {
        this.interval = interval;
    }

    /**
     * Get the file to use when recording values
     *
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * Set the file to use when recording values
     *
     * @param file
     *            the file to set
     */
    public void setFile(final String file) {
        this.file = file;
    }

    /**
     * Get the properties to record from the parent
     *
     * @return the properties
     */
    public List<String> getProperties() {
        return properties;
    }

    /**
     * Set the properties to record from the parent
     *
     * @param properties
     *            the properties to set
     */
    public void properties(final String... properties) {
        this.properties.addAll(Arrays.asList(properties));
    }

    /**
     * Set the properties to record from the parent
     *
     * @param property
     *            the property to set
     */
    public void property(final String property) {
        this.properties.add(property);
    }



    /**
     * Get the maximum length limit for the number of samples taken
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Set the maximum length limit for the number of samples taken
     *
     * @param limit
     *            the limit to set
     */
    public void setLimit(final Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the usingSql
     */
    public boolean isUsingSql() {
        return usingSql;
    }

    /**
     * @param usingSql
     *            the usingSql to set
     */
    public void setUsingSql(boolean usingSql) {
        this.usingSql = usingSql;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.file, this.interval, this.properties, this.limit, this.usingSql);
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
        final Recorder other = (Recorder) obj;
        return Objects.equals(this.file, other.file)
                && Objects.equals(this.interval, other.interval)
                && Objects.equals(this.properties, other.properties)
                && Objects.equals(this.limit, other.limit)
                && Objects.equals(this.usingSql, other.usingSql);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getGldObjectType() {
        return "recorder";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void writeGldProperties(final StringBuilder sb) {
        writeProperty(sb, "interval", this.interval, "s");
        writeProperty(sb, "file", this.file);
        writeProperty(sb, "property", String.join(", ", this.properties));
        writeProperty(sb, "limit", this.limit);
    }

    @Override
    public void createSqlObjects(SqlFile file) {
        if (this.isUsingSql()) {
            final SqlTableDef tableDef = file.sqlTableDef(this.getName());
            this.getProperties().forEach(p -> tableDef.sqlColumnDef(p));
        }
    }

}
