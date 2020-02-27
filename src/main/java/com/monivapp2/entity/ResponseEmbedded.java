package com.monivapp2.entity;

import java.util.List;

public class EmbeddedResponse {
	
	private List<Movie> movies;

	public EmbeddedResponse() {

	}
	
	public EmbeddedResponse(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}