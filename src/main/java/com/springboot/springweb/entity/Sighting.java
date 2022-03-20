package com.springboot.springweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.Date;

@Entity
@EnableAutoConfiguration
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueBirdAndLocationAndDateTime", columnNames = {"birdId", "location", "sightingTime"})})
public class Sighting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @OneToOne(cascade = CascadeType.ALL)
    @OneToOne(cascade = CascadeType.MERGE)
//    @Column(unique = true)
//    @Embedded
    @JoinColumn(name = "birdId", referencedColumnName = "id")
//    @JoinColumn(name = "bird_id")
//    @JoinColumn(name = "birdId")
    private Bird bird;

    @Column(columnDefinition = "varchar(80)")
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

    public Bird getBird() {
        return bird;
    }

    public void setBird(Bird bird) {
        this.bird = bird;
    }
}
