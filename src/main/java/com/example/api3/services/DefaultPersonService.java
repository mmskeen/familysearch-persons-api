package com.example.api3.services;

import com.example.api3.models.Person;
import com.example.api3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultPersonService implements PersonService {
    
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        personRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Person findOne(long id) {
        Optional<Person> person = personRepository.findById(id);
        if (!person.isPresent()) {
            return null;
        }
        return person.get();
    }

    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person update(long id, Person person) {
        if (!personRepository.findById((id)).isPresent()) {
            return null;
        }
        return personRepository.save(person);
    }

    @Override
    public boolean delete(long id) {
        if (!personRepository.findById((id)).isPresent()) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }

}
