package com.ingenia.challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

	private Long id;
	private String name;

	public Station(String name) {
		this.name = name;
	}

}
