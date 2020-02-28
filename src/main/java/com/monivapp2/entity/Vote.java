package com.monivapp2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="movieId")
	private int movieId;
	
	public Vote() {
		
	}

	public Vote(int id, int movie_id) {
		this.id = id;
		this.movieId = movie_id;
	}

	public Vote(int movie_id) {
		this.movieId = movie_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovie_id() {
		return movieId;
	}

	public void setMovie_id(int movie_id) {
		this.movieId = movie_id;
	}
}