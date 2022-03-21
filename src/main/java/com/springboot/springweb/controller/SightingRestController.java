package com.springboot.springweb.controller;

import com.springboot.springweb.entity.Sighting;
import com.springboot.springweb.exception.ResourceNotFoundException;
import com.springboot.springweb.repository.BirdRepository;
import com.springboot.springweb.repository.SightingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class SightingRestController {

    @Autowired
    SightingRepository sightingRepo;

    @Autowired
    BirdRepository birdRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(SightingRestController.class);

    @GetMapping("/sighting/")
    public List<Sighting> getAllSightings() {

        return sightingRepo.findAll();
    }

    @GetMapping("/sighting/{id}")
    public ResponseEntity<Sighting> getSighting(@PathVariable("id") long id) {
        LOGGER.info("finding sighting by id " + id);
        Sighting sighting = sightingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Sighting with id = " + id));
        return new ResponseEntity<>(sighting, HttpStatus.OK);
    }

    @GetMapping("/sighting/location/{location}")
    public ResponseEntity<List<Sighting>> getSightingByLocation(@PathVariable("location") String location) {
        LOGGER.info("finding sighting by location " + location);

        Sighting s = new Sighting();
        s.setLocation(location);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withIgnorePaths("bird_id")
                .withIgnorePaths("dataAndTime")
                .withIncludeNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.EXACT);

        Example<Sighting> example = Example.of(s, matcher);
        List<Sighting> ls = sightingRepo.findAll(example);
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }

    @GetMapping("/sighting/bird/{birdName}")
    public List<Sighting> getSightingByBirdName(@PathVariable("birdName") String birdName) {
        LOGGER.info("finding sighting by birdName " + birdName);
        return sightingRepo.findAllByBirdName(birdName);
    }

    @GetMapping("/sighting/start/{startTime}/end/{endTime}")
    public List<Sighting> getSightingBetween(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime) throws ParseException {
        LOGGER.info("finding sighting by startTime " + startTime + "endTime " + endTime);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);

        return sightingRepo.findAllBySightingTimeBetween(start, end);
    }

    @PostMapping("/sighting/")
    public Sighting createSighting(@RequestBody Sighting sighting) {
        return sightingRepo.save(sighting);
    }

    @PutMapping("/sighting/")
    public Sighting updateSighting(@RequestBody Sighting sighting) {
        return sightingRepo.save(sighting);
    }

    @DeleteMapping("/sighting/{id}")
    public void deleteSighting(@PathVariable("id") long id) {
        sightingRepo.deleteById(id);
    }
}
