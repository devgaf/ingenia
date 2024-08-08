package com.ingenia.challenge.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Path {
	private Long id;
	private Double cost;
	private Station sourceStation;
	private Station destinationStation;

	public Path(Station source, Station destination, double cost) {
		this.sourceStation = source;
		this.destinationStation = destination;
		this.cost = cost;
	}

}
