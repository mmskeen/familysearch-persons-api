package com.example.api3;

import com.example.api3.models.Address;
import com.example.api3.models.Person;
import com.example.api3.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Api3Application {

	public static void main(String[] args) {
		SpringApplication.run(Api3Application.class, args);
	}

	@Bean
	@Autowired
	CommandLineRunner runner(PersonRepository personRepository) {
		return (args) -> {
			long count = personRepository.count();

			if (count == 0) {
				Address address1 = new Address("123 Main St", "Provo", "UT", "84604", "USA");
				Person person1 = new Person("John", "Doe", address1);

				//
				Address address2 = new Address("789 Center St", "Salt Lake City", "UT", "84044", "USA");
				Person person2 = new Person("Jane", "Smith", address2);

				personRepository.save(person1);
				personRepository.save(person2);
			}
		};
	}

}
