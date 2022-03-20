package com.springboot.springweb.controller;

import com.springboot.springweb.entity.Bird;
import com.springboot.springweb.repository.BirdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BirdRestController {

    @Autowired
    BirdRepository birdRepo;


    private static final Logger LOGGER = LoggerFactory.getLogger(BirdRestController.class);

    @GetMapping("/bird/")
    public List<Bird> getAllBirds() {

        return birdRepo.findAll();
    }

    @GetMapping("/bird/{id}")
    public Bird getBird(@PathVariable("id") long id) {
        LOGGER.info("finding bird by id " + id);
        return birdRepo.findById(id).get();
    }

    @GetMapping("/bird/name/{name}")
    public List<Bird> getBirdByName(@PathVariable("name") String name) {
        LOGGER.info("finding bird by name " + name);

        Bird bird = new Bird();
        bird.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(bird, matcher);
        return birdRepo.findAll(example);
    }

    @GetMapping("/bird/color/{color}")
    public List<Bird> getBirdByColor(@PathVariable("color") String color) {
        LOGGER.info("finding bird by color " + color);

        Bird bird = new Bird();
        bird.setColor(color);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Bird> example = Example.of(bird, matcher);
        return birdRepo.findAll(example);
    }

    @GetMapping("/bird/name/{name}/color/{color}")
    public Bird getBirdByNameColor(@PathVariable("name") String name, @PathVariable("color") String color) {
        LOGGER.info("finding bird by name " + name + "by color " + color);

        return birdRepo.findByNameAndColor(name, color);
    }


    @PostMapping("/bird/")
    public Bird createBird(@RequestBody Bird bird) {
        return birdRepo.save(bird);
    }

    @PutMapping("/bird/")
    public Bird updateBird(@RequestBody Bird bird) {

        LOGGER.info("update bird :");
        LOGGER.info("update bird : [name " + bird.getName() + "],[color: " + bird.getColor() + "]");
        LOGGER.info("update bird : [weight " + bird.getWeight() + "],[height: " + bird.getHeight() + "]");

        Bird newBird = birdRepo.findByNameAndColor(bird.getName(), bird.getColor());
        newBird.setWeight(bird.getWeight());
        newBird.setHeight(bird.getHeight());

        return birdRepo.save(newBird);
    }

    @DeleteMapping("/bird/{id}")
    public void deleteBird(@PathVariable("id") long id) {
        birdRepo.deleteById(id);
    }
}
