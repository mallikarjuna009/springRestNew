package com.cms.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cms.customer.model.Customer;
import com.cms.customer.service.CustomerService;


@RestController
@RequestMapping("/api")
public class CustomerController {
	
	
	static {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Customer>> getAllCustomers(Model model) {

		List<Customer> listOfCustomers = customerService.getAllCustomers();
	/*	model.addAttribute("customer", new Customer());
		model.addAttribute("listOfCustomers", listOfCustomers);*/
		System.out.println("method aclled44");
	        if (listOfCustomers.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Customer>>(listOfCustomers, HttpStatus.OK);
		
	
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public String goToHomePage() {
		return "redirect:/getAllCustomers";
	}
	
	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) {
		System.out.println("method aclled");
	Customer customer=customerService.getCustomer(id);
	  if (customer==null) {
          return new ResponseEntity(HttpStatus.NO_CONTENT);
          // You many decide to return HttpStatus.NOT_FOUND
      }
      return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCustomer(@RequestBody Customer customer) {	
		if(customer.getId()==0)
		{
			customerService.addCustomer(customer);
		}
		else
		{	
			customerService.updateCustomer(customer);
		}

		return "storedData";
	}



	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
		return "deleted data";

	}	
}
