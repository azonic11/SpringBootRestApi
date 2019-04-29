package com.crud.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.crud.models.Customer;

@Service
public class CustomerService{

	private final static String DATE_PATTERN = "dd-MM-yyyy";
	private static Set<Customer> customers = new HashSet<>() ;
	private final static AtomicLong counter = new AtomicLong();
	private static Boolean firstRun = true;

	private static Long getNewId(){
		return counter.incrementAndGet();
	}
	
	public void generateCustomers() throws ParseException {
		customers.add(new Customer(getNewId(),"Igor","Nanaev",new SimpleDateFormat(DATE_PATTERN).parse("20-05-1977"),"860857148","none@gmai.com"));
		customers.add(new Customer(getNewId(),"Anton","Isaev",new SimpleDateFormat(DATE_PATTERN).parse("01-01-1965"),"+3706682147","antanasss@one.lt"));
		customers.add(new Customer(getNewId(),"Kiril","Nevazno",new SimpleDateFormat(DATE_PATTERN).parse("10-07-2001"),"+3704741835","lol@yandex.ru"));
		customers.add(new Customer(getNewId(),"Petras","Steponaitis",new SimpleDateFormat(DATE_PATTERN).parse("24-09-1998"),"865924585","Antanas@one.lt"));
		customers.add(new Customer(getNewId(),"Saulius","Naujokaitis",new SimpleDateFormat(DATE_PATTERN).parse("27-10-1999"),"8625471","snaujokaitis@mail.com"));
		customers.add(new Customer(getNewId(),"German","Budnik",new SimpleDateFormat(DATE_PATTERN).parse("20-04-1975"),"+370586248","german.budnik@gamil.com"));
	}
	
	public List<Customer> getAllCustomers() throws ParseException {
		if(firstRun) {
			generateCustomers();
			firstRun = Boolean.FALSE;
		}
		return new ArrayList<>(customers);
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
		for(Iterator<Customer> iter = customers.iterator(); iter.hasNext();) {
			Customer customer = iter.next();
			if(customer.getId() == customerId) {
				iter.remove();
				break;
			}
		}
	}
	
}