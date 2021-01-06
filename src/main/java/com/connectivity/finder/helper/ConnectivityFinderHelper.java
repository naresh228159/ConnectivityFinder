package com.connectivity.finder.helper;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.connectivity.finder.struct.ConnectivityGraph;

@Component
public class ConnectivityFinderHelper {

	@Autowired
	private ConnectivityGraph connectivityGraph;

	/**
	 * Check connectivity by useing  a breadth-first traversal that starts at an arbitrary root vertex and 
	 * explores all neighboring vertices at the same level before going deeper in the graph.
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public boolean checkConnectivity(String origin, String destination) {

		// Breadth first traversal is been used to navigate.
		LinkedList<String> connectionsToBeSearched = new LinkedList<>();
		connectionsToBeSearched.add(origin);
		// visited connections are added to avoid circular lookup.
		Set<String> visitedConnections = new HashSet<>();

		String connection;

		while (connectionsToBeSearched.size() != 0) {
			// always get the first element in the linked list
			// and perform the breadth search.
			connection = connectionsToBeSearched.getFirst();
			// if the connection is already visited and destination match is not found out skip
			// the traversal
			if (!visitedConnections.contains(connection)) {
				// get the city connections for the current city.
				LinkedHashSet<String> connections = connectivityGraph.getConnectivityMap().get(connection);

				if (!CollectionUtils.isEmpty(connections)) {
					// verify whether destination city connections exists.
					// if exists return true.
					if (connections.contains(destination))
						return true;

					// if not add that connection to the visited connection
					visitedConnections.add(connection);
					// since we follow breadth first, add the connected cities to the to be search
					// list and perform the breadth traversal.
					connectionsToBeSearched.addAll(connections);
				}
			}
			// remove the visited city from the linked list.
			connectionsToBeSearched.removeFirst();
		}

		return false;
	}

}
