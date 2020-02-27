package com.monivapp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monivapp2.entity.Movie;
import com.monivapp2.repository.MovieRepository;

@Component
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}
}