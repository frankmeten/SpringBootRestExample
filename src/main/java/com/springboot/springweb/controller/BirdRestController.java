package com.springboot.springweb.controller;

import java.util.List;

import com.springboot.springweb.entity.Bird;
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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springweb.repository.BirdRepository;


@RestController
public class BirdRestController {

    @Autowired
    BirdRepository birdrepo;


    private static final Logger LOGGER = LoggerFactory.getLogger(BirdRestController.class);

    @GetMapping("/bird/")
    public List<Bird> getAllbirds() {

        return birdrepo.findAll();
    }

    @GetMapping("/bird/{id}")
    public Bird getbird(@PathVariable("id") long id) {
        LOGGER.info("finding product by id " + id);
        return birdrepo.findById(id).get();
    }

    @GetMapping("/bird/name/{name}")
    public List<Bird> getbirdByName(@PathVariable("name") String name) {
        LOGGER.info("finding product by name " + name);

        Bird s = new Bird();
        s.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("color")
                .withIgnorePaths("weight")
                .withIgnorePaths("height")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

//        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(s, matcher);
        return birdrepo.findAll(example);
    }

    @GetMapping("/bird/color/{color}")
    public List<Bird> getbirdByColor(@PathVariable("color") String color) {
        LOGGER.info("finding product by name " + color);

        Bird s = new Bird();
        s.setColor(color);

//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("id")
//                .withIgnorePaths("color")
//                .withIgnorePaths("weight")
//                .withIgnorePaths("height")
//                .withIncludeNullValues()
//                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(s, matcher);
        return birdrepo.findAll(example);
    }


    @PostMapping("/bird/")
    public Bird createbird(@RequestBody Bird bird) {
        return birdrepo.save(bird);
    }

    @PutMapping("/bird/")
    public Bird updatebird(@RequestBody Bird bird) {
        return birdrepo.save(bird);
    }

    @DeleteMapping("/bird/{id}")
    public void deletebird(@PathVariable("id") long id) {
        birdrepo.deleteById(id);
    }
}
