package com.hystrix.dashboard.controller;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/rest/hystrix")
public class HelloController {

	@HystrixCommand(fallbackMethod = "fallback", commandKey = "hello", groupKey = "hello")
	@GetMapping("/hello")
	public String hello() {
		if (RandomUtils.nextBoolean()) {
			throw new RuntimeException("failed!!");
		} else {
			return "Hello User!!";
		}
	}

	public String fallback() {
		return "Failed Hello!!";
	}

	@HystrixCommand(fallbackMethod = "fallbackFacebook", commandKey = "helloFacebook", groupKey = "helloFacebook")
	@GetMapping("/hellofacebook")
	public String helloFacebook() {
		if (RandomUtils.nextBoolean()) {
			throw new RuntimeException("failed Facebook!!");
		} else {
			return "Hello Facebook User!!";
		}
	}

	public String fallbackFacebook() {
		return "Failed Facebook Hello!!";
	}

}
