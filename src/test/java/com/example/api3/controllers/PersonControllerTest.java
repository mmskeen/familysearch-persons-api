package com.example.api3.controllers;

import com.example.api3.models.Address;
import com.example.api3.models.Person;
import com.example.api3.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonControllerTest {

    @InjectMocks
    PersonController controller;

    @Mock
    PersonService service;

    Person person1, person2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Address address1 = new Address("123 Main St", "Provo", "UT", "84604", "USA");
        person1 = new Person("John", "Doe", address1);
        person2 = new Person("Johnny2", "Doe", address1);
    }

    @Test
    void getPersons() {
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        when(service.findAll()).thenReturn(list);
        ResponseEntity<List<Person>> returnedListEntity = controller.getPersons();

        assertEquals(list.size(), returnedListEntity.getBody().size());
        assertEquals(HttpStatus.OK, returnedListEntity.getStatusCode());
    }

    @Test
    void getPersons_emptyList() {
        List<Person> list = new ArrayList<>();
        when(service.findAll()).thenReturn(list);
        ResponseEntity<List<Person>> returnedListEntity = controller.getPersons();

        assertEquals(0, returnedListEntity.getBody().size());
        assertEquals(HttpStatus.OK, returnedListEntity.getStatusCode());
    }

    @Test
    void getPerson() {
        when(service.findOne((long) 1)).thenReturn(person1);
        ResponseEntity<Person> returnedPersonEntity = controller.getPerson(1);

        assertEquals(person1.getFirstName(), returnedPersonEntity.getBody().getFirstName());
        assertEquals(HttpStatus.OK, returnedPersonEntity.getStatusCode());
    }

    @Test
    void getPerson_notFound() {
        when(service.findOne((long) 5)).thenReturn(null);
        ResponseEntity<Person> returnedPersonEntity = controller.getPerson(5);

        assertNull(returnedPersonEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, returnedPersonEntity.getStatusCode());
    }

    @Test
    void newPerson() {
        when(service.create(person1)).thenReturn(person1);
        ResponseEntity<Person> returnedPersonEntity = controller.newPerson(person1);

        assertEquals(person1.getFirstName(), returnedPersonEntity.getBody().getFirstName());
        assertEquals(HttpStatus.OK, returnedPersonEntity.getStatusCode());
    }

    @Test
    void newPerson_Error() {
        when(service.create(person1)).thenReturn(null);
        ResponseEntity<Person> returnedPersonEntity = controller.newPerson(person1);

        assertNull(returnedPersonEntity.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, returnedPersonEntity.getStatusCode());
    }

    @Test
    void updatePerson() {
        when(service.update((long) 1, person2)).thenReturn(person2);
        ResponseEntity<Person> returnedPersonEntity = controller.updatePerson(1, person2);

        assertEquals(person2.getFirstName(), returnedPersonEntity.getBody().getFirstName());
        assertEquals(HttpStatus.OK, returnedPersonEntity.getStatusCode());
    }

    @Test
    void updatePerson_notFound() {
        when(service.update((long) 5, person2)).thenReturn(null);
        ResponseEntity<Person> returnedPersonEntity = controller.updatePerson(5, person2);

        assertNull(returnedPersonEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, returnedPersonEntity.getStatusCode());
    }

    @Test
    void deletePerson() {
        when(service.delete((long) 1)).thenReturn(true);
        ResponseEntity returnedPersonEntity = controller.deletePerson(1);

        assertEquals(HttpStatus.NO_CONTENT, returnedPersonEntity.getStatusCode());
    }

    @Test
    void deletePerson_notFound() {
        when(service.delete((long) 5)).thenReturn(false);
        ResponseEntity returnedPersonEntity = controller.deletePerson(5);

        assertEquals(HttpStatus.NOT_FOUND, returnedPersonEntity.getStatusCode());
    }

}