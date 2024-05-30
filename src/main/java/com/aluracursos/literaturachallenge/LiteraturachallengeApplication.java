package com.aluracursos.literaturachallenge;

import com.aluracursos.literaturachallenge.principal.Principal;
import com.aluracursos.literaturachallenge.repository.AutorRepository;
import com.aluracursos.literaturachallenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturachallengeApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository librosRepository;

	@Autowired
	private AutorRepository autoresRepository;
	public static void main(String[] args) {
		SpringApplication.run(LiteraturachallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository,autoresRepository);
		principal.principal();

	}
}
