package com.connectivity.finder.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.connectivity.finder.struct.ConnectivityGraph;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityFinderHelperTest {

	@InjectMocks
	ConnectivityFinderHelper connectivityFinderHelper;

	@Mock
	ConnectivityGraph connectivityGraph;

	Map<String, LinkedHashSet<String>> connectivityMap = new HashMap<>();

	@Before
	public void setUp() {
		connectivityMap.put("boston",
				Stream.of("new york", "newark").collect(Collectors.toCollection(LinkedHashSet::new)));
		connectivityMap.put("newark",
				Stream.of("philadelphia", "boston", "trenton").collect(Collectors.toCollection(LinkedHashSet::new)));
		connectivityMap.put("trenton",
				Stream.of("albany", "newark").collect(Collectors.toCollection(LinkedHashSet::new)));
		connectivityMap.put("albany", Stream.of("trenton").collect(Collectors.toCollection(LinkedHashSet::new)));
		connectivityMap.put("philadelphia", Stream.of("newark").collect(Collectors.toCollection(LinkedHashSet::new)));
		connectivityMap.put("new york", Stream.of("boston").collect(Collectors.toCollection(LinkedHashSet::new)));
		Mockito.when(connectivityGraph.getConnectivityMap()).thenReturn(connectivityMap);
	}

	@Test
	public void testCheckConnectivity1() {
		assertTrue(connectivityFinderHelper.checkConnectivity("boston", "new york"));
	}

	@Test
	public void testCheckConnectivity2() {
		assertFalse(connectivityFinderHelper.checkConnectivity("boston", "nework"));
	}

	@Test
	public void testCheckConnectivity3() {
		assertTrue(connectivityFinderHelper.checkConnectivity("trenton", "albany"));
	}

	@Test
	public void testCheckConnectivity4() {
		assertFalse(connectivityFinderHelper.checkConnectivity("philadelphia", "nework"));
	}

	@Test
	public void testCheckConnectivity5() {
		assertTrue(connectivityFinderHelper.checkConnectivity("philadelphia", "newark"));
	}

	@Test
	public void testCheckConnectivity6() {
		assertTrue(connectivityFinderHelper.checkConnectivity("newark", "trenton"));
	}

	@Test
	public void testCheckConnectivity7() {
		assertTrue(connectivityFinderHelper.checkConnectivity("albany", "trenton"));
	}

}
