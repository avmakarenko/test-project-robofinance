package ru.robofinance.test.project.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.robofinance.test.project.api.rest.handler.CustomerHandler;
import ru.robofinance.test.project.core.dto.CustomerDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CustomerController {
    private final CustomerHandler customerHandler;

    @PostMapping(path = "customers")
    public void createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerHandler.handleCreateCustomer(customerDto);
    }

    @PutMapping(path = "customers")
    public void updateCustomer(@RequestBody CustomerDto customerDto) {
        customerHandler.handleUpdateCustomer(customerDto);
    }

    @GetMapping(path = "customers")
    public List<CustomerDto> getCustomers(@RequestParam String firstName, @RequestParam String secondName) {
        return customerHandler.handleGetCustomers(firstName, secondName);
    }
}
