package ru.robofinance.test.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.robofinance.test.project.domain.Customer;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, BigInteger> {

    List<Customer> findAllByFirstNameAndLastName(String firstName, String lastName);
}
