/**
 * 
 */
package gov.pnnl.prosser.api;

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
	
	public static void writeNs3Simulator(final Path path, final Ns3Simulator ns3Simulator) throws IOException {
		
		ns3Simulator.setup();
		
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
        
        sb.append("\n");
        
		if (namespaces != null) {
			namespaces.forEach(n -> n.writeNamespace(sb));
		}
        
        sb.append("\nint\n");
        sb.append("main (int argc, char *argv[])\n");
        sb.append("{\n");
        
        if (objects != null && objects.size() > 0) {
        	objects.get(0).writeNs3Properties(sb);
        }
        
        sb.append("}\n");
        
        try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write(sb.toString());
        }
	}
	
}
