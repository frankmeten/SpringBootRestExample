package com.springboot.springweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springweb.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


}
