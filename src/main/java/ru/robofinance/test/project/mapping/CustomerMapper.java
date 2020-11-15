package ru.robofinance.test.project.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.robofinance.test.project.domain.Address;
import ru.robofinance.test.project.domain.Customer;
import ru.robofinance.test.project.dto.AddressDto;
import ru.robofinance.test.project.dto.CustomerDto;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    Customer customerDtoToCustomer(CustomerDto customerDto);

    Address actualAddressDtoToActualAddress(AddressDto addressDto);
}
