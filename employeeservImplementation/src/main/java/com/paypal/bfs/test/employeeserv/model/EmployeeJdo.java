
package com.paypal.bfs.test.employeeserv.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Employee")
@Table(uniqueConstraints= {@UniqueConstraint(columnNames = {"firstName", "lastName", "dateOfBirth"})})
public class EmployeeJdo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;
  
    @Column(nullable = false)
    private String lastName;

   
    @OneToOne(cascade = CascadeType.ALL)
    private AddressJdo address;
    
    @Column(nullable = false)
    private Date dateOfBirth;
   
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
  
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    

    public EmployeeJdo() {
		super();
	}

	public EmployeeJdo(String firstName, String lastName, AddressJdo address, Date date) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.dateOfBirth = date;
	}

	public AddressJdo getAddress() {
		return address;
	}

	public void setAddress(AddressJdo address) {
		this.address = address;
	}

	public Date getDate() {
		return dateOfBirth;
	}

	public void setDate(Date date) {
		this.dateOfBirth = date;
	}

}
