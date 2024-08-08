package com.ingenia.challenge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingenia.challenge.services.StationService;

@SpringBootTest
class StationServiceTests {

	 @Test
	     void testAddAndGetStation() {
	        StationService stationService = new StationService();
	        stationService.addStation(10L, "Barcelona");

	        assertNotNull(stationService.getStation(10L));
	        assertEquals("Barcelona", stationService.getStation(10L).getName());
	    }

	    @Test
	     void testGetStations() {
	        StationService stationService = new StationService();
	        stationService.addStation(10L, "Barcelona");
	        stationService.addStation(11L, "Paris");
	        stationService.addStation(12L, "Berlin");
	        stationService.addStation(13L, "Roma");


	        assertEquals(4, stationService.getStations().size());
	    }

}
