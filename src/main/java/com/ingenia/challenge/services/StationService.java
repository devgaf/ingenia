package com.ingenia.challenge.services;

import com.ingenia.challenge.model.Station;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class StationService {

	private final Map<Long, Station> stations = new HashMap<>();

	public void addStation(Long id, String name) {
		stations.put(id, new Station(id, name));
	}

	public Station getStation(Long id) {
		return stations.get(id);
	}
	
	public Collection<Station> getStations() {
        return stations.values();
    }

}
