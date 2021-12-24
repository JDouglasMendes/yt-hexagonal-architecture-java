package com.ms.customer;

import com.ms.customer.controller.CustomerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CustomerApplicationTests {
	@Autowired
	private CustomerController customerController;
	@Test
	void contextLoads() {
		assertThat(customerController).isNotNull();
	}

}
