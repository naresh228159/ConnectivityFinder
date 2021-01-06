package com.connectivity.finder.struct;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
/* 
 * Graph data structure for storing connected data based on the resource file.
 */

public class ConnectivityGraph {

	private Map<String, LinkedHashSet<String>> connectivityMap = new HashMap<>();

	public Map<String, LinkedHashSet<String>> getConnectivityMap() {
		return connectivityMap;
	}

	/**
	 * Add Vertexs representing the origin and destination
	 * 
	 * @param origin
	 * @param destination
	 */
	public void addPath(String origin, String destination) {
		LinkedHashSet<String> adjacentConnection = connectivityMap.get(origin);
		if (adjacentConnection == null) {
			adjacentConnection = new LinkedHashSet<>();
			connectivityMap.put(origin, adjacentConnection);
		}
		adjacentConnection.add(destination);
		connectivityMap.put(origin, adjacentConnection);
	}

	
	/**
	 * Add bidirectional paths
	 * 
	 * @param origin
	 * @param destination
	 */
	public void addTwoWayVertex(String origin, String destination) {
		addPath(origin, destination);
		addPath(destination, origin);
	}
}
