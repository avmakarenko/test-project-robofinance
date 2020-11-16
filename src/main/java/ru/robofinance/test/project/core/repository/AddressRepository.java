package ru.robofinance.test.project.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.robofinance.test.project.core.domain.Address;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<Address, BigInteger> {
    List<Address> findAll();
}
