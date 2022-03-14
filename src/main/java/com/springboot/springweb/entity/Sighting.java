package com.springboot.springweb.entity;

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
    private long bird_id;
    private String location;
    private Date dataAndTime;

    public Sighting() {
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

    public Date getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(Date dataAndTime) {
        this.dataAndTime = dataAndTime;
    }
}
