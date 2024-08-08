package com.ingenia.challenge.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.ingenia.challenge.model.ShortestPathResult;
import com.ingenia.challenge.services.PathService;

@RestController
@RequestMapping("/paths")
public class PathController {
	private final PathService pathService;

    public PathController(PathService pathService) {
        this.pathService = pathService;
    }

    @PutMapping("/{pathId}")
    public ResponseEntity<Void> addPath(@PathVariable Long pathId, @RequestBody Map<String, Object> body) {
        pathService.addPath(pathId, ((Number) body.get("source_id")).longValue(), ((Number) body.get("destination_id")).longValue(), ((Number) body.get("cost")).doubleValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{sourceId}/{destinationId}")
    public ResponseEntity<Map<String, Object>> findShortestPath(@PathVariable Long sourceId, @PathVariable Long destinationId) {
    	ShortestPathResult path = pathService.findShortestPath(sourceId, destinationId);
        return ResponseEntity.ok(Map.of("path", path.getPath(), "cost", path.getCost()));
    }
}
