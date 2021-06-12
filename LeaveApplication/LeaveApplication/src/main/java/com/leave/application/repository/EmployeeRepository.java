package com.leave.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leave.application.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

}