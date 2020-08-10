
package com.paypal.bfs.test.employeeserv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Address")
public class AddressJdo {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String line1;

    
    private String line2;

    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String state;
   
    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String zipCode;
        
    public AddressJdo() {
		super();
	}

	public AddressJdo(String line1, String line2, String city, String state, String country, String zipCode) {
		super();
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}
   
    public String getLine1() {
        return line1;
    }
  
    public void setLine1(String line1) {
        this.line1 = line1;
    }
   
    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
   
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getCountry() {
        return country;
    }
   
    public void setCountry(String country) {
        this.country = country;
    }
   
    public String getZipCode() {
        return zipCode;
    }

  
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
