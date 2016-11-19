/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

import com.google.scheduler.entities.Appointment;
import com.google.scheduler.entities.Clearances;
import com.google.scheduler.entities.User;

import java.util.Date;
import java.util.List;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Client.
 * 
 * @author Lindsey
 */
public class Client extends User {
	/**
	 * Description of the property firstName.
	 */
	private String firstName = "";

	/**
	 * Description of the property lastName.
	 */
	private String lastName = "";

	/**
	 * Description of the property phoneNumber.
	 */
	private int phoneNumber = 0;

	/**
	 * Description of the property calendar.
	 */
	private Appointment calendar = null;

	/**
	 * Description of the property clearances.
	 */
	private List<Clearances> clearances = null;

	/**
	 * Description of the property appointments.
	 */
	private List appointments = null;

	/**
	 * Description of the property birthday.
	 */
	private Date birthday = null;

	// Start of user code (user defined attributes for Client)

	// End of user code

	/**
	 * Description of the method getUserId.
	 * @return 
	 */
	public Long getUserId() {
		// Start of user code for method getUserId
		Long getUserId = null;
		return getUserId;
		// End of user code
	}

	/**
	 * Description of the method getEmail.
	 * @return 
	 */
	public String getEmail() {
		// Start of user code for method getEmail
		String getEmail = "";
		return getEmail;
		// End of user code
	}

	/**
	 * Description of the method setEmail.
	 * @param email 
	 */
	public void setEmail(String email) {
		// Start of user code for method setEmail
		// End of user code
	}


	/**
	 * Description of the method getPassword.
	 * @return 
	 */
	public String getPassword() {
		// Start of user code for method getPassword
		String getPassword = "";
		return getPassword;
		// End of user code
	}

	/**
	 * Description of the method setPassword.
	 * @param password 
	 */
	public void setPassword(String password) {
		// Start of user code for method setPassword
		// End of user code
	}

	/**
	 * Description of the method setUserId.
	 * @param clientId 
	 */
	public void setUserId(Long clientId) {
		// Start of user code for method setUserId
		// End of user code
	}

	// Start of user code (user defined methods for Client)

	// End of user code
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
	 * Returns lastName.
	 * @return lastName 
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets a value to attribute lastName. 
	 * @param newLastName 
	 */
	public void setLastName(String newLastName) {
		this.lastName = newLastName;
	}

	/**
	 * Returns phoneNumber.
	 * @return phoneNumber 
	 */
	public int getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets a value to attribute phoneNumber. 
	 * @param newPhoneNumber 
	 */
	public void setPhoneNumber(int newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}

	/**
	 * Returns calendar.
	 * @return calendar 
	 */
	public Appointment getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Appointment newCalendar) {
		this.calendar = newCalendar;
	}

	/**
	 * Returns clearances.
	 * @return clearances 
	 */
	public List<Clearances> getClearances() {
		return this.clearances;
	}

	/**
	 * Returns appointments.
	 * @return appointments 
	 */
	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	/**
	 * Sets a value to attribute appointments. 
	 * @param newAppointments 
	 */
	public void setAppointments(List<Appointment> newAppointments) {
		this.appointments = newAppointments;
	}

	/**
	 * Returns birthday.
	 * @return birthday 
	 */
	public Date getBirthday() {
		return this.birthday;
	}

	/**
	 * Sets a value to attribute birthday. 
	 * @param newBirthday 
	 */
	public void setBirthday(Date newBirthday) {
		this.birthday = newBirthday;
	}

}
