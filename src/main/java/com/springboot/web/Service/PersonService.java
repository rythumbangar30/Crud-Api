package com.springboot.web.Service;

import com.springboot.web.dao.PersonRepository;
import com.springboot.web.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;

    public Person addPerson(Person person){
       return repository.save(person);
    }

    public List<Person> getAllPerson(){
        return repository.findAll();
    }

    public Person getPersonById(int id){
        Optional<Person> personFromDb = repository.findById(id);
        if( personFromDb.isPresent()){
            return personFromDb.get();
        }
        return null;
    }

    public Person updatePerson(int id,Person person){
        Optional<Person> personFromDb = repository.findById(id);
        if(personFromDb.isPresent()){
          Person updatePerson=personFromDb.get();
          updatePerson.setName(person.getName());
          updatePerson.setDept(person.getDept());
          updatePerson.setSalary(person.getSalary());
          return repository.save(updatePerson);
        }
        return null;
    }

    public String deletedPerson(int id){
        Optional<Person> personFromDb = repository.findById(id);
        if(personFromDb.isPresent()){
            repository.deleteById(id);
            return "Person got Deleted";
        }
        return "Person not Deleted";
    }
}
