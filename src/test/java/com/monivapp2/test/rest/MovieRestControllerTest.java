package com.monivapp2.test.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.monivapp2.entity.Movie;
import com.monivapp2.rest.MovieRestController;
import com.monivapp2.service.MovieService;

@RunWith(SpringRunner.class)
@WebMvcTest(MovieRestController.class)
public class MovieRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MovieService movieService;
	
	@Test
	public void restMovies() throws Exception{
		
		when(movieService.getAllMovies()).thenReturn(Arrays.asList(
						new Movie(1,"The Matrix"),
						new Movie(2,"The Matrix Reloaded"),
						new Movie(3,"The Matrix Revolutions")));
		
		String expected = "["
				+ "{id:1, title:\"The Matrix\"},"
				+ "{id:2, title:\"The Matrix Reloaded\"},"
				+ "{id:3, title:\"The Matrix Revolutions\"}"
				+ "]";
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/movies")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(expected))
				.andReturn();
	}
}