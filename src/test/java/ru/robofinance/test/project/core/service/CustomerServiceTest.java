package ru.robofinance.test.project.core.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.robofinance.test.project.core.domain.Address;
import ru.robofinance.test.project.core.domain.Customer;
import ru.robofinance.test.project.core.repository.AddressRepository;
import ru.robofinance.test.project.core.repository.CustomerRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Address address;
    @Mock
    private Customer customer;
    @InjectMocks
    private CustomerService customerService;

    @Test
    void createCustomerShouldBeOk() {
        when(customerRepository.save(any())).thenReturn(customer);
        when(addressRepository.save(any())).thenReturn(address);

        customerService.createCustomer(createCustomer());

        verify(customerRepository, times(1)).save(any());
        verify(addressRepository, times(2)).save(any());
    }

    @Test
    void updateCustomerShouldBeOk() {
        when(customerRepository.findById(any())).thenReturn(java.util.Optional.of(customer));
        when(addressRepository.save(any())).thenReturn(address);

        customerService.updateCustomer(createCustomer());

        verify(customerRepository, times(1)).findById(any());
        verify(addressRepository, times(1)).save(any());
    }

    @Test
    void updateCustomerShouldNotFindCustomer() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        customerService.updateCustomer(createCustomer());

        verify(customerRepository, times(1)).findById(any());
        verify(addressRepository, times(0)).save(any());
    }

    @Test
    void findCustomersShouldBeOk() {
        when(customerRepository.findAllByFirstNameAndLastName(anyString(), anyString())).thenReturn(List.of(customer));

        customerService.findCustomers("first-name", "last-name");

        verify(customerRepository, times(1)).findAllByFirstNameAndLastName(anyString(), anyString());
    }

    private Customer createCustomer() {
        return Customer.builder()
                .id(BigInteger.valueOf(1))
                .actualAddress(createAddress(BigInteger.valueOf(1)))
                .registeredAddress(createAddress(BigInteger.valueOf(2)))
                .firstName("first-name")
                .lastName("last-name")
                .middleName("middle-name")
                .sex("male")
                .build();
    }

    private Address createAddress(BigInteger bigInteger) {
        return Address.builder()
                .id(bigInteger)
                .city("city")
                .country("country")
                .created(LocalDateTime.now())
                .flat("211")
                .house("23")
                .modified(LocalDateTime.now())
                .street("street")
                .region("region")
                .build();
    }
}