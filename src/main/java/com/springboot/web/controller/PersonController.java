package com.springboot.web.controller;

import com.springboot.web.Service.PersonService;
//import com.springboot.web.dao.PersonRepository;
import com.springboot.web.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/getAllPerson")
    public ResponseEntity<List<Person>> getAll(){
        List<Person> allPerson = personService.getAllPerson();
        return new ResponseEntity<>(allPerson,HttpStatus.OK);
    }

    @GetMapping("/getPerson/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id){
        Person personById = personService.getPersonById(id);
        return new ResponseEntity<>(personById,HttpStatus.OK);
    }

    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        Person savePerson = personService.addPerson(person);
        return new ResponseEntity<>(savePerson,HttpStatus.CREATED);
    }

    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id,@RequestBody Person person){
        Person updatePerson = personService.updatePerson(id, person);
        return new ResponseEntity<>(updatePerson,HttpStatus.OK);
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable int id){
        String Message=personService.deletedPerson(id);
        return new ResponseEntity<>(Message,HttpStatus.NO_CONTENT);
    }
}
