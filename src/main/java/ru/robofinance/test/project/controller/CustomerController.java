package ru.robofinance.test.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.robofinance.test.project.domain.Customer;
import ru.robofinance.test.project.dto.CustomerDto;
import ru.robofinance.test.project.service.CustomerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping(path = "customers")
    public void createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
    }

    @PutMapping(path = "customers")
    public void updateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(customerDto);
    }

    @GetMapping(path = "customers")
    public List<Customer> getCustomers(@RequestParam String firstName, @RequestParam String secondName) {
        return customerService.findCustomers(firstName, secondName);
    }
}
