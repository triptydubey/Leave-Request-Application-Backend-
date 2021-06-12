package com.leave.application.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leave.application.models.Employee;

@Entity
@Table(name="EMP_LEAVE")
/*@NamedQuery(name="EmpLeaves.findAll", query="SELECT t FROM EmpLeaves t")*/
public class EmpLeaves implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="note")
	private String note;

	@Column(name="leaveType")
	private String leaveType;
	
	@Column(name="status")
	private String status = "open";

	@Column(name="approver_name")
	private String approverName="Nirmala Shettar";

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="emp_id")
	 private Employee owner;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	 @JsonIgnore
	public Employee getOwner() {
		return owner;
	}

	 @JsonIgnore
	public void setOwner(Employee owner) {
		this.owner = owner;
	}
	
	public long getOwnerId() {
		return owner.getEmployeeId();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EmpLeaves() {}
	
	public EmpLeaves(long id, String startDate, String endDate, int duration, String leaveType,String note, String approverName,String status) {
		super();
		this.id = id;
		//this.empId = empId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.note = note;
		this.status = status;
		this.approverName = approverName;
		this.duration = duration;
		this.leaveType = leaveType;
	}
	
}
