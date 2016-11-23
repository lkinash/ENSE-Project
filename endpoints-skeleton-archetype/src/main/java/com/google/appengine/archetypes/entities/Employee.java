/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Employee.
 * 
 * @author Lindsey
 */
@Entity
public class Employee {
	/**
	 * Description of the property calendar.
	 */
	private Object calendar;

	/**
	 * Description of the property firstName.
	 */
	private String name;

	/**
	 * Description of the property employeeId.
	 */
	@Id 
	private long employeeId;
	
	
	public Employee(){
		
	}
	
	public Employee(Object newCalendar, String newName, long newEmployeeId){
		this.calendar = newCalendar;
		this.name = newName;
		this.employeeId = newEmployeeId;
	}

	
	/**
	 * Returns calendar.
	 * @return calendar 
	 */
	public Object getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Object newCalendar) {
		this.calendar = newCalendar;
	}

	/**
	 * Returns firstName.
	 * @return firstName 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute firstName. 
	 * @param newFirstName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns employeeId.
	 * @return employeeId 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute employeeId. 
	 * @param newEmployeeId 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}



}
