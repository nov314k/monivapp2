	package com.monivapp2.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp2.entity.Movie;
import com.monivapp2.service.MovieService;

@RestController
public class MovieRestController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/rest/movies")
	List<Movie> restMovies() {
		return movieService.getAllMovies();
	}
}