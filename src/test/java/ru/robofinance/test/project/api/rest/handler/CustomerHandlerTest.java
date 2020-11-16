package ru.robofinance.test.project.api.rest.handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.robofinance.test.project.core.domain.Customer;
import ru.robofinance.test.project.core.dto.AddressDto;
import ru.robofinance.test.project.core.dto.CustomerDto;
import ru.robofinance.test.project.core.exception.CustomerNotFindException;
import ru.robofinance.test.project.core.service.CustomerService;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerHandlerTest {

    @Mock
    private CustomerService customerService;
    @InjectMocks
    private CustomerHandler customerHandler;

    @Test
    void handleCreateCustomerShouldBeOk() {
        doNothing().when(customerService).createCustomer(any());

        customerHandler.handleCreateCustomer(createCustomerDto());

        verify(customerService, times(1)).createCustomer(any());
    }

    @Test
    void handleUpdateCustomerShouldBeOk() {
        when(customerService.updateCustomer(any())).thenReturn(true);

        customerHandler.handleUpdateCustomer(createCustomerDto());

        verify(customerService, times(1)).updateCustomer(any());
    }

    @Test
    void handleUpdateCustomerShouldThrowException() {
        when(customerService.updateCustomer(any())).thenReturn(false);

        assertThrows(CustomerNotFindException.class, ()->customerHandler.handleUpdateCustomer(createCustomerDto()));
    }

    @Test
    void handleGetCustomersShouldBeOk() {
        when(customerService.findCustomers(anyString(), anyString())).thenReturn(List.of(new Customer()));

        customerHandler.handleGetCustomers("anyString", "anyString");

        verify(customerService, times(1)).findCustomers(anyString(), anyString());
    }

    private CustomerDto createCustomerDto() {
        return CustomerDto.builder()
                .id(BigInteger.valueOf(1))
                .actualAddress(createAddressDto(BigInteger.valueOf(1)))
                .registeredAddress(createAddressDto(BigInteger.valueOf(2)))
                .firstName("first-name")
                .lastName("last-name")
                .middleName("middle-name")
                .sex("male")
                .build();
    }

    private AddressDto createAddressDto(BigInteger bigInteger) {
        return AddressDto.builder()
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