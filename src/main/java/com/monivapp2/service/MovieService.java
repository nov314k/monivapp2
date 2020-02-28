package com.monivapp2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monivapp2.entity.Movie;
import com.monivapp2.entity.Vote;
import com.monivapp2.exception.MovieNotFoundException;
import com.monivapp2.repository.MovieRepository;
import com.monivapp2.repository.VoteRepository;

@Component
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	public List<Movie> getAllMovies() {
		
		return movieRepository.findAll();
	}
	
	public List<Movie> getAllMoviesOrderByVotesDesc() {
		
		return movieRepository.findAllByOrderByVotesDesc();
	}
	
	public Movie addMovie(Movie theMovie) {

		return movieRepository.save(theMovie);
	}

	public Movie voteForMovie(int movieId) {

		Movie theMovie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException(movieId));
		theMovie.setVotes(theMovie.getVotes() + 1);
		movieRepository.save(theMovie);
		Vote theVote = new Vote(movieId);
		voteRepository.save(theVote);
		return theMovie;
	}
}