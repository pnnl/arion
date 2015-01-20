/**
 * 
 */
package gov.pnnl.prosser.api.ns3.obj;

import gov.pnnl.prosser.api.AbstractNs3Object;
import gov.pnnl.prosser.api.AbstractProsserObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject;
import gov.pnnl.prosser.api.gld.obj.ClimateObject.Builder;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author happ546
 *
 */
public class NodeContainer extends AbstractNs3Object {
	private String name, printObj;
	private int numNodes;
	
	public NodeContainer(String name) {
		this.name = name;
		printObj = "NodeContainer " + this.name + ";\n";
	}
	
	public NodeContainer(Builder builder) {
		super(builder);
	}

	/**
	 * Create specified number of nodes in NC
	 * @param numNodes
	 */
	public void create(int numNodes) {
		this.numNodes = numNodes;
		printObj += name + ".Create(" + this.numNodes + ");\n";

	}

	public String getName() {
		return this.name;
	}

	/** 
	 * Append characteristics of this object to given stringbuilder
	 */
	@Override
	public void writeNs3Properties(StringBuilder sb) {
		sb.append(printObj);
	}
	
   public static class Builder extends AbstractNs3Object.AbstractBuilder<NodeContainer, Builder> {

        private Object numNodes;
		private String printObj;

		@Override
        public NodeContainer build() {
            return new NodeContainer(this);
        }

        @Override
        protected Builder self() {
            return this;
        }

		public Builder create(int i) {
			this.numNodes = numNodes;
			this.printObj += name + ".Create(" + this.numNodes + ");\n";
			return this;
		}

    }	
}
