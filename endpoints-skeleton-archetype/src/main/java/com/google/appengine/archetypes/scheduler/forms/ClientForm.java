/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import java.util.Date;
import java.util.List;

import com.google.appengine.archetypes.scheduler.entities.Appointment;
import com.google.appengine.archetypes.scheduler.entities.Clearances;
import com.google.appengine.archetypes.scheduler.list.AdminClearances;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ClientForm.
 * 
 * @author Lindsey
 */
public class ClientForm {
	

	/**
	 * Description of the property firstName.
	 */
	private String firstName;
	
	/**
	 * Description of the property lastName.
	 */
	private String lastName;
	
	/**
	 * Description of the property phoneNumber.
	 */
	private long phoneNumber;

	/**
	 * Description of the property email.
	 */
	private String email;

	/**
	 * Description of the property birthday.
	 */
	private TimeBlockForm birthday;
	
	
	public ClientForm(){
		
	}
	
	public ClientForm(String newFirstName, String newLastName, long newPhoneNumber, TimeBlockForm newBirthday, String newEmail  ){

		this.birthday = newBirthday;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.email = newEmail;

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
	 * Returns birthday.
	 * @return birthday 
	 */
	public TimeBlockForm getBirthday() {
		return this.birthday;
	}

	/**
	 * Sets a value to attribute birthday. 
	 * @param newBirthday 
	 */
	public void setBirthday(TimeBlockForm newBirthday) {
		this.birthday = newBirthday;
	}


	/**
	 * Returns phoneNumber.
	 * @return phoneNumber 
	 */
	public long getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets a value to attribute phoneNumber. 
	 * @param newPhoneNumber 
	 */
	public void setPhoneNumber(long newPhoneNumber) {
		this.phoneNumber = newPhoneNumber;
	}




}
