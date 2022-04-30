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
	private long year;
	@Column(name = "producer")
	private String producer;
	@Column(name = "studio")
	private String studio;
	@Column(name = "winner")
	private boolean winner;
	
	public Movie() {}
	
	public Movie(String title, String producer, String studio, long year, boolean winner) {
		super();
		this.title = title;
		this.producer = producer;
		this.studio = studio;
		this.year = year;
		this.winner = winner;
	}

	public Long getId() {
		return id;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public String getStudio() {
		return studio;
	}
	
	public String getTitle() {
		return title;
	}
	
	public long getYear() {
		return year;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public void setStudio(String studio) {
		this.studio = studio;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setWinner(boolean winner) {
		this.winner = winner;
	}
	
	public void setYear(long year) {
		this.year = year;
	}
}
