package com.leave.application.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leave.application.models.EmpLeaves;
import com.leave.application.models.Employee;
import com.leave.application.exception.ResourceNotFoundException;
import com.leave.application.service.EmpLeavesService;
import com.leave.application.service.EmployeeService;

import  com.leave.application.repository.EmpLeavesRepository;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmpLeavesService empLeavesService;
	
	@Autowired
    EmpLeavesRepository EmpLeavesRepository;

    // GET ALL EMPLOYEES
    
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public List<Employee> getEmployee() {
		return employeeService.getEmployees();
	}

	// CREATE A NEW EMPLOYEE
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/newEmployee", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	// GET EMPLOYEE BY ID
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public Optional<Employee> getEmployeeById(@PathVariable(value = "employeeId") Long employeeId)
			throws ResourceNotFoundException {
		return employeeService.getEmployeeById(employeeId);
	}

	// UPDATE AN EMPLOYEE
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable(value = "employeeId") Long employeeId, @RequestBody Employee employee)
			throws ResourceNotFoundException {
		return employeeService.updateEmployeeById(employeeId, employee);
	}

	// DELETE AN EMPLOYEE
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
	public List<Employee> deleteEmployeeById(@PathVariable(value = "employeeId") long employeeId)
			throws ResourceNotFoundException {
		    employeeService.deleteEmployeeById(employeeId);
		    return employeeService.getEmployees();
	}

	// GET ALL LEAVE REQUESTS
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getAllLeaveRequests", method = RequestMethod.GET)
	public List<EmpLeaves> getEmpLeaves() {
		return empLeavesService.getAllEmpLeaves();
	}

	// CREATE A LEAVE REQUEST
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "employee/{employeeId}/leaveRequest", method = RequestMethod.POST)
	public EmpLeaves createEmpLeaves(@PathVariable(value = "employeeId") Long employeeId,
			@RequestBody EmpLeaves leaveRequest) throws ResourceNotFoundException {
		return empLeavesService.createEmpLeaves(employeeId, leaveRequest);
	}

	// GET LEAVE REQUEST BY ID
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/leaveRequest/leave/{id}", method = RequestMethod.GET)
	public Optional<EmpLeaves> getEmpLeavesById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		return empLeavesService.getEmpLeavesById(id);
	}

	// GET LEAVE REQUEST BY Employee ID
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/leaveRequest/{employeeId}", method = RequestMethod.GET)
	public Set<EmpLeaves> getEmpLeavesByEmployeeId(@PathVariable(value = "employeeId") Long employeeId)
			throws ResourceNotFoundException {
		return empLeavesService.getEmpLeavesByEmployeeId(employeeId);
	}

	// UPDATE A LEAVE REQUEST
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/leaveRequest/{id}", method = RequestMethod.PUT)
	public List<EmpLeaves> updateEmpLeaves(@PathVariable(value = "id") Long id, @RequestBody EmpLeaves empLeaves)
			throws ResourceNotFoundException {
		//get owner Id after finding the leave request through id
		Optional<EmpLeaves> leaves = EmpLeavesRepository.findById(id);
		EmpLeaves employeeLeaves = leaves.get();
		Long empId = employeeLeaves.getOwnerId();
		
		//update  the request
		empLeavesService.updateEmpLeavesById(id, empLeaves);
		
		//return the updated leaves by id data
		return empLeavesService.getAllEmpLeaves();
	}

	// DELETE A LEAVE REQUEST
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/leaveRequest/{id}", method = RequestMethod.DELETE)
	public Set<EmpLeaves> deleteEmpLeavesById(@PathVariable(value = "id") long id)
			throws ResourceNotFoundException {
		//get owner Id after finding the leave rewuest through id
		Optional<EmpLeaves> leaves = EmpLeavesRepository.findById(id);
		EmpLeaves empLeaves = leaves.get();
		Long empId = empLeaves.getOwnerId();
		
		//delete  the request
		empLeavesService.deleteEmpLeavesById(id);
		
		//return the updated leaves by id data
		return empLeavesService.getEmpLeavesByEmployeeId(empId);
		
	}

}
