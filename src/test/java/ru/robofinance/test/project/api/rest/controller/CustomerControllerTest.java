package ru.robofinance.test.project.api.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.robofinance.test.project.api.rest.handler.CustomerHandler;
import ru.robofinance.test.project.core.dto.AddressDto;
import ru.robofinance.test.project.core.dto.CustomerDto;
import ru.robofinance.test.project.core.exception.CustomerNotFindException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerHandler customerHandler;

    @Test
    void createCustomerShouldReturn200() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCustomerDto("male"))))
                .andExpect(status().isOk());
    }

    @Test
    void createCustomerShouldReturn400() throws Exception {
        mockMvc.perform(post("/api/v1/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCustomerDto("anyStr"))))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getCustomersShouldReturn404() throws Exception {
        mockMvc.perform(get("/api/v1/customers","anyStr", "anyStr")
                .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getCustomersShouldReturn200() throws Exception {
        when(customerHandler.handleGetCustomers(anyString(), anyString())).thenReturn(List.of(createCustomerDto("male")));

        mockMvc.perform(get("/api/v1/customers")
                .param("firstName", "first-name")
                .param("secondName", "last-name")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void putCustomersShouldReturn404() throws Exception {
        doThrow(new CustomerNotFindException()).when(customerHandler).handleUpdateCustomer(any());

        mockMvc.perform(put("/api/v1/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCustomerDto("anyStr"))))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void putCustomersShouldReturn200() throws Exception {
        doNothing().when(customerHandler).handleUpdateCustomer(any());

        mockMvc.perform(put("/api/v1/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCustomerDto("anyStr"))))
                .andExpect(status().isOk());
    }

    private CustomerDto createCustomerDto(String sex) {
        return CustomerDto.builder()
                .id(BigInteger.valueOf(1))
                .actualAddress(createAddressDto(BigInteger.valueOf(1)))
                .registeredAddress(createAddressDto(BigInteger.valueOf(2)))
                .firstName("first-name")
                .lastName("last-name")
                .middleName("middle-name")
                .sex(sex)
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
