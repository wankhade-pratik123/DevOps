package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping
	public List<Address> getAddress() {
		List<Address> customers = addressService.getAllAddress();
		return customers;
	}

	@GetMapping("/{addressId}")
	public Address getAddress(@PathVariable(name = "addressId") Long addressId) {
		return addressService.getAddress(addressId);
	}

	@PostMapping
	public Address saveAddress(Address address) {
		return addressService.saveAddress(address);
	}

	@DeleteMapping("/{addressId}")
	public String deleteAddress(@PathVariable(name = "addressId") Long addressId) {
		return addressService.deleteAddress(addressId);
	}

	@PutMapping("/{addressId}")
	public void updateAddress(@RequestBody Address address, @PathVariable(name = "addressId") Long addressId) {
		Address add = addressService.getAddress(addressId);
		if (add != null) {
			addressService.updateAddress(address);
		}
	}
}
