package ru.robofinance.test.project.api.rest.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.robofinance.test.project.core.domain.Customer;
import ru.robofinance.test.project.core.dto.CustomerDto;
import ru.robofinance.test.project.core.exception.CustomerNotFindException;
import ru.robofinance.test.project.core.service.CustomerService;

import java.util.List;

import static ru.robofinance.test.project.api.rest.mapping.CustomerMapper.MAPPER;

@Component
@RequiredArgsConstructor
public class CustomerHandler {
    private final CustomerService customerService;

    public void handleCreateCustomer(CustomerDto customerDto) {
        customerService.createCustomer(MAPPER.customerDtoToCustomer(customerDto));
    }

    public void handleUpdateCustomer(CustomerDto customerDto) {
        if(!customerService.updateCustomer(MAPPER.customerDtoToCustomer(customerDto)))
            throw new CustomerNotFindException();
    }

    public List<CustomerDto> handleGetCustomers(String firstName, String secondName) {
        List<Customer> customers = customerService.findCustomers(firstName, secondName);
        return MAPPER.customersToCustomerDtos(customers);
    }
}
