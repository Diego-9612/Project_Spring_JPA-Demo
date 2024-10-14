package com.diego.spingboot.jpa.project_springboot_jpa_demo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.diego.spingboot.jpa.project_springboot_jpa_demo.dto.PersonDto;
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

		// findOne();
		// findByProgramingLanguage();
		// findByProgrammingLanguageAndName();
		// buscarByProgramingLanguage();
		// buscarByProgramingLanguageAndName();
		// obtenerPersonaData();
		// obtenerPersonaDataByName("Diego");
		// obtenerPersonaDataByProgramingLanguageAndName("Java", "Guerrero");
		// findOneLikeName("di");
		// findByNameContaining("Diego");
		// create();
		// personalizedQueriesBetween();
		// personalizedQueriesConcatUpperAndLowerCase();
		// queriesFunctionAggregation();
		whereIn();
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("================== consulta where in ==================");
		List<Person> persons = personRepository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("================== consulta por el nombre mas corto y su largo ==================");
		List<Object[]> registers = personRepository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("================== consulta pra obtener el ultimo registro de persona ==================");
		Optional<Person> optionalPerson = personRepository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {

		System.out.println(
				"================== consulta con el total de registros de la tabla persona ==================");
		Long count = personRepository.getTotalPerson();
		System.out.println(count);

		System.out.println("================== consulta con el valor minimo del id ==================");
		Long min = personRepository.getMinId();
		System.out.println(min);

		System.out.println("================== consulta con el valor maximo del id");
		Long max = personRepository.getMaxId();
		System.out.println(max);

		System.out.println("================== consulta con el nombre y su largo ==================");
		List<Object[]> regs = personRepository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});

		System.out.println("================== consulta con el nombre mas corto ==================");
		Integer minLengthName = personRepository.getMinLengthName();
		System.out.println(minLengthName);

		System.out.println("================== consulta con el nombre mas largo ==================");
		Integer maxLengthName = personRepository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println(
				"================== consultas resumen de funciones de agregacion min, max, sum, avg, count ==================");
		Object[] resumeReg = (Object[]) personRepository.getResumeAggregationFunction();
		System.out.println(
				"min=" + resumeReg[0] +
						", max=" + resumeReg[1] +
						", sum=" + resumeReg[2] +
						", avg=" + resumeReg[3] +
						", count=" + resumeReg[4]);
	}

	@Transactional(readOnly = true)
	public void personalizedQueriesBetween() {
		System.out.println("================== consultas por rangos ==================");
		List<Person> persons = personRepository.findByIdBetweenOrderByNameAsc(2L, 3L);
		persons.forEach(System.out::println);

		persons = personRepository.findByNameBetweenOrderByNameDescLastNameDesc("G", "P");
		persons.forEach(System.out::println);

		persons = personRepository.findAllByOrderByNameAscLastNameDesc();
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesConcatUpperAndLowerCase() {
		System.out.println("================== consulta nombres y apellidos de personas ==================");
		List<String> names = personRepository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos mayuscula ==================");
		names = personRepository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos minuscula ==================");
		names = personRepository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("================== consulta personalizada persona upper y lower case ==================");
		List<Object[]> regs = personRepository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out
				.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));

	}

	@Transactional(readOnly = true)
	public void personalizedQueriesDistinct() {
		System.out.println("================== consultas con nombres de personas ==================");
		List<String> names = personRepository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("==================  consultas con nombres unicos de personas ==================");
		names = personRepository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("================== consulta con lenguaje de programacion unicas ==================");
		List<String> languages = personRepository.findAllPrograminglanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println(
				"================== consulta con total de lenguajes de programacion unicas ==================");
		Long totalLanguage = personRepository.findAllPrograminglanguageDistinctCount();
		System.out.println("total de lenguajes de programacion: " + totalLanguage);

	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println(
				"================== consulta por objeto persona y lenguaje de programacion ==================");
		List<Object[]> personsRegs = personRepository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = personRepository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("consulta que puebla y devuelve objeto dto de una clase personalizada");
		List<PersonDto> personsDto = personRepository.findAllPersonDto();
		personsDto.forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("================== consulta solo el nombre por el id ==================");
		System.out.println("Ingrese el id:");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("===== mostrando solo el nombre =====");
		String name = personRepository.getNameById(id);
		System.out.println(name);

		System.out.println("===== mostrando solo el id =====");
		Long idDb = personRepository.getIdById(id);
		System.out.println(idDb);

		System.out.println("===== mostrando nombre completo con concat =====");
		String fullName = personRepository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("===== consulta por campos personalizados por el id =====");
		Optional<Object> optionalReg = personRepository.obtenerPersonDataById(id);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("id=" + personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]
					+ ", lenguaje=" + personReg[3]);
		}

		System.out.println("===== consulta por campos personalizados lista ======");
		List<Object[]> regs = personRepository.obtenerPersonDataList();
		regs.forEach(reg -> System.out
				.println("id=" + reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2] + ", lenguaje=" + reg[3]));
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
		personRepository.findOne(1L).ifPresent(person -> System.out.println("Persona encontrada: " + person));
	}

	public void findByProgramingLanguage() {
		List<Person> persons = personRepository.findByPrograminglanguage("Java");
		persons.forEach(person -> System.out.println("Persona con Java: " + person));
	}

	public void findByProgrammingLanguageAndName() {
		List<Person> persons = personRepository.findByPrograminglanguageAndName("Java", "Diego");
		persons.forEach(person -> System.out.println("Persona llamada Diego que programa en Java: " + person));
	}

	public void buscarByProgramingLanguage() {
		List<Person> persons = personRepository.BuscarByPrograminglanguage("Python");
		persons.forEach(person -> System.out.println("Persona con Python: " + person));
	}

	public void buscarByProgramingLanguageAndName() {
		List<Person> persons = personRepository.buscarByPrograminglanguageAndName("Python", "Andres");
		persons.forEach(person -> System.out.println("Persona llamada Andres que programa en Python: " + person));
	}

	public void obtenerPersonaData() {
		List<Object[]> personsData = personRepository.obtenerPersonaData();
		personsData.forEach(data -> System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
	}

	public void obtenerPersonaDataByName(String name) {
		List<Object[]> personsData = personRepository.obtenerPersonaData(name);
		personsData.forEach(data -> System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
	}

	public void obtenerPersonaDataByProgramingLanguageAndName(String programingLanguage, String name) {
		List<Object[]> personsData = personRepository.obtenerPersonaData(programingLanguage, name);
		personsData.forEach(data -> System.out.println("Nombre: " + data[0] + ", Lenguaje: " + data[1]));
	}

	public void findOneLikeName(String name) {
		personRepository.findOneLikeName(name)
				.ifPresent(person -> System.out.println("Persona que contiene '" + name + "' en su nombre: " + person));
	}

	public void findByNameContaining(String name) {
		personRepository.findByNameContaining(name)
				.ifPresent(person -> System.out.println("Persona cuyo nombre contiene '" + name + "': " + person));
	}
}
