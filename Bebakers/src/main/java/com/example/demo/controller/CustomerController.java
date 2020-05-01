package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Customer;
import com.example.demo.security.User;
import com.example.demo.security.UserRepository;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserRepository userRepository;
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/api/customer")
	public List<Customer> getCustomers() {
		List<Customer> customers = customerService.getAllCustomer();
		return customers;
	}

	@GetMapping("/api/customer/{customerId}")
	public Customer getCustomer(@PathVariable(name = "customerId") Long customerId) {
		return customerService.getCustomer(customerId);
	}

	@PostMapping("/api/customer")
	public Customer saveCustomer(Customer customer) {
		return customerService.saveCustomer(customer);
	}

	@DeleteMapping("/api/customer/{customerId}")
	public String deleteCustomer(@PathVariable(name = "customerId") Long customerId) {
		return customerService.deleteCustomer(customerId);
	}

	@PutMapping("/api/customer/{customerId}")
	public void updateCustomer(@RequestBody Customer customer, @PathVariable(name = "customerId") Long customerId) {
		Customer cust = customerService.getCustomer(customerId);
		if (cust != null) {
			customerService.updateCustomer(customer);
		}
	}
}
