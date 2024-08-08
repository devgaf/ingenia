package com.ingenia.challenge.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Station {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	public Station(String name) {
		this.name = name;
	}

}
