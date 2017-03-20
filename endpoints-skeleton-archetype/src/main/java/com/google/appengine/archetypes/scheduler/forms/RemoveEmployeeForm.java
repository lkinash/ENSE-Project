package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.scheduler.list.AdminClearances;


/**
 * Description of EmployeeForm.
 * 
 * @author Lindsey
 */
public class RemoveEmployeeForm {
	
	/**
	 * Description of the property name.
	 */
	private String name;

	/**
	 * Description of the property calendar.
	 */
	private Calendar calendar;

	/**
	 * Description of the property serivces.
	 */
	private List<Long> serviceIds;

	/**
	 * Description of the property password.
	 */
	private String password;

	/**
	 * Description of the property email.
	 */
	private String email;
	
	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;

	
	private long employeeId;
	
	
	public RemoveEmployeeForm(){
		
	}
	
	public RemoveEmployeeForm(String newName, Calendar newCalendar, List<Long> newServiceIds, long newEmployeeId){
		
		this.calendar = newCalendar;
		this.name = newName;
		this.serviceIds = newServiceIds;
		this.clearance = AdminClearances.employee;
		this.employeeId = newEmployeeId;
	}
	
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns calendar.
	 * @return calendar 
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Calendar newCalendar) {
		this.calendar = newCalendar;
	}

	/**
	 * Returns services.
	 * @return services 
	 */
	public List<Long> getServiceIds() {
		return serviceIds;
	}

	/**
	 * Sets a value to attribute services. 
	 * @param newServices 
	 */
	public void setServiceIds(List<Long> newServiceIds) {
		this.serviceIds = newServiceIds;
	}
	
	/**
	 * Returns email.
	 * @return email 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute email. 
	 * @param newEmail 
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * Returns password.
	 * @return password 
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets a value to attribute password. 
	 * @param newPassword 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	
	/**
	 * Returns clearance.
	 * @return clearance 
	 */
	public AdminClearances getClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute clearance. 
	 * @param newClearance 
	 */
	public void setClearance(AdminClearances newClearance) {
		this.clearance = newClearance;
	}
	
	
	/**
	 * Returns clearance.
	 * @return clearance 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute clearance. 
	 * @param newClearance 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}
	
}
