package com.ms.customer.controller;

import com.ms.customer.controller.dtos.CustomerDto;
import com.ms.customer.domain.Customer;
import com.ms.customer.exceptions.ContentNotFoundException;
import com.ms.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
 import javax.validation.Valid;

@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto save(@RequestBody @Valid CustomerDto customerDto) {
        return new ModelMapper()
                .map(customerService.save(new ModelMapper().map(customerDto, Customer.class)),
                        CustomerDto.class);

    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto get(@PathVariable Long id){
        return new ModelMapper().map(customerService.findById(id)
                .orElseThrow(() -> new ContentNotFoundException(id)),
                CustomerDto.class);
    }
}
