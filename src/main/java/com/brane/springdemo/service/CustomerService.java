package com.brane.springdemo.service;

import java.util.List;

import com.brane.springdemo.model.Customer;

//we created interface with CRUD methods for CustomerServiceRestClientImpl to call CRM REST API from this SERVICE.
public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
