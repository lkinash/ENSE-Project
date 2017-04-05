/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ClientLoginForm.
 * 
 * @author Lindsey
 */
public class ClientLoginForm {


	/**
	 * Description of the property email.
	 */
	private String email;


	
	public ClientLoginForm(){
		
	}
	
	public ClientLoginForm(String newEmail){
		
		this.email = newEmail;
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


}
