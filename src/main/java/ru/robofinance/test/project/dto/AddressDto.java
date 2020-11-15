package ru.robofinance.test.project.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
public class AddressDto implements Serializable {
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
