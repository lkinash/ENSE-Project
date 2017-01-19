/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.entities.Account;

import java.util.Date;
import java.util.Iterator;
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
public class Client extends Account {
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
	private Calendar calendar;

	/**
	 * Description of the property clearances.
	 */
	private List<Long> clearanceIds;

	/**
	 * Description of the property appointmentIds.
	 */
	@Index
    private List<Long> appointmentIds;

	/**
	 * Description of the property birthday.
	 */
	private Date birthday;

	// Start of user code (user defined attributes for Client)

	// End of user code

	public Client(){
		
	}
	
	public Client(String newFirstName, String newLastName, int newPhoneNumber, Date newBirthday, List<Long> newAppointments, List<Long> newClearanceIds, Calendar newCalendar, String newEmail, String newPassword, long newUserId  ){
		
		super(newEmail, newPassword, newUserId);
		
		this.birthday = newBirthday;
		this.appointmentIds = newAppointments;
		this.calendar = newCalendar;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.clearanceIds = newClearanceIds;
	}
	
	/**
	 * Description of the method getUserId.
	 * @return 
	 */
	public long getUserId() {
		return super.getUserId();
	}
	
	public void setUserId(long newUserId) {
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
	 * Returns clearances.
	 * @return clearances 
	 */
	public List<Long> getClearances() {
		return this.clearanceIds;
	}
	
	/**
	 * Returns clearances.
	 * @return clearances 
	 */
	public void addClearance(Long newClearance) {
		this.clearanceIds.add(newClearance);
	}
	
	/**
	 * Returns clearances.
	 * @return clearances 
	 */
	public boolean removeClearance(Long newClearance) {
		
		Iterator<Long> temp = clearanceIds.iterator();
		while (temp.hasNext()) {
		  	Long tempClearance = temp.next();
		   if(tempClearance == newClearance){
			   temp.remove();
			   return true;
		   }
		}
		return false;
	}
	
	

	/**
	 * Returns appointmentIds.
	 * @return appointmentIds 
	 */
	public List<Long> getAppointments() {
		return this.appointmentIds;
	}

	/**
	 * Sets a value to attribute appointmentIds. 
	 * @param newAppointments 
	 */
	public void setAppointments(List<Long> newAppointments) {
		this.appointmentIds = newAppointments;
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
