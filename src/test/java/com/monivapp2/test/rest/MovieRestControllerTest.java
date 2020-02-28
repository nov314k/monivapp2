package com.monivapp2.test.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
	
	Logger logger = Logger.getGlobal();
	
	@Test
	public void restMoviesGet() throws Exception {
		
		when(movieService.getAllMoviesOrderByVotesDesc()).thenReturn(Arrays.asList(
						new Movie(1, "The Matrix", 2),
						new Movie(2, "The Matrix Reloaded", 1),
						new Movie(3, "The Matrix Revolutions", 0)));
		
		String expected = "["
				+ "{id:1,title:\"The Matrix\",votes:2},"
				+ "{id:2,title:\"The Matrix Reloaded\",votes:1},"
				+ "{id:3,title:\"The Matrix Revolutions\",votes:0}"
				+ "]";
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/movies")
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(expected))
				.andReturn();
	}
	
	@Test
	public void restMoviesPost() throws Exception {
		
		String expected = "{\"title\":\"Movie to Add\",\"votes\":0}";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/rest/movies")
				.accept(MediaType.APPLICATION_JSON)
				.content(expected)
				.contentType(MediaType.APPLICATION_JSON);
		
	    MvcResult result = mockMvc.perform(request)
	    		.andExpect(status().isOk())
	    		.andReturn();
	    
	    logger.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void restMoviesVoteGet() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/movies/vote/2")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();

		logger.info(result.getResponse().getContentAsString());
	}
}