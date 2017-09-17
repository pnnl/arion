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
package gov.pnnl.prosser.api.c.obj;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;

/**
 * @author happ546
 * @param <T>
 *
 */
public class Pointer<T extends AbstractNs3Object> extends AbstractNs3Object {

	private T object;

	/**
	 * Create a new Pointer with the given name
	 *
	 * @param name
	 */
	public Pointer(String name) {
		this.setName(name);
	}

	/**
	 * Create a new Pointer with the given name and encapsulate the given object
	 * with it
	 *
	 * @param name
	 * @param obj
	 */
	public Pointer(String name, AbstractNs3Object obj) {
		this.setName(name);
		this.setType(obj);
	}

	/**
	 * Sets the name of this Pointer without printing any output
	 */
	@Override
	public void setName(String name) {
		super.setNameString(name);
	}

	/**
	 * Equivalent to this.pointer = obj;
	 *
	 * @param obj the AbstractNs3Object to set as this pointer
	 */
	public void assign(AbstractNs3Object obj) {
		appendPrintInfo(this.getName() + " = " + obj.getName() + ";\n");
	}

	/**
	 * Creates a Pointer for the given AbstractNs3Object
	 *
	 * @param obj the AbstractNs3Object to wrap in a pointer
	 */
	public void encapsulate(AbstractNs3Object obj) {
		appendPrintInfo("\n  Ptr<" + obj.getClass().getSimpleName() + "> "
				+ this.getName() + "(" + obj.getName() + ");\n");
	}

	/**
	 * @param obj the AbstractNs3Object to set this Pointer type to
	 */
	public void setType(AbstractNs3Object obj) {
		appendPrintInfo("\n  Ptr<" + obj.getClass().getSimpleName() + "> " + this.getName() + ";\n");
	}

	/**
	 * Creates an object of the type of the given object
	 *
	 * @param obj
	 */
	public void createObject(T obj) {
		this.object = obj;
		appendPrintInfo("\n  Ptr<" + obj.getClass().getSimpleName() + "> "
				+ this.getName() + " = CreateObject<" + obj.getClass().getSimpleName() + "> ();\n");
	}

	/**
	 * @return the AbstractNs3Object this Pointer points to
	 */
	public AbstractNs3Object getObject() {
		return this.object;
	}

}
