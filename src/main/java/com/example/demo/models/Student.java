package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sid;
    private String name;
    private int weight;
    private int height;
    private String hair_colour;
    private double gpa;

    public Student () {
        
    }

    public Student(String name, int weight, int height, String hair_colour, double gpa) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hair_colour = hair_colour;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getHair_colour() {
        return hair_colour;
    }
    public void setHair_colour(String hair_colour) {
        this.hair_colour = hair_colour;
    }
    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    
    
}
