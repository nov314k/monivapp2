package com.monivapp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.monivapp2.client.MovieRestClient;
import com.monivapp2.entity.Movie;

@Controller
public class MovieController {

	@Autowired
	private MovieRestClient movieRestClient;
	
	@GetMapping("/list")
	public String getMovies(Model theModel) {

		List<Movie> movies = movieRestClient.getMovies();
		theModel.addAttribute("movies", movies);
		return "movies";
	}
	
	@GetMapping("/list/{movieId}")
	public String getMovie(Model theModel, @PathVariable int movieId) {

		Movie theMovie = movieRestClient.getMovie(movieId);
		theModel.addAttribute("theMovie", theMovie);
		return "movie";
	}
}