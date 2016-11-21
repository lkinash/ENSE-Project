/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import java.util.Date;
import java.util.List;

import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Clearances;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ClientForm.
 * 
 * @author Lindsey
 */
public class ClientForm {
	/**
	 * Description of the property password.
	 */
	private String password = "";

	/**
	 * Description of the property firstName.
	 */
	private String firstName = "";

	/**
	 * Description of the property email.
	 */
	private String email = "";

	/**
	 * Description of the property birthday.
	 */
	private Date birthday = null;

	/**
	 * Description of the property phoneNumber.
	 */
	private int phoneNumber = 0;

	/**
	 * Description of the property lastName.
	 */
	private String lastName = "";

	// Start of user code (user defined methods for ClientForm)

	// End of user code
	
	public ClientForm(){
		
	}
	
	public ClientForm(String newFirstName, String newLastName, int newPhoneNumber, Date newBirthday, String newPassword, String newEmail  ){
		
		super();
		
		this.birthday = newBirthday;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.email = newEmail;
		this.password = newPassword;

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

}
