package com.connectivity.finder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectivity.finder.helper.ConnectivityFinderHelper;

/**
 * Service class calls the helper and determines if the origin and destination are connected
 *
 */
@Service
public class ConnectivityFinderService {

	ConnectivityFinderHelper connectivityFinderHelper;
	
	@Autowired
	public ConnectivityFinderService(ConnectivityFinderHelper connectivityFinderHelper) {
		this.connectivityFinderHelper = connectivityFinderHelper;
	}

	/**
	 * Triggers breadth first search on the connected graph data structure and 
	 * checks if the given origin and destination are connected
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public boolean isConnected(String origin, String destination) {
		return connectivityFinderHelper.checkConnectivity(origin, destination);
	}
}
