package com.springmvc.demo;

import com.springmvc.demo.domain.SocialMetaTag;
import com.springmvc.demo.service.SocialMetaTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Autowired
	SocialMetaTagService socialMetaTagService;
	@Override
	public void run(String... args) throws Exception {

	}
}
