package com.monivapp2.entity;

import java.util.List;

public class ResponseEmbedded {
	
	private List<Movie> movies;

	public ResponseEmbedded() {

	}
	
	public ResponseEmbedded(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}