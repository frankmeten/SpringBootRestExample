package com.springboot.springweb.repository;

import com.springboot.springweb.entity.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SightingRepository extends JpaRepository<Sighting, Long> {

    List<Sighting> findAllBySightingTimeBetween(Date publicationTimeStart, Date publicationTimeEnd);

    @Query(value = "SELECT s FROM Sighting s join Bird b on s.bird = b.id  where b.name =:birdName")
    List<Sighting> findAllByBirdName(String birdName);

}
