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
package gov.pnnl.prosser.api.ns3;

import gov.pnnl.prosser.api.Ns3Simulator;
import gov.pnnl.prosser.api.Ns3SimulatorWriter;

import java.util.Objects;

/**
 * @author happ546
 *
 */
public abstract class AbstractNs3Object {

	private String name;
	private static String printObj;
	private String printInfo;
	private String constructorInfo;

	/**
	 * If printObj doesn't already contains text from other ns-3 objects,
	 * initialize it.
	 * Initializes pointer flag to false
	 */
	public AbstractNs3Object() {
		if (AbstractNs3Object.printObj == null) {
			AbstractNs3Object.printObj = "";
		}
		this.printInfo = "";
		this.constructorInfo = "";
		this.name = null;

		Ns3Simulator.addObject(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of this AbstractNs3Object and adds the constructor
	 * text to this object's information field, printObj,
	 * to later output to a c++ ns-3 file.
	 * Must be called immediately after the constructor.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.appendConstructorInfo("\n  " + this.getClass().getSimpleName() +
				" " + this.name + ";\n");
	}

	/**
	 * @return the name of this Node prefixed with "pointer_"
	 */
	public String getPointerName() {
		return "pointer_" + this.name;
	}

	/**
	 * Sets this AbstractNs3Object's name string without printing anything to the ns-3
	 * output file.
	 * Used by EpsBearer, LteHelper, and other ns-3 objects that supply parameters to
	 * their ns-3 constructors
	 * @param name the name to set
	 */
	public void setNameString(String name) {
		this.name = name;
	}

	/**
	 *
	 * @param text
	 * 			the text to append to this object's printInfo string for the
	 * 			ns-3 output file
	 */

	public void appendPrintInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPrintInfo(text);
		//this.setPrintInfo(this.getPrintInfo() + "  " + text);
	}

	/**
	 *
	 * @param text
	 * 			the String to append to the end (after Simulator::Run() is called)
	 * 			of the ns-3 simulation. Used for file outputting.
     */
	public void appendPostSimInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPostSimInfo(text);
	}

	/**
	 * @param text
	 * 			the text to set this object's printInfo string
	 */
	private void setPrintInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPrintInfo(text);
		//this.printInfo = text;
	}

	/**
	 * @return
	 * 			this object's printInfo string
	 */
	public String getPrintInfo() {
		return this.printInfo;
	}

	/**
	 *
	 * @param text
	 * 			the text to append to this object's constructorInfo string for the
	 * 			ns-3 output file
	 */
	protected void appendConstructorInfo(String text) {
		Ns3SimulatorWriter.getInstance().appendPrintInfo(text);
		//this.setConstructorInfo(this.getConstructorInfo() + " " + text);
	}

	/**
	 *
	 * @param text
	 * 			the text to set this object's constructorInfo string
	 */
	private void setConstructorInfo(String text) {
		this.constructorInfo = text;
	}

	/**
	 *
	 * @return
	 * 			this object's constructorInfo string
	 */
	public String getConstructorInfo() {
		return this.constructorInfo;
	}

	/**
	 * Append constructor information of this object to given StringBuilder
	 * @param sb the StringBuilder
	 */
	public void writeNs3Constructors(StringBuilder sb) {
		sb.append(getConstructorInfo());
	}

	/**
	 * Append characteristics of this object to given StringBuilder
	 * @param sb the stringBuilder
	 */
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(getPrintInfo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, AbstractNs3Object.printObj);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AbstractNs3Object other = (AbstractNs3Object) obj;
		return Objects.equals(this.name, other.name)
				&& Objects.equals(AbstractNs3Object.printObj, AbstractNs3Object.printObj);
	}

	/**
	 * Gets the given AbstractNs3Object as a Ptr to be used in
	 * ns-3 helper methods.  Called automatically in Node and Channel construction.
	 */
	protected void getAsPointer() {
		String objClass = this.getClass().getSimpleName();

		this.appendConstructorInfo("Ptr<" + objClass + "> " + this.getPointerName() +
				" = " + "CreateObject<" + objClass + ">();\n");
	}

}
