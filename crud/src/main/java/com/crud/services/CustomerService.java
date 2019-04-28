package com.crud.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.crud.models.Customer;

@Service
public class CustomerService{

	private static List<Customer> customers = new ArrayList<>() ;
	private final static AtomicLong counter = new AtomicLong();
	private static Boolean firstRun = true;

	private static Long getNewId(){
		return counter.incrementAndGet();
	}
	
	public void generateCustomers() {
		customers.add(new Customer(getNewId(),"Igor","Nanaev","860857148","none@gmai.com"));
		customers.add(new Customer(getNewId(),"Anton","Isaev","+3706682147","antanasss@one.lt"));
		customers.add(new Customer(getNewId(),"Kiril","Nevazno","+3704741835","lol@yandex.ru"));
		customers.add(new Customer(getNewId(),"Petras","Steponaitis","865924585","Antanas@one.lt"));
		customers.add(new Customer(getNewId(),"Saulius","Naujokaitis","8625471","snaujokaitis@mail.com"));
		customers.add(new Customer(getNewId(),"German","Budnik","+370586248","german.budnik@gamil.com"));
	}
	
	public List<Customer> getAllCustomers() {
		if(firstRun) {
			generateCustomers();
			firstRun = Boolean.FALSE;
		}
		return customers;
	}
	
	public Customer updateCustomer(Customer customer) {
		if(customer.getId() == null) {
			customer.setId(getNewId());
		}else {
			removeCustomer(customer.getId());
		}
		customers.add(customer);
		return  customer;
	}

	public Optional<Customer> getCustomer(Long customerId) {
		return  customers.stream().filter(p -> p.getId() == customerId).findFirst();
		}

	public void removeCustomer(Long customerId) {
		for(Iterator<Customer> iter = customers.listIterator(); iter.hasNext();) {
			Customer customer = iter.next();
			if(customer.getId() == customerId) {
				iter.remove();
				break;
			}
		}
	}
	
}