package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepositroy;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepositroy customerRepositroy;
	
	public void setCustomerRepository(CustomerRepositroy customerRepositroy) {
		this.customerRepositroy = customerRepositroy;
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepositroy.findAll();
	}

	@Override
	public Customer getCustomer(Long customerId) {
		Optional<Customer> optEmp = customerRepositroy.findById(customerId);
		return optEmp.get();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepositroy.save(customer);
	}

	@Override
	public String deleteCustomer(Long customerId) {
		customerRepositroy.deleteById(customerId);
		return "Cusomer deleted succefully";
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepositroy.save(customer);
	}
}
