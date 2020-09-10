package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Address;

public interface AddressService {

	public List<Address> getAllAddress();

	public Address getAddress(Long addressId);

	public Address saveAddress(Address address);

	public String deleteAddress(Long addressId);

	public Address updateAddress(Address address);
}
