package com.springboot.springweb.repository;

import com.springboot.springweb.entity.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightingRepository extends JpaRepository<Sighting, Long> {


}
