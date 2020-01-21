package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Customer;

public interface ICustomerRepo extends MongoRepository<Customer, String>{
	public void deleteBycustomerId(String customerId);
	public Customer findBycustomerId(String customerId);
}
