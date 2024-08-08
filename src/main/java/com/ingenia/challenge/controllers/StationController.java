package com.ingenia.challenge.controllers;

import com.ingenia.challenge.services.StationService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/stations")
public class StationController {

	private final StationService stationService;
	
	public StationController(StationService stationService) {
		this.stationService = stationService;
	}

	@PutMapping("/{stationId}")
	public ResponseEntity<Map<String, String>> addStation(@PathVariable Long stationId, @RequestBody Map<String, String> body) {
		stationService.addStation(stationId, body.get("name"));
		return ResponseEntity.ok(Map.of("status", "ok"));
	}

}
