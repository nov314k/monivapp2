package com.monivapp2.test.it;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MovieControllerTestIt {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws JSONException {
		
		String response = this.restTemplate.getForObject("/rest/movies",  String.class);
		JSONAssert.assertEquals("["
				+ "{id:1,title:\"The Matrix\"},"
				+ "{id:2,title:\"The Matrix Reloaded\"},"
				+ "{id:3,title:\"The Matrix Revolutions\"}"
				+ "]", response, false);
	}
}