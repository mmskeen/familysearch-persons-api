package com.example.api3.services;

import com.example.api3.models.Address;
import com.example.api3.models.Person;
import com.example.api3.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultPersonServiceTest {

    @InjectMocks
    DefaultPersonService service;

    @Mock
    PersonRepository personRepository;

    Person person1, person2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

            Address address1 = new Address("123 Main St", "Provo", "UT", "84604", "USA");
            person1 = new Person("John", "Doe", address1);
            person2 = new Person("Johnny2", "Doe", address1);
    }

    @Test
    void findAll() {
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        when(personRepository.findAll()).thenReturn(list);
        List<Person> returnedList = service.findAll();
        assertNotNull(returnedList);
        assertEquals(list.size(), returnedList.size());
    }

    @Test
    void findAll_emptyList() {
        List<Person> list = new ArrayList<>();
        when(personRepository.findAll()).thenReturn(list);
        List<Person> returnedList = service.findAll();
        assertNotNull(returnedList);
        assertEquals(0, returnedList.size());
    }

    @Test
    void findOne() {
        when(personRepository.findById((long) 1)).thenReturn(Optional.of(person1));
        Person returnedPerson = service.findOne(1);
        assertNotNull(returnedPerson);
        assertEquals(person1.getFirstName(), returnedPerson.getFirstName());
    }

    @Test
    void findOne_notFound() {
        when(personRepository.findById((long) 5)).thenReturn(Optional.empty());
        Person returnedPerson = service.findOne(5);
        assertNull(returnedPerson);
    }

    @Test
    void create() {
        when(personRepository.save(person1)).thenReturn(person1);
        Person returnedPerson = service.create(person1);
        assertNotNull(returnedPerson);
        assertEquals(person1.getFirstName(), returnedPerson.getFirstName());
    }

    @Test
    void create_Error() {
        when(personRepository.save(person1)).thenReturn(null);
        Person returnedPerson = service.create(person1);
        assertNull(returnedPerson);
    }

    @Test
    void update() {
        when(personRepository.findById((long) 1)).thenReturn(Optional.of(person1));
        when(personRepository.save(person2)).thenReturn(person2);
        Person returnedPerson = service.update(1, person2);
        assertNotNull(returnedPerson);
        assertEquals(person2.getFirstName(), returnedPerson.getFirstName());
    }

    @Test
    void update_notFound() {
        when(personRepository.findById((long) 5)).thenReturn(Optional.empty());
        Person returnedPerson = service.update(5, person2);
        assertNull(returnedPerson);
    }

    @Test
    void update_Error() {
        when(personRepository.findById((long) 1)).thenReturn(Optional.of(person1));
        when(personRepository.save(person2)).thenReturn(null);
        Person returnedPerson = service.update(1, person2);
        assertNull(returnedPerson);
    }

    @Test
    void delete() {
        when(personRepository.findById((long) 1)).thenReturn(Optional.of(person1));
        boolean deleted = service.delete(1);
        assertTrue(deleted);
    }

    @Test
    void delete_notFound() {
        when(personRepository.findById((long) 5)).thenReturn(Optional.empty());
        boolean deleted = service.delete(5);
        assertFalse(deleted);
    }

}