package com.example.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@GetMapping(path = "hello/{name}")
	public String hello(@PathVariable String name) {
		log.debug("Entered home controller");
		log.debug("Exited home controller");
		return "Hello " + name + "...!!!";
		
	}
	
}
