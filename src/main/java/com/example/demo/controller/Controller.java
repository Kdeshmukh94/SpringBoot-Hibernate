package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.model.Employee;

@RestController
@RequestMapping("/springBoot-orm")
public class Controller {
	
	@Autowired
	EmployeeDao dao;
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		dao.saveEmployee(employee);
		
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	@PutMapping("/saveEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		
		Employee emp = dao.updateEmployee(id, employee);
		
		if(emp!=null) {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} else {
			 throw new EmployeeNotFoundException("Employee Not Found for ID: "+id);
		}	
	}
	
	@GetMapping("/getAll")
	public List<Employee> getEmployeeList(){
		return dao.getEmployees();
	}

}
