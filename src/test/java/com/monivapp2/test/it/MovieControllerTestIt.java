package com.monivapp2.test.it;

import static org.junit.Assert.assertNotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class MovieControllerTestIt {
	
	@Autowired
	private TestRestTemplate restGetTemplate;
	
	static private String restMoviesPost_MovieControllerUrl_addAMovie;
	static private String restMoviesActionVotePost_VoteControllerUrl_voteForAMovie;
	static private RestTemplate restTemplate;
	static private HttpHeaders httpHeaders;
	static private JSONObject voteJsonObject;
	static private JSONObject movieJsonObject;
	
	@BeforeClass
	public static void runBeforeAllTestMethods() throws JSONException {
		
		restMoviesPost_MovieControllerUrl_addAMovie =
				"http://localhost:8080/rest/movies";
		restMoviesActionVotePost_VoteControllerUrl_voteForAMovie =
				"http://localhost:8080/rest/movies/action/vote";
			
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		voteJsonObject = new JSONObject();
		movieJsonObject = new JSONObject();
	}

	@Test
	@Order(1)
	public void contextLoads() throws JSONException {
		
	}
	
	@Test
	@Order(2)
	public void areMoviesCorrectlyPreloaded_restMoviesGet_MovieController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/movies", String.class);
		
		JSONAssert.assertEquals("["
				+ "{id:1,title:\"The Matrix\",votes:2},"
				+ "{id:2,title:\"The Matrix Reloaded\",votes:1},"
				+ "{id:3,title:\"The Matrix Revolutions\",votes:0}"
				+ "]", response, false);
	}
	
	@Test
	@Order(3)
	public void areVotesCorrectlyPreloaded_restVotesGet_VoteController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/votes", String.class);
		
		JSONAssert.assertEquals("["
				+ "{id:1,movieid:1},"
				+ "{id:2,movieid:2},"
				+ "{id:3,movieid:3}"
				+ "]", response, false);
	}
	
	@Test
	@Order(4)
	public void isCorrectJsonReturnedAfterAddingAMovie_restMoviesPost_MovieController()
			throws JSONException, JsonMappingException, JsonProcessingException {
		
		movieJsonObject.put("title", "Movie Under Test");
		final ObjectMapper objectMapper = new ObjectMapper();
		
		HttpEntity<String> request = 
			      new HttpEntity<String>(movieJsonObject.toString(), httpHeaders);
		
		String movieResultAsJsonStr = restTemplate
				.postForObject(restMoviesPost_MovieControllerUrl_addAMovie,
						request, String.class);

		JsonNode root = objectMapper.readTree(movieResultAsJsonStr);
		
		assertNotNull(movieResultAsJsonStr);
	    assertNotNull(root);
	    assertNotNull("Movie Under Test", root.path("title").asText());
	    assertNotNull("0", root.path("votes").asText());
	    // TODO Test id=4 (since I'm testing it elsewhere)?
		
	}
	
	@Test
	@Order(5)
	public void isCorrectJsonReturnedAfterVotingForAMovie_restMoviesVoteGet_MovieController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/movies/vote/4", String.class);
		
		JSONAssert.assertEquals("{id:4,title:\"Movie Under Test\",votes:1},", response,
				false);
	}
	
	@Test
	@Order(6)
	public void isCorrectJsonReturnedAfterJustAddoingARandomVote_restMoviesActionVotePost_VoteController()
			throws JSONException, JsonMappingException, JsonProcessingException {
		
		voteJsonObject.put("movieid", "77");
		final ObjectMapper objectMapper = new ObjectMapper();
		
		HttpEntity<String> request = 
			      new HttpEntity<String>(voteJsonObject.toString(), httpHeaders);
		
		String voteResultAsJsonStr = restTemplate
				.postForObject(restMoviesActionVotePost_VoteControllerUrl_voteForAMovie,
						request, String.class);

		JsonNode root = objectMapper.readTree(voteResultAsJsonStr);
		
		assertNotNull(voteResultAsJsonStr);
	    assertNotNull(root);
	    assertNotNull("77", root.path("movieid").asText());
	    // TODO Test id=5 (since I'm testing it elsewhere)?
	}
	
	@Test
	@Order(7)
	public void isCorrectJsonReturnedAfterJustAddingARandomVote_restMoviesActionVoteGet_VoteController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/movies/action/vote/88", String.class);
		
		JSONAssert.assertEquals("{id:6,movieid:88}", response, false);
	}
	
	@Test
	@Order(8)
	public void isCorrectCountOfVotesIntegerReturned_restLimits_VoteController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/limits", String.class);
		
		JSONAssert.assertEquals("6", response.toString(), false);
	}
	
	@Test
	@Order(9)
	public void isFinalMovieTableCorrect_restMovies_MovieController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/movies", String.class);
		
		JSONAssert.assertEquals("["
				+ "{id:1,title:\"The Matrix\",votes:2},"
				+ "{id:2,title:\"The Matrix Reloaded\",votes:1},"
				+ "{id:4,title:\"Movie Under Test\",votes:1},"
				+ "{id:3,title:\"The Matrix Revolutions\",votes:0}"
				+ "]", response, false);
	}
	
	@Test
	@Order(10)
	public void isFinalVoteTableCorrect_restVotes_VoteController()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/votes", String.class);
		
		JSONAssert.assertEquals("["
				+ "{id:1,movieid:1},"
				+ "{id:2,movieid:2},"
				+ "{id:3,movieid:3},"
				+ "{id:4,movieid:4},"
				+ "{id:5,movieid:77},"
				+ "{id:6,movieid:88}"
				+ "]", response, false);
	}
}