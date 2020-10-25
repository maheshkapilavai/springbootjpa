package com.springjpa.crudops.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springjpa.crudops.exception.EmployeNotFoundException;
import com.springjpa.crudops.model.Employee;
import com.springjpa.crudops.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	//@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long id) throws EmployeNotFoundException{
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeNotFoundException("Employee not found with id:"+id));
		return ResponseEntity.ok().body(emp);
	}
	
	@RequestMapping(value = "/employees", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) throws EmployeNotFoundException{
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeNotFoundException("Employee not found with id:"+id));
		
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		
		employeeRepository.save(emp);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteEmployee(@PathVariable long id) throws EmployeNotFoundException{
		Employee emp = employeeRepository.findById(id).orElseThrow(() -> new EmployeNotFoundException("Employee not found with id:"+id));
		employeeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	

}
