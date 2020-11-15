package ru.robofinance.test.project.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@Builder
public class CustomerDto implements Serializable {
    private BigInteger id;
    private AddressDto registeredAddress;
    private AddressDto actualAddress;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
}
