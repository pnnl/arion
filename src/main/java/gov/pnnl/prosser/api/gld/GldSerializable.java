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
package gov.pnnl.prosser.api.gld;

import gov.pnnl.prosser.api.sql.SqlFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * GridLabD specific serialization interface
 *
 * @author nord229
 */
public interface GldSerializable {

    /**
     * Write the string representation of this object to a StringBuilder
     *
     * @param sb
     *            StringBuilder to use when writing
     */
    public void writeGldString(StringBuilder sb);

    /**
     * Write the referenced files in this object to the specified path
     * @param path the output path
     */
    public default void writeExternalFiles(Path path) throws IOException {
    }

    /**
     * Write the referenced files in this object to the specified path
     * @param path the output path
     */
    public default void writeSharedFiles(Path path) throws IOException {
    }

    public default void createSqlObjects(SqlFile file) {
    }

    /**
     * Write the string property value with the following format
     * \t[propName] [propValue];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final String propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    /**
     * Write the long property value with the following format
     * \t[propName] [propValue];\n
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Long propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    /**
     * Write the integer property value with the following format
     * \t[propName] [propValue];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Integer propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    /**
     * Write the boolean property value with the following format
     * \t[propName] [propValue];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue) {
        writeProperty(sb, propName, propValue, null);
    }

    /**
     * Write the name of the gld object to be referenced with the following format
     * \t[propName] [propValue.getName()];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final AbstractGldObject propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.getName(), null);
    }

    /**
     * Write the enum property value with the following format
     * \t[propName] [propValue.name()];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Enum<?> propValue) {
        if (propValue == null) {
            return;
        }
        writeProperty(sb, propName, propValue.name(), null);
    }

    /**
     * Write the string property value and units with the following format
     * \t[propName] [propValue] [propUnits];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     * @param propUnits the prop units
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final String propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    /**
     * Write the long property value and units with the following format
     * \t[propName] [propValue] [propUnits];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     * @param propUnits the prop units
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Long propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    /**
     * Write the integer property value and units with the following format
     * \t[propName] [propValue] [propUnits];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     * @param propUnits the prop units
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Integer propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    /**
     * Write the boolean property value and units with the following format
     * \t[propName] [propValue] [propUnits];\n
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     * @param propValue the prop value
     * @param propUnits the prop units
     */
    public default void writeProperty(final StringBuilder sb, final String propName, final Boolean propValue, final String propUnits) {
        if (propValue == null) {
            return;
        }
        writePropName(sb, propName);

        sb.append(propValue);

        writePropUnitsAndTrailer(sb, propUnits);
    }

    /**
     * Write the property name with the following format
     * \t[propname]' '
     *
     * @param sb the string builder to use when writing
     * @param propName the prop name
     */
    public default void writePropName(final StringBuilder sb, final String propName) {
        sb.append('\t').append(propName).append(' ');
    }

    /**
     * Write the property units and trailer with the following format
     * ' '[propUnits];\n
     *
     * @param sb the string builder to use when writing
     * @param propUnits the prop units
     */
    public default void writePropUnitsAndTrailer(final StringBuilder sb, final String propUnits) {
        if (propUnits != null) {
            sb.append(' ').append(propUnits);
        }
        sb.append(";\n");
    }

}
