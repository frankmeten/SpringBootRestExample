package com.springboot.springweb.repository;

import com.springboot.springweb.entity.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {

    Bird findByNameAndColor(String name, String color);
}
