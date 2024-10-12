package com.diego.spingboot.jpa.project_springboot_jpa_demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_Name")
    private String lastName;

    @Column(name = "programing_language")
    private String programinglanguage;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person(Long id, String name, String lastName, String programinglanguage) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.programinglanguage = programinglanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrograminglanguage() {
        return programinglanguage;
    }

    public void setPrograminglanguage(String programinglanguage) {
        this.programinglanguage = programinglanguage;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", lastName=" + lastName + ", programinglanguage="
                + programinglanguage + "]";
    }

}
