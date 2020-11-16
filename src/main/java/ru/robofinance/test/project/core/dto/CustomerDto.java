package ru.robofinance.test.project.core.dto;

import lombok.*;

import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "male|female")
    private String sex;
}
