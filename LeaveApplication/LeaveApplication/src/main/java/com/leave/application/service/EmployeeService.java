package com.leave.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import  com.leave.application.repository.EmployeeRepository;
import  com.leave.application.repository.EmpLeavesRepository;
import com.leave.application.models.EmpLeaves;
import  com.leave.application.models.Employee;
import com.leave.application.exception.ResourceNotFoundException;

@Service
public class EmployeeService {
	
	@Autowired
    EmployeeRepository EmployeeRepository;
	
	@Autowired
    EmpLeavesRepository EmpLeavesRepository;
	
	//GET ALL EMPLOYEES
	public List<Employee> getEmployees() {
        //return EmployeeRepository.findAll();
        List<Employee> employee = new ArrayList<>();
		EmployeeRepository.findAll().forEach(employee::add);
		return employee;
    }
	
	//Get employees by id
	public Optional<Employee> getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        if (!EmployeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " not found");
        }
        return EmployeeRepository.findById(employeeId);
    }
	
	//Create a New employee
	public Employee createEmployee(Employee employee) {
        return EmployeeRepository.save(employee);

    }
	
	//Update employee
	public Employee updateEmployeeById(Long employeeId, Employee EmployeeRequest) throws ResourceNotFoundException {
        if (!EmployeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " not found");
        }
        Optional<Employee> Employee = EmployeeRepository.findById(employeeId);

        if (!Employee.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " not found");
        }
        
    	Employee Employee1 = Employee.get();
    	
    	if(EmployeeRequest.getFirstName()!=null)
         {
    		Employee1.setFirstName(EmployeeRequest.getFirstName());
         }
    	if(EmployeeRequest.getLastName()!=null)
        {
    		Employee1.setLastName(EmployeeRequest.getLastName());
        }
    	if(EmployeeRequest.getEmailId()!=null)
        {
    		Employee1.setEmailId(EmployeeRequest.getEmailId());
        }
    	if(EmployeeRequest.getDesignation()!=null)
        {
    		Employee1.setDesignation(EmployeeRequest.getDesignation());
        }
    	if(EmployeeRequest.getPhoneNumber()!=null)
        {
    		Employee1.setPhoneNumber(EmployeeRequest.getPhoneNumber());
        }
        
        return EmployeeRepository.save(Employee1);

    }
	
	//Delete an employee
	public ResponseEntity<Object> deleteEmployeeById(long employeeId) throws ResourceNotFoundException {
        if (!EmployeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " not found");
        }

        Optional<Employee> byId = EmployeeRepository.findById(employeeId);
        Employee employee = byId.get();
        Set<EmpLeaves> leaves = employee.getEmpLeaves();
        for(EmpLeaves empLeaves : leaves){
        	EmpLeavesRepository.deleteById(empLeaves.getId());
        	}
        EmployeeRepository.deleteById(employeeId);

        return ResponseEntity.ok().build();

    }
}
