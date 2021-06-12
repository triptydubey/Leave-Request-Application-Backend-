package com.leave.application.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leave.application.models.EmpLeaves;

@Entity
@Table(name="EMPLOYEE")
/*@NamedQueries({
    @NamedQuery(name="Employee.findAll",
                query="SELECT e FROM Employee e"),
    @NamedQuery(name="Employee.findById",
                query="SELECT e FROM Employee e WHERE e.employeeId = :employeeId"),
}) */
public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id")
	private long employeeId;

	@Column(name="email_id")
	private String emailId;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="Emp_designation")
	private String designation;

	@Column(name="phone_number")
	private String phoneNumber;
	
	//bi-directional many-to-one association to EmpLeaves
	@OneToMany(mappedBy = "owner")
	private Set<EmpLeaves> employeeLeaves = new HashSet<>();;
	
	public Employee() {
	}

	//GETTERS AND SETTERS
	public Set<EmpLeaves> getEmpLeaves() {
		return employeeLeaves;
	}
	
	public void setEmpLeaves(Set<EmpLeaves> employeeLeaves) {
		this.employeeLeaves = employeeLeaves;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/*
	public EmpLeaves addEmpLeaves(EmpLeaves employeeLeaves) {
		getEmpLeaves().add(employeeLeaves);
		EmpLeaves.setEmpLeaves(this);

		return employeeLeaves;
	}

	public EmpLeaves removeEmpLeaves(EmpLeaves EmpLeaves) {
		getEmpLeaves().remove(EmpLeaves);
		EmpLeaves.setEmployee(null);

		return EmpLeaves;
	}
	*/
	
	public Employee(long employeeId, String emailId, String firstName,String lastName,
			String designation, String phoneNumber, List<EmpLeaves> employeeLeaves) {
		super();
		this.employeeId = employeeId;
		this.emailId = emailId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
		employeeLeaves = employeeLeaves;
	}
}
