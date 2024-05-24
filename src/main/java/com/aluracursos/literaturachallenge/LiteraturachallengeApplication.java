package com.aluracursos.literaturachallenge;

import com.aluracursos.literaturachallenge.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturachallengeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturachallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();

	}
}
