package com.springboot.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springweb.entity.Bird;

public interface BirdRepository extends JpaRepository<Bird, Long> {


}
