package com.monivapp2.test.rest;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public void restGetMovies() throws Exception {
		
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
		
		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(expected))
				.andReturn();
	}
	
	@Test
	public void restPostMovie() throws Exception {
		
		String movieToAdd = "{\"title\":\"Movie to Add\"}";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/rest/movie")
				.accept(MediaType.APPLICATION_JSON)
				.content(movieToAdd)
				.contentType(MediaType.APPLICATION_JSON);
		
	    MvcResult result = mockMvc
	    		.perform(request)
	    		.andExpect(status().isOk())
	    		.andReturn();
	}
	
	@Test
	public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
	  throws IOException, JSONException {
		
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
		
		JSONObject movieJsonObject = new JSONObject();
	    movieJsonObject.put("id", 100);
	    movieJsonObject.put("title", "Yet Another Movie");
	    
	    final ObjectMapper objectMapper = new ObjectMapper();
		
	    HttpEntity<String> request = 
	      new HttpEntity<String>(movieJsonObject.toString(), headers);
	     
	    String movieResultAsJsonStr = 
	      restTemplate.postForObject("http://localhost:8080/rest/movie/", request, String.class);
	    JsonNode root = objectMapper.readTree(movieResultAsJsonStr);
	     
	    assertNotNull(movieResultAsJsonStr);
	    assertNotNull(root);
	    assertNotNull(root.path("title").asText());
	}
}