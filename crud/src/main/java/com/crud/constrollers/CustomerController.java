package com.crud.constrollers;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.models.Customer;
import com.crud.models.exceptions.CustomerNotFoundException;
import com.crud.services.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/customers") 
	public List<Customer> getCustomers() throws ParseException {
		return customerService.getAllCustomers();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable long id) {
		Optional<Customer> customer = customerService.getCustomer(id);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("id-" + id);
		}
		return customer.get();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/customers/{id}")  
	public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
		
		try {
			customerService.removeCustomer(id);
			return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/customers/{id}") 
	public Customer editCustomer(@PathVariable long id, @RequestBody Customer customer) { 
		customer.setId(id);
		customerService.removeCustomer(id); 
		return customerService.updateCustomer(customer); 
	}
	 
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/customers") 
	public long addCustomer(@RequestBody Customer customer) {
		return customerService.updateCustomer(customer).getId();
	}
}
