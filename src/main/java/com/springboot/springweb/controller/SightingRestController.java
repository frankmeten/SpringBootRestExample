package com.springboot.springweb.controller;

import com.springboot.springweb.entity.Sighting;
import com.springboot.springweb.repository.SightingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public Sighting getsighting(@PathVariable("id") long id) {
        LOGGER.info("finding product by id " + id);
        return sightingrepo.findById(id).get();
    }

    @GetMapping("/sighting/name/{name}")
    public List<Sighting> getsightingByName(@PathVariable("name") String location) {
        LOGGER.info("finding product by location " + location);

        Sighting s = new Sighting();
        s.setLocation(location);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("color")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

//        ExampleMatcher matcher = ExampleMatcher.matchingAny();

        Example<Sighting> example = Example.of(s, matcher);
        return sightingrepo.findAll(example);
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
