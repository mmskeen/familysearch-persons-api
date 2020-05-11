package com.example.api3.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "persons", uniqueConstraints={@UniqueConstraint(columnNames ={"lcFirstName","lcLastName"})})
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "firstName", length = 100, nullable = false)
    private String firstName;
    
    @Column(name = "lastName", length = 100, nullable = false)
    private String lastName;

    @Column(name="address", nullable = false)
    private Address address;

    @Column(nullable = false)
    private String lcFirstName;

    @Column(nullable = false)
    private String lcLastName;

    public Person(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.lcFirstName = firstName.toLowerCase();
        this.lcLastName = lastName.toLowerCase();
    }

    public Person() {
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.lcFirstName = firstName.toLowerCase();
    }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.lcLastName = lastName.toLowerCase();
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

}
