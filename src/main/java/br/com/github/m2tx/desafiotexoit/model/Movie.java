package br.com.github.m2tx.desafiotexoit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movie")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "year")
	private Integer year;
	@Column(name = "producers")
	private String producers;
	@Column(name = "studios")
	private String studios;
	@Column(name = "winner")
	private boolean winner;
	
	public Movie() {}
	
	public Movie(String title, String producers, String studios, Integer year, boolean winner) {
		super();
		this.title = title;
		this.producers = producers;
		this.studios = studios;
		this.year = year;
		this.winner = winner;
	}

	public Long getId() {
		return id;
	}
	
	public String getProducers() {
		return producers;
	}
	
	public String getStudios() {
		return studios;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setProducers(String producers) {
		this.producers = producers;
	}
	
	public void setStudios(String studios) {
		this.studios = studios;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public void setYear(Integer year) {
		this.year = year;
	}
}
