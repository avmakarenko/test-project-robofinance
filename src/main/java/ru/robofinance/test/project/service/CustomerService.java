package ru.robofinance.test.project.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.robofinance.test.project.domain.Customer;
import ru.robofinance.test.project.dto.CustomerDto;
import ru.robofinance.test.project.exception.CustomerNotFindException;
import ru.robofinance.test.project.repository.AddressRepository;
import ru.robofinance.test.project.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static ru.robofinance.test.project.mapping.CustomerMapper.MAPPER;

/*
Создание клиента
Изменение фактического адреса клиента
Поиск клиента по имени и фамилии
* */
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = MAPPER.customerDtoToCustomer(customerDto);
        customer.setActualAddress(addressRepository.save(customer.getActualAddress()));
        customer.setRegisteredAddress(addressRepository.save(customer.getRegisteredAddress()));
        customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(CustomerDto customerDto) {
        Optional<Customer> byId = customerRepository.findById(customerDto.getId());
        if(byId.isPresent()) {
            addressRepository.save(MAPPER.actualAddressDtoToActualAddress(customerDto.getActualAddress()));
        }
        else throw new CustomerNotFindException();
    }

    @Transactional()
    public List<Customer> findCustomers(String firstName, String secondName) {
        return customerRepository.findAllByFirstNameAndLastName(firstName, secondName);
    }
}
