package com.paypal.bfs.test.employeeserv.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.employeeserv.model.EmployeeJdo;

public interface EmployeeRepository extends JpaRepository<EmployeeJdo,Integer> {

}
