package com.monivapp2.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monivapp2.entity.Response;
import com.monivapp2.entity.Movie;

@Service
public class MovieRestClientImpl implements MovieRestClient {

	private RestTemplate restTemplate;
	private final String REST_API_URL = "http://localhost:8080/movies/";
	
	@Autowired
	public MovieRestClientImpl(RestTemplate theRestTemplate) {
		restTemplate = theRestTemplate;
	}
	
	@Override
	public List<Movie> getMovies() {
		
		Response theResponse = restTemplate.getForObject(REST_API_URL, Response.class);
		return theResponse.get_embedded().getMovies();
	
//		ResponseEntity<List<Movie>> responseEntity = restTemplate.exchange(
//				"http://localhost:8080/movies/",
//				HttpMethod.GET,
//				null,
//				new ParameterizedTypeReference<List<Movie>>() {});
//		String movies = responseEntity.getBody().toString();
//		return movies;
	}
	
	@Override
	public Movie getMovie(int theId) {
		
		Movie theMovie = restTemplate.getForObject(REST_API_URL + Integer.toString(theId),
				Movie.class);
		return theMovie;
	}
}