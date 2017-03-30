package com.google.appengine.archetypes.scheduler.forms;

import java.util.Date;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

public class RemoveClientForm {


	/**
	 * Description of the property firstName.
	 */
	private String firstName;

	/**
	 * Description of the property email.
	 */
	private String email;

	/**
	 * Description of the property birthday.
	 */
	private Date birthday;

	/**
	 * Description of the property phoneNumber.
	 */
	private int phoneNumber;

	/**
	 * Description of the property lastName.
	 */
	private String lastName;
	
	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;

	private long clientId;
	
	
	public RemoveClientForm(){
		
	}
	
	public RemoveClientForm(long newClientId, String newFirstName, String newLastName, int newPhoneNumber, Date newBirthday,  String newEmail  ){
		
		super();
		
		this.clientId = newClientId;
		this.birthday = newBirthday;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.email = newEmail;
		this.clearance = AdminClearances.client;

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
	 * Returns clientId.
	 * @return clientId 
	 */
	public long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute clientId. 
	 * @param newclientId 
	 */
	public void setClientId(long newClientId) {
		this.clientId = newClientId;
	}

	
}
