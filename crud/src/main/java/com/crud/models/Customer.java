package com.crud.models;

import java.util.Date;
import java.util.Objects;

public class Customer {

	private Long id;
    private String firstName; 
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String email;

    public Customer() {};
    
	public Customer(Long id, String firstName, String lastName, Date birthday, String phoneNumber, String email){
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName; 
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return (Date) birthday.clone();
	}

	public void setBirthday(Date birthday) {
		this.birthday = (Date) birthday.clone();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
        if (!(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        return customer.id.equals(this.id);
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash(this.id);
	    }
}
