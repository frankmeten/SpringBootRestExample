package com.springboot.springweb.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springweb.entity.Student;
import com.springboot.springweb.repository.StudentRepository;


@RestController
public class StudentRestController {

    @Autowired
    StudentRepository studrepo;


    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRestController.class);

    @GetMapping("/student/")
    public List<Student> getAllStudents() {

        return studrepo.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        LOGGER.info("finding product by id " + id);
        return studrepo.findById(id).get();
    }

    @GetMapping("/student/name/{name}")
    public List<Student> getStudent(@PathVariable("name") String name) {
        LOGGER.info("finding product by name " + name);

        Student s = new Student();
        s.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("testscore")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);


//        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Student> example = Example.of(s, matcher);
//		return studrepo.findById(id).get();
//		return studrepo.findAll(example).get(0);
//		return studrepo.findOne(example).get();

        return studrepo.findAll(example);
//
//        Optional<Student> actual = studrepo.findOne(example);
//
//        if (actual.isPresent()) {
////            return studrepo.findOne(example).get();
//            return studrepo.findAll(example).get(0);
//        } else {
//            return null;
//        }


    }


    @PostMapping("/student/")
    public Student createStudent(@RequestBody Student student) {
        return studrepo.save(student);
    }

    @PutMapping("/student/")
    public Student updateStudent(@RequestBody Student student) {
        return studrepo.save(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") long id) {
        studrepo.deleteById(id);
    }
}
