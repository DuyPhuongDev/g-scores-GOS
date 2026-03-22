package com.dphuong.go.gscores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GscoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(GscoresApplication.class, args);
	}

}
