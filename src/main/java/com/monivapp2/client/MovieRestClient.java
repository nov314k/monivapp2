package com.monivapp2.client;

import java.util.List;

import com.monivapp2.entity.Movie;

public interface MovieRestClient {
	
	public List<Movie> getMovies();
	
	public Movie getMovie(int theId);
}