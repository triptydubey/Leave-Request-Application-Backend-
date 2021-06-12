package com.leave.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leave.application.repository.EmployeeRepository;
import  com.leave.application.repository.EmpLeavesRepository;
import  com.leave.application.models.Employee;
import com.leave.application.exception.ResourceNotFoundException;
import  com.leave.application.models.EmpLeaves;

@Service
public class EmpLeavesService {

	@Autowired
    EmployeeRepository EmployeeRepository;
	
	@Autowired
    EmpLeavesRepository EmpLeavesRepository;
	
	//Get all Employee leave requests
	public List<EmpLeaves> getAllEmpLeaves() {
        //return EmpLeavesRepository.findAll();
        List<EmpLeaves> empleaves = new ArrayList<>();
		EmpLeavesRepository.findAll().forEach(empleaves::add);
		return empleaves;
    }
	
	//Get a leave request by Id
	public Optional<EmpLeaves> getEmpLeavesById(Long id) throws ResourceNotFoundException {
        if (!EmpLeavesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Leave Request with id " + id + " not found");
        }
        return EmpLeavesRepository.findById(id);
    }
	
	//GET LEAVE REQUESTS WITH EMPLOYEE ID
	public Set<EmpLeaves> getEmpLeavesByEmployeeId(Long employeeId) throws ResourceNotFoundException
	{
		Optional<Employee> byId = EmployeeRepository.findById(employeeId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " does not exist");
        }
        Employee employee = byId.get();
        Set<EmpLeaves> leaves = employee.getEmpLeaves();
        return leaves;
	}
	
	
	//Create a new leave request
	public EmpLeaves createEmpLeaves(Long employeeId, EmpLeaves empLeaves) throws ResourceNotFoundException {
        Set<EmpLeaves> empLeaves1 = new HashSet<>();
        Employee employee1 = new Employee();

        Optional<Employee> byId = EmployeeRepository.findById(employeeId);
        if (!byId.isPresent()) {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " does not exist");
        }
        Employee employee = byId.get();

        //tie Employee to EmpLeaves
        empLeaves.setOwner(employee);

        EmpLeaves empLeaves2 = EmpLeavesRepository.save(empLeaves);
        //tie EmpLeaves to Employee
        empLeaves1.add(empLeaves2);
        employee1.setEmpLeaves(empLeaves1);

        return empLeaves2;

    }

	//Update a leave Request
    public EmpLeaves updateEmpLeavesById(Long id, EmpLeaves leaveRequest) throws ResourceNotFoundException {
        if (!EmpLeavesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Leave Request with id " + id + " not found");
        }
        Optional<EmpLeaves> empLeaves = EmpLeavesRepository.findById(id);

        if (!empLeaves.isPresent()) {
            throw new ResourceNotFoundException("Leave Request with id " + id + " not found");
        }

        EmpLeaves empLeaves1 = empLeaves.get();
        
        if(leaveRequest.getStartDate()!=null)
        {
        	empLeaves1.setStartDate(leaveRequest.getStartDate());
        }
        if(leaveRequest.getEndDate()!=null)
        {
        	empLeaves1.setEndDate(leaveRequest.getEndDate());
        }
        if(leaveRequest.getNote()!=null)
        {
        	empLeaves1.setNote(leaveRequest.getNote());
        }
        if(leaveRequest.getStatus()!=null)
        {
        	empLeaves1.setStatus(leaveRequest.getStatus());
        }
        if(leaveRequest.getDuration()!= empLeaves.get().getDuration())
        {
        	empLeaves1.setDuration(leaveRequest.getDuration());
        }
        if(leaveRequest.getLeaveType()!=null)
        {
        	empLeaves1.setLeaveType(leaveRequest.getLeaveType());
        }
        

        return EmpLeavesRepository.save(empLeaves1);
    }

    
   //DELETE A LEAVE REQUEST
    public ResponseEntity<Object> deleteEmpLeavesById(long id) throws ResourceNotFoundException {
        if (!EmpLeavesRepository.existsById(id)) {
            throw new ResourceNotFoundException("Leave Request with id " + id + " not found");
        }

        EmpLeavesRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
