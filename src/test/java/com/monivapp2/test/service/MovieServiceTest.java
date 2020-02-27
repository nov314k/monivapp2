package com.monivapp2.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.monivapp2.entity.Movie;
import com.monivapp2.repository.MovieRepository;
import com.monivapp2.service.MovieService;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

	@InjectMocks
	private MovieService movieService;

	@Mock
	private MovieRepository movieRepository;

	@Test
	public void getAllMovies() {
		
		when(movieRepository.findAll()).thenReturn(Arrays.asList(
				new Movie(1,"The Matrix"),
				new Movie(2,"The Matrix Reloaded"),
				new Movie(3,"The Matrix Revolutions")));

		List<Movie> items = movieService.getAllMovies();
		
		assertEquals("The Matrix", items.get(0).getTitle());
		assertEquals("The Matrix Reloaded", items.get(1).getTitle());
		assertEquals("The Matrix Revolutions", items.get(2).getTitle());
	}
}