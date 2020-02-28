package com.monivapp2.test.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.logging.Logger;

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

import com.monivapp2.entity.Vote;
import com.monivapp2.rest.VoteRestController;
import com.monivapp2.service.VoteService;

@RunWith(SpringRunner.class)
@WebMvcTest(VoteRestController.class)
public class VoteRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VoteService voteService;
	
	Logger logger = Logger.getGlobal();
	
	@Test
	public void restMoviesGet() throws Exception {
		
		when(voteService.getAllVotes()).thenReturn(Arrays.asList(
						new Vote(1, 1),
						new Vote(2, 2),
						new Vote(3, 3)));
		
		String expected = "["
				+ "{id:1,movieid:1},"
				+ "{id:2,movieid:2},"
				+ "{id:3,movieid:3}"
				+ "]";
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/votes")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(expected))
				.andReturn();
	}
	
	@Test
	public void restMoviesActionVotePost() throws Exception {
		
		String voteToAdd = "{\"id\":1,\"movieid\":3}";
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/rest/movies/action/vote")
				.accept(MediaType.APPLICATION_JSON)
				.content(voteToAdd)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
	    		.andExpect(status().isOk())
	    		.andReturn();
	    
	    logger.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void restMoviesActionVoteGet() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/movies/action/vote/2")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		
	    logger.info(result.getResponse().getContentAsString());
	}
	
	@Test
	public void restLimitsGet() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/rest/limits")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andReturn();
		
	    logger.info(result.getResponse().getContentAsString());
	}
}