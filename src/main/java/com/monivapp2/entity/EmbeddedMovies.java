package com.monivapp2.entity;

import java.util.List;

public class EmbeddedMovies {
	
	private List<Movie> movies;

	public EmbeddedMovies() {

	}
	
	public EmbeddedMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}