package ru.robofinance.test.project.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "address")
@Data
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String flat;
    private LocalDateTime created;
    private LocalDateTime modified;
}

