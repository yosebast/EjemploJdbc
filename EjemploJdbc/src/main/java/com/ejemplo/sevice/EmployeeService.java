package com.ejemplo.sevice;

import java.util.List;

import com.ejemplo.model.Employee;

public interface EmployeeService {

	List<Employee> findByNameEmployee(String name);
	
}
