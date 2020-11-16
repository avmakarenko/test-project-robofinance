package ru.robofinance.test.project.api.rest.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.robofinance.test.project.core.domain.Address;
import ru.robofinance.test.project.core.domain.Customer;
import ru.robofinance.test.project.core.dto.AddressDto;
import ru.robofinance.test.project.core.dto.CustomerDto;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer customerDtoToCustomer(CustomerDto customerDto);

    Address actualAddressDtoToActualAddress(AddressDto addressDto);

    CustomerDto customerToCustomerDto(Customer customer);
    List<CustomerDto> customersToCustomerDtos(List<Customer> customers);
}
