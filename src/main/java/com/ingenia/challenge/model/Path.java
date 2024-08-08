package com.ingenia.challenge.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity

public class Path {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false)
	    private Double cost;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "source_id", nullable = false)
	    private Station sourceStation;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "destination_id", nullable = false)
	    private Station destinationStation;

}
