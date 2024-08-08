package com.ingenia.challenge.services;

import org.springframework.stereotype.Service;

import com.ingenia.challenge.model.*;

import java.util.*;

@Service
public class PathService {
	private final Map<Long, Path> paths = new HashMap<>();
	private final StationService stationService;

	public PathService(StationService stationService) {
		this.stationService = stationService;
	}

	public void addPath(Long id, Long sourceId, Long destinationId, double cost) {
		Station source = stationService.getStation(sourceId);
		Station destination = stationService.getStation(destinationId);
		paths.put(id, new Path(id, cost, source, destination));
	}

	public ShortestPathResult findShortestPath(Long sourceId, Long destinationId) {
		Station source = stationService.getStation(sourceId);
		Station destination = stationService.getStation(destinationId);

		Map<Station, Double> distances = initializeDistances(source);
		Map<Station, Station> previousStations = new HashMap<>();
		relaxPaths(source, distances, previousStations);

		List<Long> path = buildPath(destination, previousStations);
		return new ShortestPathResult(path, distances.get(destination));
	}

	private Map<Station, Double> initializeDistances(Station source) {
		Map<Station, Double> distances = new HashMap<>();
		for (Station station : stationService.getStations()) {
			distances.put(station, Double.MAX_VALUE);
		}
		distances.put(source, 0.0);
		return distances;
	}

	private void relaxPaths(Station source, Map<Station, Double> distances, Map<Station, Station> previousStations) {
		PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
		queue.add(source);

		while (!queue.isEmpty()) {
			Station current = queue.poll();
			for (Path path : paths.values()) {
				if (path.getSourceStation().equals(current) || path.getDestinationStation().equals(current)) {
					Station neighbor = path.getSourceStation().equals(current) ? path.getDestinationStation()
							: path.getSourceStation();
					double newDist = distances.get(current) + path.getCost();
					if (newDist < distances.get(neighbor)) {
						distances.put(neighbor, newDist);
						previousStations.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			}
		}
	}

	private List<Long> buildPath(Station destination, Map<Station, Station> previousStations) {
		List<Long> path = new ArrayList<>();
		Station step = destination;
		while (previousStations.get(step) != null) {
			path.add(0, step.getId());
			step = previousStations.get(step);
		}
		return path;
	}
}
