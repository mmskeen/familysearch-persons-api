package com.example.api3.services;

import com.example.api3.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findOne(long id);

    Person create(Person person);

    Person update(long id, Person person);

    boolean delete(long id);

}
