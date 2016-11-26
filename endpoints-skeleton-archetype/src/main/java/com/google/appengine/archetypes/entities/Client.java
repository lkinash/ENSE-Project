/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.entities.User;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

// End of user code

/**
 * Description of Client.
 * 
 * @author Lindsey
 */
@Entity
public class Client extends User {
	/**
	 * Description of the property firstName.
	 */
	@Index
    private String firstName;

	/**
	 * Description of the property lastName.
	 */
	@Index
    private String lastName;

	/**
	 * Description of the property phoneNumber.
	 */
	private int phoneNumber;

	/**
	 * Description of the property calendar.
	 */
	private Appointment calendar;

	/**
	 * Description of the property clearances.
	 */
	private List<Clearances> clearances;

	/**
	 * Description of the property appointments.
	 */
	@Index
    private List<Appointment> appointments;

	/**
	 * Description of the property birthday.
	 */
	private Date birthday;

	// Start of user code (user defined attributes for Client)

	// End of user code

	public Client(){
		
	}
	
	public Client(String newFirstName, String newLastName, int newPhoneNumber, Date newBirthday, List<Appointment> newAppointments, List<Clearances> newClearances, Appointment newCalendar, String newEmail, String newPassword, String newUserId  ){
		
		super(newEmail, newPassword, newUserId);
		
		this.birthday = newBirthday;
		this.appointments = newAppointments;
		this.calendar = newCalendar;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.clearances = newClearances;
	}
	
	/**
	 * Description of the method getUserId.
	 * @return 
	 */
	public String getUserId() {
		return super.getUserId();
	}
	
	public void setUserId(String newUserId) {
		super.setUserId(newUserId);
	}

	/**
	 * Description of the method getEmail.
	 * @return 
	 */
	public String getEmail() {
		return super.getEmail();
	}

	/**
	 * Description of the method setEmail.
	 * @param email 
	 */
	public void setEmail(String newEmail) {
		super.setEmail(newEmail);
	}


	/**
	 * Description of the method getPassword.
	 * @return 
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * Description of the method setPassword.
	 * @param password 
	 */
	public void setPassword(String newPassword) {
		super.setPassword(newPassword);
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
