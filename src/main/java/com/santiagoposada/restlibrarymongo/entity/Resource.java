package com.santiagoposada.restlibrarymongo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

//@Document
@Entity
@Data
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String category;
    private String type;
    private LocalDate lastBorrow;
    private Integer unitsOwed;
    private Integer unitsAvailable;

    public Resource() {
    }

    public Resource(Integer id, String name, String category, String type, LocalDate lastBorrow, Integer unitsOwed, Integer unitsAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.type = type;
        this.lastBorrow = lastBorrow;
        this.unitsOwed = unitsOwed;
        this.unitsAvailable = unitsAvailable;
    }

    public Resource(String name, String category, String type, LocalDate lastBorrow, Integer unitsOwed, Integer unitsAvailable) {
        this.name = name;
        this.category = category;
        this.type = type;
        this.lastBorrow = lastBorrow;
        this.unitsOwed = unitsOwed;
        this.unitsAvailable = unitsAvailable;
    }
}
