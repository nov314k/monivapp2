package com.monivapp2.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monivapp2.entity.Item;

@RestController
public class ItemController {

	@GetMapping("/item")
	public Item item() {
		return new Item(1, "Ball", 10, 100);
	}
}