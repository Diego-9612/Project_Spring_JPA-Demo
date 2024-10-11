package com.diego.spingboot.jpa.project_springboot_jpa_demo.repositori;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.spingboot.jpa.project_springboot_jpa_demo.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    // Método personalizado con @Query para buscar una persona por su ID
    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    // Métodos proporcionados automáticamente por JPA basados en nombres de atributos.
    // Busca una lista de personas que tengan el mismo lenguaje de programación.
    List<Person> findByPrograminglanguage(String programinglanguage);

    // Busca una lista de personas por lenguaje de programación y nombre.
    List<Person> findByPrograminglanguageAndName(String programinglanguage, String name);

    // Método personalizado con @Query para buscar personas por su lenguaje de programación.
    @Query("select p from Person p where p.programinglanguage=?1")
    List<Person> BuscarByPrograminglanguage(String programinglanguage);

    // Método personalizado con @Query para buscar personas por lenguaje de programación y nombre.
    @Query("select p from Person p where p.programinglanguage=?1 and p.name=?2")
    List<Person> buscarByPrograminglanguageAndName(String programinglanguage, String name);

    // Similar al anterior, pero corrige el nombre del campo "programmingLanguage" en lugar de "programinglanguage".
    @Query("select p from Person p where p.programinglanguage=?1 and p.name=?2")
    List<Person> buscarByPrograminglanguage(String programinglanguage, String name);

    // Método que devuelve solo el nombre y lenguaje de programación de todas las personas.
    @Query("select p.name, p.programinglanguage from Person p")
    List<Object[]> obtenerPersonaData();

    // Devuelve los datos de personas que tienen un nombre específico.
    @Query("select p from Person p where p.name=?1")
    List<Object[]> obtenerPersonaData(String name);

    // Método que busca una persona cuyo nombre contenga una parte del texto (búsqueda por "like").
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    // Busca una persona cuyo nombre contenga una parte del texto usando un método predeterminado de JPA.
    Optional<Person> findByNameContaining(String name);

    // Devuelve el nombre y lenguaje de programación de personas que coincidan con un lenguaje de programación y un nombre específicos.
    @Query("select p.name, p.programinglanguage from Person p where p.programinglanguage=?1 and p.name =?2")
    List<Object[]> obtenerPersonaData(String programinglanguage, String name);
}

