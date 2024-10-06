package com.diego.spingboot.jpa.project_springboot_jpa_demo.repositori;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.diego.spingboot.jpa.project_springboot_jpa_demo.entities.Person;
import java.util.List;


public interface PersonRepository extends CrudRepository <Person, Long> {

    //metodo predeterminado por JPA
List<Person> findByPrograminglanguage(String programinglanguage);

//Creamos un metodo llamando a los atrivutos de la clase person 
//@Query("select p from Person p where p.programinglanguage=?1")
//List<Person> BuscarByPrograminglanguage(String programinglanguage);

List<Person> findByPrograminglanguageAndName(String programinglanguage, String name);

}
