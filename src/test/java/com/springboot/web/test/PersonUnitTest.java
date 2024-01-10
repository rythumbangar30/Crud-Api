package com.springboot.web.test;

import com.springboot.web.Service.PersonService;
import com.springboot.web.dao.PersonRepository;
import com.springboot.web.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonUnitTest {
    @Mock
    static PersonRepository personRepository;

    @InjectMocks
    PersonService personService;


    @Test
    void testAddPerson() {
        // Given
        Person personToAdd=new Person();
        personToAdd.setName("Cream");
        personToAdd.setDept("IT");
        personToAdd.setSalary(20000);
        Mockito.when(personRepository.save(personToAdd)).thenReturn(personToAdd);

        // When
        Person addedPerson = personService.addPerson(personToAdd);

        // Then
//        assertEquals(personToAdd, addedPerson);
    }
//    private static Person user(){
//       Person person=new Person();
//       person.setName("Cream");
//       person.setDept("IT");
//       person.setSalary(20000);
//       personRepository.save(person);
//    }
}
