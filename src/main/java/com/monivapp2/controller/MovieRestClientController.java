package com.monivapp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monivapp2.entity.Movie;
import com.monivapp2.rest.client.MovieRestClient;

@Controller
@RequestMapping("/client")
public class MovieRestClientController {

	@Autowired
	private MovieRestClient movieRestClient;
	
	@GetMapping("/list")
	public String showListPage(Model theModel) {

		List<Movie> movies = movieRestClient.getAllMovies();
		theModel.addAttribute("movies", movies);
		return "client/listPage";
	}
	
	@GetMapping("/list/{movieId}")
	public String showOneMoviePage(Model theModel, @PathVariable int movieId) {

		Movie theMovie = movieRestClient.getMovie(movieId);
		theModel.addAttribute("theMovie", theMovie);
		return "client/oneMoviePage";
	}
}