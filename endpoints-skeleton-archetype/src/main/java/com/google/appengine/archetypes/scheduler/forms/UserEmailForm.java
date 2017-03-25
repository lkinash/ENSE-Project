package com.google.appengine.archetypes.scheduler.forms;

public class UserEmailForm {

	private String userEmail;
	
	public UserEmailForm(){
		
	}
	
	public UserEmailForm(String newUserEmail){

		this.userEmail = newUserEmail;
	
	}
	
	/**
	 * Returns email.
	 * @return email 
	 */
	public String getUserEmail() {
		return this.userEmail;
	}

	/**
	 * Sets a value to attribute email. 
	 * @param newEmail 
	 */
	public void setUserEmail(String newEmail) {
		this.userEmail = newEmail;
	}
	
}
