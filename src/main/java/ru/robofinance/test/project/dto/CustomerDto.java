package ru.robofinance.test.project.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private BigInteger id;
    private AddressDto registeredAddress;
    private AddressDto actualAddress;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
}
