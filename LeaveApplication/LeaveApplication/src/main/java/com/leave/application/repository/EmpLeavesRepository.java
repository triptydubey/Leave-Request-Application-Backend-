package com.leave.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.leave.application.models.EmpLeaves;

@Repository
public interface EmpLeavesRepository extends CrudRepository<EmpLeaves, Long>{
	
	}

//THIS LAYER WILL COMMUNICATE WITH DATABASE AND PERSIST DATA THERE