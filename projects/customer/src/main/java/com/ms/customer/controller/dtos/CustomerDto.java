package com.ms.customer.controller.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Data
@Getter
@Setter
public class CustomerDto {
    private Long Id;
    @NotBlank(message = "First name is required")
    private String firstName;
     @NotBlank(message = "Last name is required")
    private String lastName;
}
