/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of Employee.
 * 
 * @author Lindsey
 */
public class Employee {
	/**
	 * Description of the property calendar.
	 */
	private Object calendar = null;

	/**
	 * Description of the property firstName.
	 */
	private String name = "";

	/**
	 * Description of the property employeeId.
	 */
	private int employeeId = 0;
	
	public Employee(){
		
	}
	
	public Employee(Object calendar, String name, int employeeId){
		this.calendar = calendar;
		this.name = name;
		this.employeeId = employeeId;
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
	public int getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute employeeId. 
	 * @param newEmployeeId 
	 */
	public void setEmployeeId(int newEmployeeId) {
		this.employeeId = newEmployeeId;
	}



}
