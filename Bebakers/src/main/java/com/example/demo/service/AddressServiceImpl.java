package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addRepository;

	@Override
	public List<Address> getAllAddress() {
		return addRepository.findAll();
	}

	@Override
	public Address getAddress(Long addressId) {
		Optional<Address> optEmp = addRepository.findById(addressId);
		return optEmp.get();
	}

	@Override
	public Address saveAddress(Address address) {
		return addRepository.save(address);
	}

	@Override
	public String deleteAddress(Long addressId) {
		addRepository.deleteById(addressId);
		return "Address deleted sucessfully";
	}

	@Override
	public Address updateAddress(Address address) {
		return addRepository.save(address);
	}

}
