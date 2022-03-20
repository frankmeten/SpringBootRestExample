package com.springboot.springweb.entity;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Table(uniqueConstraints = {@UniqueConstraint(name = "UniqueNameAndColor", columnNames = {"name", "color"})})
public class Bird {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(30)")
    private String name;
    @Column(columnDefinition = "varchar(20)")
    private String color;
    private double weight;
    private double height;

    @OneToOne(mappedBy = "bird")
    private Sighting sighting;


    public Bird() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
