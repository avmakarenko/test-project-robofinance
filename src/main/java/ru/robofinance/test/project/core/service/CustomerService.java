package ru.robofinance.test.project.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.robofinance.test.project.core.domain.Customer;
import ru.robofinance.test.project.core.repository.AddressRepository;
import ru.robofinance.test.project.core.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public void createCustomer(Customer customer) {
        customer.setActualAddress(addressRepository.save(customer.getActualAddress()));
        customer.setRegisteredAddress(addressRepository.save(customer.getRegisteredAddress()));
        customerRepository.save(customer);
    }

    @Transactional
    public boolean updateCustomer(Customer customer) {
        Optional<Customer> byId = customerRepository.findById(customer.getId());
        if(byId.isPresent()) {
            addressRepository.save(customer.getActualAddress());
            return true;
        }
        else return false;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Customer> findCustomers(String firstName, String secondName) {
        return customerRepository.findAllByFirstNameAndLastName(firstName, secondName);
    }
}
