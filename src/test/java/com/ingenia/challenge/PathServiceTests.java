package com.ingenia.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingenia.challenge.model.ShortestPathResult;
import com.ingenia.challenge.services.PathService;
import com.ingenia.challenge.services.StationService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PathServiceTests {

	 private StationService stationService;
	    private PathService pathService;

	    @BeforeEach
	    public void setUp() {
	        stationService = new StationService();
	        pathService = new PathService(stationService);

	        stationService.addStation(10L, "Barcelona");
	        stationService.addStation(11L, "Paris");
	        stationService.addStation(12L, "Berlin");
	        stationService.addStation(13L, "Roma");

	        pathService.addPath(1L, 10L, 11L, 50.0);
	        pathService.addPath(2L, 10L, 12L, 100.0);
	        pathService.addPath(3L, 10L, 13L, 60.0);
	        pathService.addPath(4L, 13L, 12L, 20.0);
	    }

	    @Test
	    void testFindShortestPath() {
	        ShortestPathResult result = pathService.findShortestPath(12L, 11L);

	        assertNotNull(result);
	        assertEquals(130.0, result.getCost());
	        assertEquals(List.of(13L, 10L, 11L), result.getPath());
	    }

	    @Test
	    void testFindShortestPathDirect() {
	        ShortestPathResult result = pathService.findShortestPath(10L, 13L);

	        assertNotNull(result);
	        assertEquals(60.0, result.getCost());
	        assertEquals(List.of(13L), result.getPath());
	    }

	    @Test
	    void testFindShortestPathSameStation() {
	        ShortestPathResult result = pathService.findShortestPath(10L, 10L);

	        assertNotNull(result);
	        assertEquals(0.0, result.getCost());
	        assertTrue(result.getPath().isEmpty());
	    }
}
