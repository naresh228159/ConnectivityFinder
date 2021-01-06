package com.connectivity.finder.struct;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityGraphTest {
	
	@InjectMocks
	private ConnectivityGraph connectivityGraph;
	
	@Test
	public void testAddPath() {
		connectivityGraph.addPath("Newark", "Denver");
		connectivityGraph.addPath("Denver", "Dublin");
		assertNotNull(connectivityGraph.getConnectivityMap());
		assertFalse(connectivityGraph.getConnectivityMap().isEmpty());
		assertFalse(connectivityGraph.getConnectivityMap().get("Newark").isEmpty());
	}
	
	@Test
	public void testAddTwoWayVertex() {
		connectivityGraph.addTwoWayVertex("Newark", "Denver");
		assertNotNull(connectivityGraph.getConnectivityMap());
		assertFalse(connectivityGraph.getConnectivityMap().isEmpty());
		assertFalse(connectivityGraph.getConnectivityMap().get("Newark").isEmpty());
		assertFalse(connectivityGraph.getConnectivityMap().get("Denver").isEmpty());
	}

}
