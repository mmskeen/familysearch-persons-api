package com.example.api3.controllers;

import com.example.api3.models.Person;
import com.example.api3.repositories.PersonRepository;
import com.example.api3.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/persons", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<List<Person>>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        Person person = service.findOne(id);
        if (person == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> newPerson(@RequestBody Person person) {
        Person newPerson = service.create(person);
        if (newPerson == null) {
            return new ResponseEntity<Person>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<Person>(newPerson, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
        Person newPerson = service.update(id, person);
        if (newPerson == null) {
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<Person>(newPerson, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletePerson(@PathVariable("id") long id) {
        if (!service.delete(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
