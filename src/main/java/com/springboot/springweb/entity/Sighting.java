package com.springboot.springweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@EnableAutoConfiguration
public class Sighting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long bird_id;
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private Date sightingTime;

    public Sighting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getBird_id() {
        return bird_id;
    }

    public void setBird_id(long bird_id) {
        this.bird_id = bird_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getSightingTime() {
        return sightingTime;
    }

    public void setSightingTime(Date dataAndTime) {
        this.sightingTime = dataAndTime;
    }


}
