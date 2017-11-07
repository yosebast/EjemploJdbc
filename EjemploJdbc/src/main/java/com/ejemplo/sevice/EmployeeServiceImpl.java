package com.ejemplo.sevice;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.ejemplo.model.Employee;
import com.ejemplo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	EmployeeRepository  employeeRepo;
	
	@Override
	public List<Employee> findByNameEmployee(String name) {
		// TODO Auto-generated method stub		
		
		return employeeRepo.findByName(name);
	}

	
}
