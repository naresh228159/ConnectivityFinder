package com.connectivity.finder.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.connectivity.finder.helper.ConnectivityFinderHelper;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityFinderServiceTest {
	
	@InjectMocks
	ConnectivityFinderService connectivityFinderService;
	
	@Mock
	ConnectivityFinderHelper connectivityFinderHelper;
	
	@Test
	public void testIsConnected_True() {
		Mockito.when(connectivityFinderHelper.checkConnectivity(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.TRUE);
		assertTrue(connectivityFinderService.isConnected("Boston", "Newark"));
	}
	
	@Test
	public void testIsConnected_False() {
		Mockito.when(connectivityFinderHelper.checkConnectivity(Mockito.anyString(),Mockito.anyString())).thenReturn(Boolean.FALSE);
		assertFalse(connectivityFinderService.isConnected("Boston", "Newark"));
	}

}
