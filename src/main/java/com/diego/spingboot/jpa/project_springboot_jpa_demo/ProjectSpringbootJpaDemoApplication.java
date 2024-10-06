package com.diego.spingboot.jpa.project_springboot_jpa_demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.diego.spingboot.jpa.project_springboot_jpa_demo.entities.Person;
import com.diego.spingboot.jpa.project_springboot_jpa_demo.repositori.PersonRepository;

@SpringBootApplication
public class ProjectSpringbootJpaDemoApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringbootJpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// List<Person> persons = (List<Person>) personRepository.findAll();
		// List<Person> persons = (List<Person>)
		// personRepository.findByPrograminglanguage("PYTHON");
		//List<Person> persons = (List<Person>) personRepository.BuscarByPrograminglanguage("JAVA");
		List<Person> persons = (List<Person>) personRepository.findByPrograminglanguageAndName("JAVA", "Guerrero");
		persons.forEach(person -> {
			System.out.println(person);
		});
	}

}
