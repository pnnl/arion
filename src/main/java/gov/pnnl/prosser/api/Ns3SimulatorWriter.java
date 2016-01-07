/**
 * 
 */
package gov.pnnl.prosser.api;

import gov.pnnl.prosser.api.ns3.AbstractNs3Object;
import gov.pnnl.prosser.api.ns3.module.Module;
import gov.pnnl.prosser.api.ns3.module.Namespace;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Ns-3 specific experiment writer
 * @author happ546
 *
 */
public class Ns3SimulatorWriter {
	
	/**
	 * @param path the Path for the output file
	 * @param ns3Simulator the Ns3Simulator
	 * @throws IOException on IO errors
	 */
	
	private static volatile Ns3SimulatorWriter instance = new Ns3SimulatorWriter();
    private StringBuilder instanceSb;
	private StringBuilder postSimSb;

	private Ns3SimulatorWriter() {
		instanceSb = new StringBuilder();
		postSimSb = new StringBuilder();
	}
	
	public static Ns3SimulatorWriter getInstance() {
        return instance;
    }

	public void appendPrintInfo(String text) {
		this.instanceSb.append(text);
	}

	public void appendPostSimInfo(String text) {
		this.postSimSb.append(text);
	}
	
	public void writeNs3Simulator(final Path path, final Ns3Simulator ns3Simulator) throws IOException {
		
		// Adds FNCS ns-3 application at end of ns-3 file to output string
		ns3Simulator.setupFncsApplicationHelper();
		
		final List<Module> modules = ns3Simulator.getModules();
		final List<Namespace> namespaces = ns3Simulator.getNamespaces();
        final List<AbstractNs3Object> objects = ns3Simulator.getObjects();
		final StringBuilder sb = new StringBuilder();
		
		// Ns-3 file header stuff; need this
		sb.append("/* -*- Mode:C++; c-file-style:\"gnu\"; indent-tabs-mode:nil; -*- */\n");
		
		// Ns-3 GPL boilerplate stuff; do we need this?
		sb.append("/*\n");
		sb.append(" * This program is free software; you can redistribute it and/or modify\n");
		sb.append(" * it under the terms of the GNU General Public License version 2 as\n");
		sb.append(" * published by the Free Software Foundation;\n");
		sb.append(" *\n");
		sb.append(" * This program is distributed in the hope that it will be useful,\n");
		sb.append(" * but WITHOUT ANY WARRANTY; without even the implied warranty of\n");
		sb.append(" * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n");
		sb.append(" * GNU General Public License for more details.\n");
		sb.append(" *\n");
		sb.append(" * You should have received a copy of the GNU General Public License\n");
		sb.append(" * along with this program; if not, write to the Free Software\n");
		sb.append(" * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA\n");
		sb.append(" */\n\n");
		
        if (modules != null) {
            modules.forEach(m -> m.writeNs3String(sb));
        }

		sb.append("#include <cstdlib>\n");
		sb.append("#include <vector>\n");
		sb.append("#include <map>\n");
		sb.append("#include <iostream>\n");
		sb.append("#include <stdexcept>\n");
        sb.append("\n");

		if (namespaces != null) {
			namespaces.forEach(n -> n.writeNamespace(sb));
		}
        
        sb.append("\nint\n");
        sb.append("main (int argc, char *argv[])\n");
        sb.append("{\n");

		// Print out all objects' info
		sb.append(instanceSb.toString());
		 
		// Output object constructor/declaration strings to ns-3 file
		if (objects != null) {
			objects.forEach(o -> {
				//o.writeNs3Constructors(sb);
			});
		}
		// Output object method/usage strings to ns-3 file
		if (objects != null) {
			objects.forEach(o -> {
				//o.writeNs3Properties(sb);
			});
		}

		sb.append("Simulator::Run();\n");
		// Append post-simulation text
		sb.append(postSimSb);
		sb.append("Simulator::Destroy();\n");
		sb.append("return 0;\n");
        
        sb.append("}\n");
        
        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
	}
	
}
