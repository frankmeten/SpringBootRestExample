package com.springboot.springweb.controller;

import com.springboot.springweb.entity.Sighting;
import com.springboot.springweb.repository.BirdRepository;
import com.springboot.springweb.repository.SightingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @PostMapping("/getSighting/")
    public List<Sighting> getSightings(@RequestBody Sighting sighting) {

        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        Example<Sighting> example = Example.of(sighting, matcher);
        return sightingRepo.findAll(example);
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
