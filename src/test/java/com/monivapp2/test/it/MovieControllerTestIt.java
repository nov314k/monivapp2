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
	
	static private String voteUrl;
	static private RestTemplate restTemplate;
	static private HttpHeaders httpHeaders;
	static private JSONObject voteJsonObject;
	
	@BeforeClass
	public static void runBeforeAllTestMethods() throws JSONException {
		
		voteUrl = "http://localhost:8080/rest/movies/action/vote";
		restTemplate = new RestTemplate();
		httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		voteJsonObject = new JSONObject();
	}

	@Test
	@Order(1)
	public void contextLoads() throws JSONException {
		
	}
	
	@Test
	@Order(2)
	public void areMoviesCorrectlyPreloaded() throws JSONException {
		
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
	public void areVotesCorrectlyPreloaded() throws JSONException {
		
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
	public void isCorrectJsonReturnedAfterVoting_ViaVotingRest_ViaGet()
			throws JSONException {
		
		String response = this.restGetTemplate
				.getForObject("/rest/movies/vote/2", String.class);
		
		JSONAssert.assertEquals("{id:2,title:\"The Matrix Reloaded\",votes:2},",
				response, false);
	}
	
	@Test
	@Order(5)
	public void isCorrectJsonReturnedAfterVoting_ViaVotingRest_ViaPost()
			throws JSONException, JsonMappingException, JsonProcessingException {
		
		voteJsonObject.put("movieid", "77");
		final ObjectMapper objectMapper = new ObjectMapper();
		
		HttpEntity<String> request = 
			      new HttpEntity<String>(voteJsonObject.toString(), httpHeaders);
		
		String voteResultAsJsonStr = restTemplate
				.postForObject(voteUrl, request, String.class);

		JsonNode root = objectMapper.readTree(voteResultAsJsonStr);
		
		assertNotNull(voteResultAsJsonStr);
	    assertNotNull(root);
	    assertNotNull("77", root.path("movieid").asText());
	}
}