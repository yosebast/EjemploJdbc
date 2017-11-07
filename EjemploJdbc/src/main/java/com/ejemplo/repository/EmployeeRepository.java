package com.ejemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ejemplo.model.Employee;
import java.lang.String;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByName(String name);

}
