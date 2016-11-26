/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;

// End of user code

/**
 * Description of User.
 * 
 * @author Lindsey
 */
@Entity
public class User {
	/**
	 * Description of the property email.
	 */
	@Index
    private String email;

	/**
	 * Description of the property password.
	 */
	private String password;

	/**
	 * Description of the property userId.
	 */
	@Id
	private String userId;


	// Start of user code (user defined methods for User)

	// End of user code
	
	public User(){
		
	}
	
	public User(String newEmail, String newPassword, String newUserId){
		
		this.email = newEmail;
		this.userId = newUserId;
		this.password = newPassword;
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
	 * Returns userId.
	 * @return userId 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute userId. 
	 * @param newUserId 
	 */
	public void setUserId(String newUserId) {
		this.userId = newUserId;
	}

}
