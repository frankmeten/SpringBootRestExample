package com.springboot.springweb.controller;

import com.springboot.springweb.entity.Sighting;
import com.springboot.springweb.exception.ResourceNotFoundException;
import com.springboot.springweb.repository.SightingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SightingRestController {

    @Autowired
    SightingRepository sightingrepo;


    private static final Logger LOGGER = LoggerFactory.getLogger(SightingRestController.class);

    @GetMapping("/sighting/")
    public List<Sighting> getAllsightings() {

        return sightingrepo.findAll();
    }

    @GetMapping("/sighting/{id}")
    public ResponseEntity<Sighting> getsighting(@PathVariable("id") long id) {
        LOGGER.info("finding product by id " + id);
        Sighting sighting = sightingrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Sighting with id = " + id));
        return new ResponseEntity<>(sighting, HttpStatus.OK);
    }

    @GetMapping("/sighting/location/{location}")
    public ResponseEntity<List<Sighting>> getsightingByLocation(@PathVariable("location") String location) {
        LOGGER.info("finding product by location " + location);

        Sighting s = new Sighting();
        s.setLocation(location);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("bird_id")
                .withIgnorePaths("dataAndTime")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

//        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Sighting> example = Example.of(s, matcher);
        List<Sighting> ls = sightingrepo.findAll(example);
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }

    @GetMapping("/sighting/birdid/{birdid}")
    public List<Sighting> getsightingByColor(@PathVariable("birdid") long birdid) {
        LOGGER.info("finding product by birdid " + birdid);

        Sighting sighting = new Sighting();
        sighting.setBird_id(birdid);

        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Sighting> example = Example.of(sighting, matcher);
        return sightingrepo.findAll(example);
    }


    @PostMapping("/sighting/")
    public Sighting createsighting(@RequestBody Sighting sighting) {
        return sightingrepo.save(sighting);
    }

    @PutMapping("/sighting/")
    public Sighting updatesighting(@RequestBody Sighting sighting) {
        return sightingrepo.save(sighting);
    }

    @DeleteMapping("/sighting/{id}")
    public void deletesighting(@PathVariable("id") long id) {
        sightingrepo.deleteById(id);
    }
}
