package com.diego.spingboot.jpa.project_springboot_jpa_demo.repositori;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.spingboot.jpa.project_springboot_jpa_demo.dto.PersonDto;
import com.diego.spingboot.jpa.project_springboot_jpa_demo.entities.Person;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    // Método predeterminado de Spring Data JPA
    // Devuelve una lista de personas ordenadas por nombre ascendente (A-Z) y por
    // apellido descendente (Z-A).
    List<Person> findAllByOrderByNameAscLastNameDesc();

    // Método personalizado con @Query
    // Selecciona todas las personas y las ordena por nombre ascendente y apellido
    // descendente.
    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastName DESC")
    List<Person> getAllOrdered();

    // Método predeterminado de Spring Data JPA
    // Busca todas las personas cuyo ID esté entre id1 e id2 y las ordena por nombre
    // ascendente.
    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2);

    // Método predeterminado de Spring Data JPA
    // Busca todas las personas cuyo nombre esté entre name1 y name2, y las ordena
    // por nombre descendente y apellido descendente.
    List<Person> findByNameBetweenOrderByNameDescLastNameDesc(String name1, String name2);

    // Método personalizado con @Query
    // Selecciona todas las personas cuyo ID esté entre id1 e id2 y las ordena por
    // nombre descendente.
    @Query("SELECT p FROM Person p WHERE p.id BETWEEN ?1 AND ?2 ORDER BY p.name DESC")
    List<Person> findAllBetweenId(Long id1, Long id2);

    // Método personalizado con @Query
    // Selecciona todas las personas cuyo nombre esté entre c1 y c2, y las ordena
    // por nombre ascendente y apellido descendente.
    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2 ORDER BY p.name ASC, p.lastName DESC")
    List<Person> findAllBetweenName(String c1, String c2);

    // Método personalizado con @Query
    // Devuelve una lista con el ID, el nombre en mayúsculas, el apellido en
    // minúsculas y el lenguaje de programación en mayúsculas.
    @Query("SELECT p.id, upper(p.name), lower(p.lastName), upper(p.programinglanguage) FROM Person p")
    List<Object[]> findAllPersonDataListCase();

    // Método personalizado con @Query
    // Devuelve una lista de nombres completos concatenados (nombre + apellido) y
    // convertidos a mayúsculas.
    @Query("SELECT upper(p.name || ' ' || p.lastName) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    // Método personalizado con @Query
    // Devuelve una lista de nombres completos concatenados (nombre + apellido) y
    // convertidos a minúsculas.
    @Query("SELECT lower(concat(p.name, ' ', p.lastName)) FROM Person p")
    List<String> findAllFullNameConcatLower();

    // Método personalizado con @Query
    // Devuelve una lista de nombres completos concatenados (nombre + apellido) sin
    // cambios en mayúsculas o minúsculas.
    @Query("SELECT p.name || ' ' || p.lastName FROM Person p")
    List<String> findAllFullNameConcat();

    // Método personalizado con @Query
    // Devuelve el número de lenguajes de programación distintos que existen en la
    // tabla Person.
    @Query("SELECT COUNT(DISTINCT(p.programinglanguage)) FROM Person p")
    Long findAllPrograminglanguageDistinctCount();

    // Método personalizado con @Query
    /**
     * Devuelve una lista de todos los lenguajes de programación distintos (sin
     * duplicados) presentes en la tabla Person.
     */
    @Query("SELECT DISTINCT(p.programinglanguage) FROM Person p")
    List<String> findAllPrograminglanguageDistinct();

    // Método personalizado con @Query
    // Devuelve una lista con los nombres de todas las personas en la tabla Person.
    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    // Método personalizado con @Query
    // Devuelve una lista de nombres distintos (sin duplicados) de las personas en
    // la tabla Person.
    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    // Método personalizado con @Query
    // Devuelve una lista de objetos PersonDto que contiene solo los campos name y
    // lastName.
    @Query("SELECT NEW com.diego.spingboot.jpa.project_springboot_jpa_demo.dto.PersonDto(p.name, p.lastName) FROM Person p")
    List<PersonDto> findAllPersonDto();

    // Método personalizado con @Query
    // Devuelve una lista de objetos Person que contiene solo los campos name y
    // lastName (objeto personalizado).
    @Query("SELECT NEW  Person(p.name, p.lastName) FROM Person p")
    List<Person> findAllObjectPersonPersonalized();

    // Método personalizado con @Query
    // Devuelve el nombre de la persona (campo name) basado en su id.
    @Query("SELECT p.name FROM Person p WHERE p.id=?1")
    String getNameById(Long id);

    // Método personalizado con @Query
    // Devuelve el ID de una persona basado en su id.
    @Query("SELECT p.id FROM Person p WHERE p.id=?1")
    Long getIdById(Long id);

    // Método personalizado con @Query
    // Devuelve el nombre completo concatenado (name + lastName) de una persona por
    // su id.
    @Query("SELECT CONCAT(p.name, ' ', p.lastName) AS fullname FROM Person p WHERE p.id=?1")
    String getFullNameById(Long id);

    // Método personalizado con @Query
    // Devuelve una lista de arrays de objetos que contienen tanto la entidad Person
    // como su lenguaje de programación.
    @Query("SELECT p, p.programinglanguage FROM Person p")
    List<Object[]> findAllMixPerson();

    // Método personalizado con @Query
    // Retorna un objeto opcional que contiene los campos id, name, lastName, y
    // programinglanguage para una persona específica.
    @Query("SELECT p.id, p.name, p.lastName, p.programinglanguage FROM Person p WHERE p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);

    // Método personalizado con @Query
    // Devuelve una lista de arrays de objetos que contienen los campos id, name,
    // lastName, y programmingLanguage para todas las personas.
    @Query("SELECT p.id, p.name, p.lastName, p.programinglanguage FROM Person p")
    List<Object[]> obtenerPersonDataList();

    // Método personalizado con @Query
    // Devuelve un objeto Optional<Person> basado en el ID de la persona.
    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Optional<Person> findOne(Long id);

    // Método predeterminado de Spring Data JPA
    // Devuelve una lista de personas que tienen el mismo lenguaje de programación.
    List<Person> findByPrograminglanguage(String programinglanguage);

    // Método predeterminado de Spring Data JPA
    // Devuelve una lista de personas que tienen un lenguaje de programación y un
    // nombre específicos.
    List<Person> findByPrograminglanguageAndName(String programinglanguage, String name);

    // Método personalizado con @Query
    // Devuelve una lista de personas que tienen un lenguaje de programación
    // específico.
    @Query("SELECT p FROM Person p WHERE p.programinglanguage=?1")
    List<Person> BuscarByPrograminglanguage(String programinglanguage);

    // Método personalizado con @Query
    // Devuelve una lista de personas que tienen un lenguaje de programación y un
    // nombre específicos.
    @Query("SELECT p FROM Person p WHERE p.programinglanguage=?1 AND p.name=?2")
    List<Person> buscarByPrograminglanguageAndName(String programinglanguage, String name);

    // Método personalizado con @Query
    // Devuelve una lista de personas con el lenguaje de programación especificado
    // (nota: corrige el nombre del campo programinglanguage).
    @Query("select p FROM Person p WHERE p.programinglanguage=?1 AND p.name=?2")
    List<Person> buscarByPrograminglanguage(String programinglanguage, String name);

    // Método personalizado con @Query
    // Devuelve una lista de arrays de objetos que contienen solo el nombre y el
    // lenguaje de programación de todas las personas.
    @Query("SELECT p.name, p.programinglanguage FROM Person p")
    List<Object[]> obtenerPersonaData();

    // Método personalizado con @Query
    // Devuelve una lista de personas que tienen un nombre específico.
    @Query("SELECT p FROM Person p WHERE p.name=?1")
    List<Object[]> obtenerPersonaData(String name);

    // Método personalizado con @Query
    // Devuelve una persona cuyo nombre contenga una parte del texto (búsqueda con
    // like).
    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    // Método predeterminado de Spring Data JPA
    // Devuelve una persona cuyo nombre contenga una parte del texto (búsqueda de
    // subcadena).
    Optional<Person> findByNameContaining(String name);

    // Método personalizado con @Query
    // Devuelve el nombre y el lenguaje de programación de personas que coincidan
    // con un lenguaje de programación y un nombre específicos.
    @Query("SELECT p.name, p.programinglanguage FROM Person p WHERE p.programinglanguage=?1 AND p.name =?2")
    List<Object[]> obtenerPersonaData(String programinglanguage, String name);
}
