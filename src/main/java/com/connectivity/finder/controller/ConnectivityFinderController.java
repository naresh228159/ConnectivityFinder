package com.connectivity.finder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connectivity.finder.service.ConnectivityFinderService;

/*
 * Controller class for the Connectivity finder service 
 */

@RestController
public class ConnectivityFinderController {

	private ConnectivityFinderService connectivityFinderService;
	private static final String YES = "yes";
	private static final String NO = "no";

	@Autowired
	public ConnectivityFinderController(ConnectivityFinderService connectivityFinderService) {
		this.connectivityFinderService = connectivityFinderService;
	}

	/**
	 * Resource method for the service that takes in origin and destination
	 * and determines if they are connected based on the connection components
	 * specified in the resource file.
	 * 
	 * Returns "yes" if origin is commected to destination and "no" if otherwise.
	 * Any unexpected input will result in a "no"
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping("/connected")
	public String checkConnectivity(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) {
		if (!StringUtils.hasText(origin)|| !StringUtils.hasText(destination)) {
			return NO;
		}
		boolean isConnected = connectivityFinderService.isConnected(origin.toLowerCase().trim(),
				destination.toLowerCase().trim());
		if (isConnected) {
			return YES;
		}
		return NO;
	}
}
