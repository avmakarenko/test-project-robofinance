package ru.robofinance.test.project.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "customer")
@Data
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private BigInteger id;
    @OneToOne(orphanRemoval = true, targetEntity = Address.class)
    @JoinColumn(name = "registered_address_id", referencedColumnName = "id")
    private Address registeredAddress;
    @OneToOne(orphanRemoval = true, targetEntity = Address.class)
    @JoinColumn(name = "actual_address_id", referencedColumnName = "id")
    private Address actualAddress;
    private String firstName;
    private String lastName;
    private String middleName;
    private String sex;
}
