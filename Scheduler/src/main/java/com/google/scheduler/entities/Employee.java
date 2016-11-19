/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

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
	private String firstName = "";

	/**
	 * Description of the property employeeId.
	 */
	private int employeeId = 0;


	// Start of user code (user defined attributes for Employee)

	// End of user code

	/**
	 * Description of the method getName.
	 * @return 
	 */
	public String getName() {
		// Start of user code for method getName
		String getName = "";
		return getName;
		// End of user code
	}



	/**
	 * Description of the method setName.
	 * @param name 
	 */
	public void setName(String name) {
		// Start of user code for method setName
		// End of user code
	}

	

	// Start of user code (user defined methods for Employee)

	// End of user code
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
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets a value to attribute firstName. 
	 * @param newFirstName 
	 */
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
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
