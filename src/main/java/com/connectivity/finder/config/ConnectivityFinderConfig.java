package com.connectivity.finder.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.connectivity.finder.struct.ConnectivityGraph;

/*
 * This class loads the resource data from class path and builds the connectivity graph 
 */

@Configuration
public class ConnectivityFinderConfig {
	private static final Logger LOGGER = LogManager.getLogger(ConnectivityFinderConfig.class);

	@Value("classpath:${connectivity.file.name}")
	Resource resource;
	
	@Value("${connectivity.file.delimeter}")
	String delimiter;
	
	
	/**
	 * Reads the resource file line by line , split with specified delimiter
	 * and build a bidirectional graph
	 * 
	 * @return ConnectivityGraph
	 * @throws Exception
	 */
	@Bean("connectivityGraph")
	public ConnectivityGraph connectionsGraph() throws Exception {
		LOGGER.info("Loading connections graph on start up");
		ConnectivityGraph connectivityGraph = new ConnectivityGraph();
		// Read each line , split by delimiter and build two way vertex for bidirectional graph
		try (Stream<String> fileStream = Files.lines(Paths.get(resource.getURI()))) {
			fileStream.forEach(line -> {
				String[] connectivityArray = line.split(delimiter);
				//Bidirectional graph
				connectivityGraph.addTwoWayVertex(connectivityArray[0].toLowerCase(),
						connectivityArray[1].toLowerCase());
			});
		} catch (IOException e) {
			LOGGER.error("Exception occurred while constructing connectivity graph");
			throw e;
		}
		LOGGER.info("Completed loading connections graph");
		LOGGER.info(connectivityGraph.getConnectivityMap());
		return connectivityGraph;
	}
}
