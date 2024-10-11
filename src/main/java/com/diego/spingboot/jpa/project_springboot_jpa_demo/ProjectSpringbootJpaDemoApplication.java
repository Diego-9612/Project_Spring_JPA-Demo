package com.diego.spingboot.jpa.project_springboot_jpa_demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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

        findOne();
		findByProgramingLanguage();
        findByProgrammingLanguageAndName();
		buscarByProgramingLanguage();
		buscarByProgramingLanguageAndName();
        obtenerPersonaData();
        obtenerPersonaDataByName("Diego");
		obtenerPersonaDataByProgramingLanguageAndName("Java", "Guerrero");
        findOneLikeName("di");
        findByNameContaining("Diego");
		create();
    }

	@Transactional
	public void create() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido:");
		String lastName = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion:");
		String programinglanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programinglanguage);

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);

	}

	@Transactional
	public void delete2() {
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		optionalPerson.ifPresentOrElse(personRepository::delete,
		() -> System.out.println("Lo sentimos no existe la persona con ese id!"));

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	}

	@Transactional
	public void delete() {
		personRepository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id a eliminar:");
		Long id = scanner.nextLong();
		personRepository.deleteById(id);

		personRepository.findAll().forEach(System.out::println);

		scanner.close();
	}
	
	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el id de la persona:");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = personRepository.findById(id);

		// optionalPerson.ifPresent(person -> {
		if (optionalPerson.isPresent()) {
			Person personDB = optionalPerson.orElseThrow();	

			System.out.println(personDB);
			System.out.println("Ingrese el lenguaje de programacion:");
			String programmingLanguage = scanner.next();
			personDB.setPrograminglanguage(programmingLanguage);
			Person personUpdated = personRepository.save(personDB);
			System.out.println(personUpdated);
		} else {
			System.out.println("El usuario no esta presente! no existe!");
		}

		scanner.close();
	}

    // Implementación de los métodos personalizados y predeterminados

    public void findOne() {
        personRepository.findOne(1L).ifPresent(person -> 
            System.out.println("Persona encontrada: " + person));
    }

    public void findByProgramingLanguage() {
        List<Person> persons = personRepository.findByPrograminglanguage("Java");
        persons.forEach(person -> 
            System.out.println("Persona con Java: " + person));
    }

    public void findByProgrammingLanguageAndName() {
        List<Person> persons = personRepository.findByPrograminglanguageAndName("Java", "Diego");
        persons.forEach(person -> 
            System.out.println("Persona llamada Diego que programa en Java: " + person));
    }

    public void buscarByProgramingLanguage() {
        List<Person> persons = personRepository.BuscarByPrograminglanguage("Python");
        persons.forEach(person -> 
            System.out.println("Persona con Python: " + person));
    }

    public void buscarByProgramingLanguageAndName() {
        List<Person> persons = personRepository.buscarByPrograminglanguageAndName("Python", "Andres");
        persons.forEach(person -> 
            System.out.println("Persona llamada Andres que programa en Python: " + person));
    }

    public void obtenerPersonaData() {
        List<Object[]> personsData = personRepository.obtenerPersonaData();
        personsData.forEach(data -> 
            System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
    }

    public void obtenerPersonaDataByName(String name) {
        List<Object[]> personsData = personRepository.obtenerPersonaData(name);
        personsData.forEach(data -> 
            System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
    }

    public void obtenerPersonaDataByProgramingLanguageAndName(String programingLanguage, String name) {
        List<Object[]> personsData = personRepository.obtenerPersonaData(programingLanguage, name);
        personsData.forEach(data -> 
            System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
    }

    public void findOneLikeName(String name) {
        personRepository.findOneLikeName(name).ifPresent(person -> 
            System.out.println("Persona que contiene '" + name + "' en su nombre: " + person));
    }

    public void findByNameContaining(String name) {
        personRepository.findByNameContaining(name).ifPresent(person -> 
            System.out.println("Persona cuyo nombre contiene '" + name + "': " + person));
    }
}
