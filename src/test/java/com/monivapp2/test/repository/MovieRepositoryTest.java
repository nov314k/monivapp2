package com.monivapp2.test.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.monivapp2.entity.Movie;
import com.monivapp2.repository.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;
	
	@Test
	public void findAll() {
		
		List<Movie> items = movieRepository.findAll();
		assertEquals(3, items.size());
	}
}