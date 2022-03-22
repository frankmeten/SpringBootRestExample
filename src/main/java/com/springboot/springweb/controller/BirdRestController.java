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

    @PostMapping("/getBird/")
    public List<Bird> getBirds(@RequestBody Bird bird) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        Example<Bird> example = Example.of(bird, matcher);
        return birdRepo.findAll(example);
    }

    @PostMapping("/bird/")
    public Bird createBird(@RequestBody Bird bird) {
        return birdRepo.save(bird);
    }

    @PutMapping("/bird/")
    public Bird updateBird(@RequestBody Bird bird) {
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
