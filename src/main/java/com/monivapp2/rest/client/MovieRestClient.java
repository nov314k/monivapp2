package com.monivapp2.rest.client;

import java.util.List;

import com.monivapp2.entity.Movie;

public interface MovieRestClient {
	
	public List<Movie> getAllMovies();
	
	public Movie getMovie(int theId);
}