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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

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
        personToAdd.setId(1);
        personToAdd.setName("Cream");
        personToAdd.setDept("IT");
        personToAdd.setSalary(20000);
        Mockito.when(personRepository.save(personToAdd)).thenReturn(personToAdd);


        // When
        Person personFromDb = personService.addPerson(personToAdd);

        // Then
        assertEquals(personToAdd, personFromDb);
        assertEquals(personToAdd.getName(),personFromDb.getName());

        verify(personRepository,Mockito.times(1)).save(personFromDb);
//        doReturn(1).when(personRepository);
    }

    @Test
    void getAllPerson(){
        Person personToAdd=new Person();
        personToAdd.setId(1);
        personToAdd.setName("Cream");
        personToAdd.setDept("IT");
        personToAdd.setSalary(20000);
//        personRepository.save(personToAdd);

        Person person = new Person();
        person.setId(2);
        person.setName("Prem");
        person.setDept("MBA");
        person.setSalary(20000);
//        personRepository.save(person);



        List<Person> list=new ArrayList<>();
        list.add(personToAdd);
        list.add(person);
        System.out.println(list);
        lenient().when(personRepository.findAll()).thenReturn(list);
        List<Person> allPerson=personService.getAllPerson();

        assertEquals(allPerson.size(),list.size());
//        verify(personRepository,times(1)).save(person);
//        verify(personRepository,times(1)).save(personToAdd);

    }
    @Test
    void getPersonById(){
        Person person = new Person();
        person.setId(1);
        person.setName("Prem");
        person.setDept("MBA");
        person.setSalary(20000);
//        personRepository.save(person);
        System.out.println(person);

        Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(person));
        Person personDb=personService.getPersonById(1);

        assertEquals(personDb,person);

        verify(personRepository,times(1)).findById(1);

    }
    @Test
    void updatePerson(){
        Person person = new Person();
        person.setId(1);
        person.setName("Prem");
        person.setDept("MBA");
        person.setSalary(20000);
//        personRepository.save(person);

        Person updatePerson = new Person();
        updatePerson.setId(1);
        updatePerson.setName("Bhim");
        updatePerson.setDept("PM");
        updatePerson.setSalary(10000);
//        personRepository.save(person);

        Optional<Person> optionalEntityType = Optional.of(person);
        lenient().when(personRepository.findById(updatePerson.getId())).thenReturn(optionalEntityType);
        lenient().when(personRepository.save(updatePerson)).thenReturn(updatePerson);
        Person result=personService.updatePerson(person.getId(), updatePerson);

        assertEquals("PM",result.getDept());
        verify(personRepository,times(1)).save(any(Person.class));

    }

    @Test
    void deletePerson(){
        Person person = new Person();
        person.setId(1);
        person.setName("Prem");
        person.setDept("MBA");
        person.setSalary(20000);
        personRepository.save(person);
        Optional<Person> optionalEntityType = Optional.of(person);


        Mockito.when(personRepository.findById(person.getId())).thenReturn(optionalEntityType);

        String message=personService.deletedPerson(person.getId());

        assertEquals("Person got Deleted",message);
        verify(personRepository,times(1)).deleteById(person.getId());
//        assertThat(personRepository.findById(person.getId()).get()).isNull();
    }
//    private static Person user(){
//       Person person=new Person();
//       person.setName("Cream");
//       person.setDept("IT");
//       person.setSalary(20000);
//       personRepository.save(person);
//    }
}
