package com.ingenia.challenge.model;

import java.util.*;

public class TravelGraph {

	private Map<String, Station> stations = new HashMap<>();
	private List<Path> paths = new ArrayList<>();

	private List<Path> reconstructPath(Map<Station, Station> previousStations, Station origin, Station destination) {
		LinkedList<Path> optimalPath = new LinkedList<>();
		Station current = destination;

		while (!current.equals(origin)) {
			Station previous = previousStations.get(current);
			optimalPath.addFirst(findPath(previous, current));
			current = previous;
		}

		return optimalPath;
	}

	private Path findPath(Station origin, Station destination) {
		return paths.stream()
				.filter(p -> p.getSourceStation().equals(origin) && p.getDestinationStation().equals(destination))
				.findFirst().orElseThrow(() -> new IllegalStateException("Path not found"));
	}

	public void addStation(Station station) {
		stations.put(station.getName(), station);
	}

	public void addPath(Path path) {
		paths.add(path);
		// Add reverse path for bidirectional graph
		paths.add(new Path(path.getDestinationStation(), path.getDestinationStation(), path.getCost()));
	}

	public List<Path> findOptimalPath(String originName, String destinationName) {
		Station origin = stations.get(originName);
		Station destination = stations.get(destinationName);

		if (origin == null || destination == null) {
			throw new IllegalArgumentException("Origin or destination station not found");
		}

		Map<Station, Double> distances = new HashMap<>();
		Map<Station, Station> previousStations = new HashMap<>();
		PriorityQueue<Station> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

		for (Station station : stations.values()) {
			distances.put(station, Double.MAX_VALUE);
		}
		distances.put(origin, 0.0);
		queue.offer(origin);

		while (!queue.isEmpty()) {
			Station current = queue.poll();

			if (current.equals(destination)) {
				break;
			}

			for (Path path : paths) {
				if (path.getSourceStation().equals(current)) {
					Station neighbor = path.getDestinationStation();
					double newDistance = distances.get(current) + path.getCost();

					if (newDistance < distances.get(neighbor)) {
						distances.put(neighbor, newDistance);
						previousStations.put(neighbor, current);
						queue.remove(neighbor);
						queue.offer(neighbor);
					}
				}
			}
		}

		return reconstructPath(previousStations, origin, destination);

	}
}
