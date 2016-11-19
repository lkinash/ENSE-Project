/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.forms;

import java.util.List;

// Start of user code (user defined imports)
import Entities.Service;
// End of user code

/**
 * Description of EmployeeForm.
 * 
 * @author Lindsey
 */
public class EmployeeForm {
	/**
	 * Description of the property name.
	 */
	private String name = "";

	/**
	 * Description of the property calendar.
	 */
	private Object calendar = null;

	/**
	 * Description of the property serivces.
	 */
	private List<Service> serivces = null;

	// Start of user code (user defined methods for EmployeeForm)

	// End of user code
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
	 * Returns serivces.
	 * @return serivces 
	 */
	public List<Service> getSerivces() {
		return this.serivces;
	}

	/**
	 * Sets a value to attribute serivces. 
	 * @param newSerivces 
	 */
	public void setSerivces(List<Service> newSerivces) {
		this.serivces = newSerivces;
	}

}
