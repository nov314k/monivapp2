	package com.monivapp2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp2.entity.Movie;
import com.monivapp2.service.MovieService;

@RestController
public class MovieRestController {
	
	@Autowired
	public MovieService movieService;
	
	@GetMapping(
			value = "/rest/movies",
			produces = "application/json")
	public List<Movie> restMoviesGet() {
		
		return movieService.getAllMoviesOrderByVotesDesc();
	}
	
	@PostMapping(
			value = "/rest/movies",
			consumes = "application/json",
			produces = "application/json")
	public Movie restMoviesPost(@RequestBody Movie theMovie) {
		
		return movieService.addMovie(theMovie);
	}
	
	@GetMapping(
			value = "/rest/movies/vote/{movieId}",
			produces = "application/json")
	public Movie restMoviesVoteGet(@PathVariable int movieId) {
		
		return movieService.voteForMovie(movieId);
	}
}