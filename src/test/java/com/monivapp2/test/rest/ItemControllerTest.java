package com.monivapp2.test.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.monivapp2.rest.ItemController;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void item_basic() throws Exception{
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/item")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
//				.andExpect(content().string("{\"id\":1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}"))
				.andExpect(content().json("{id:1, name:Ball, price:10, quantity:100}"))
				.andReturn();
	}
}