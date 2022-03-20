package com.springboot.springweb.repository;

import com.springboot.springweb.entity.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Long> {


    List<Sighting> findAllBySightingTimeBetween(
            Date publicationTimeStart,
            Date publicationTimeEnd);
}
