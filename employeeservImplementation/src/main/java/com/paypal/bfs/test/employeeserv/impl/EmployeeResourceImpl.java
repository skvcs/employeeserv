package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.model.AddressJdo;
import com.paypal.bfs.test.employeeserv.model.EmployeeJdo;
import com.paypal.bfs.test.employeeserv.repo.EmployeeRepository;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	
	@Autowired
	private EmployeeRepository employeeRepo;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
    	EmployeeJdo  employeejdo =  employeeRepo.findById(Integer.parseInt(id)).get();
        
    	Employee employee = new Employee();
        employee.setId(Integer.valueOf(employeejdo.getId()));
        employee.setFirstName(employeejdo.getFirstName());
        employee.setLastName(employeejdo.getLastName());
        
        AddressJdo addressjdo = employeejdo.getAddress();
        Address address = new Address();
        address.setLine1(addressjdo.getLine1());
        address.setLine2(addressjdo.getLine2());
        address.setCity(addressjdo.getCity());
        address.setState(addressjdo.getState());
        address.setCountry(addressjdo.getCountry());
        address.setZipCode(addressjdo.getZipCode());
        employee.setAddress(address);
        employee.setDateOfBirth(employeejdo.getDate());
        
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> addEmployee(Employee employee) {
		
		// address
		Address address = employee.getAddress();
		AddressJdo addressEntity = new AddressJdo(address.getLine1(), address.getLine2(), address.getCity(),
				address.getState(), address.getCountry(), address.getZipCode());
		//validate date
		if(!isDateValid(employee.getDateOfBirth())){
		     return new ResponseEntity<>("Invalid Date", HttpStatus.BAD_REQUEST);
		}
		String errorMsg = validateRequest(employee);
		if (!StringUtils.isEmpty(errorMsg)) {
			return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
		}
		// employee
		EmployeeJdo  employeeEntity = new EmployeeJdo(employee.getFirstName(), employee.getLastName(), addressEntity, employee.getDateOfBirth());
		EmployeeJdo savedObject = null;
		try {
		// save
			savedObject = employeeRepo.save(employeeEntity);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Invalid request!!!!", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(savedObject.getId().toString(), HttpStatus.CREATED);
	}
	
	private String validateRequest(Employee employee) {
		String error = null;
		if (StringUtils.isEmpty(employee.getFirstName()) || StringUtils.isEmpty(employee.getLastName())) {
			error = "Invalid Name";
		}
		
		if (employee.getAddress() == null) {
			error = "Invalid Address";
		}
		
		Address address = employee.getAddress();
		
		if (StringUtils.isEmpty(address.getLine1()) || StringUtils.isEmpty(address.getCity())
				|| StringUtils.isEmpty(address.getCountry()) || StringUtils.isEmpty(address.getState()) || StringUtils.isEmpty(address.getZipCode())) {
			error = "Invalid Address";
		}
		
		if (address.getState().length() != STATE_SIZE || !address.getCountry().equals(COUNTRY_CODE) || address.getZipCode().length() != ZIP_CODE_SIZE) {
			error = "Invalid Address";
		}
		return error;
	}
	
	private boolean isDateValid(Date date)
	{
		if (date == null) {
			return false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.setTime(date);
		try {
		    cal.getTime();
		}
		catch (Exception e) {
		  return false;
		}

	    return date.after(new Date()) ? false : true;
	}
}
