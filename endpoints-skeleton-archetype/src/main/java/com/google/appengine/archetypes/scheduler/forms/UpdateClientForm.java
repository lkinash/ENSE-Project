package com.google.appengine.archetypes.scheduler.forms;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

public class UpdateClientForm {

	/**
	 * Description of the property serivces.
	 */
	private long clientId;

	
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
	
	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;

	
	public UpdateClientForm(){
		
	}
	
	public UpdateClientForm(long newClientId, String newFirstName, String newLastName, long newPhoneNumber, TimeBlockForm newBirthday, String newEmail  ){
		
		this.birthday = newBirthday;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.email = newEmail;
		this.clearance = AdminClearances.client;
		this.clientId = newClientId;

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
	 * Returns client.
	 * @return client 
	 */
	public long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute client. 
	 * @param newclient 
	 */
	public void setClientId(long newClientId) {
		this.clientId = newClientId;
	}
	
}
