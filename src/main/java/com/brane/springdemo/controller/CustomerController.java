package com.brane.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brane.springdemo.model.Customer;
import com.brane.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need to inject our customerService object
	@Autowired
	private CustomerService customerService;
	
	
	//making request mapping /list and then we will store list of objects into attribute model called customers.
	//This model we will use in our JSP PAGE to show all data in table with for each loop.
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//delegate calls from controller to service layer
		//get customers from db
		List<Customer> theCustomers = customerService.getCustomers();
				
		//add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		//we'll return list-customers.jsp
		return "list-customers";
	}
	

	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer = new Customer();
		
		//create model attribute to bind form data
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	

	@PostMapping("/saveCustomer")
	//data binding using model attribute customer from FORM
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//save the customer object-delegate calls to the service layer
		customerService.saveCustomer(theCustomer);	
		
		//use redirect to prevent duplicate submissions.
		//we are using here POST-REDIRECT-GET PATTERN(PRG)
		return "redirect:/customer/list";
	}
	

	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {
		
		//get the customer from the service layer
		Customer theCustomer = customerService.getCustomer(theId);	
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	

	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete an employee from the service layer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
	}
}










