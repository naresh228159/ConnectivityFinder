package com.connectivity.finder.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.connectivity.finder.service.ConnectivityFinderService;

@WebMvcTest
public class ConnectivityFinderControllerTest {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private ConnectivityFinderService connectivityFinderService;

    @Test
    public void testCheckIfCitiesConnected() throws Exception {
        when(connectivityFinderService.isConnected("newark", "boston")).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.get("/connected")
                .param("origin", "Newark")
                .param("destination", "Boston"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("yes"));
    }

    @Test
    public void testCheckIfCitiesConnected_No() throws Exception {
        when(connectivityFinderService.isConnected("Austin", "Boston")).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.get("/connected")
                .param("origin", "Austin")
                .param("destination", "Boston"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("no"));
    }
    
    @Test
    public void testCheckIfCitiesConnected_InvalidInput() throws Exception {
        when(connectivityFinderService.isConnected("Austin", "Boston")).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.get("/connected")
                .param("origin", "Austin")
                .param("destination", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("no"));
    }

    
}
