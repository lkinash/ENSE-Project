/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of Account code (Account defined imports)
import com.googlecode.objectify.annotation.Index;

// End of Account code

/**
 * Description of Account.
 * 
 * @author Lindsey
 */
@Entity
public class Account {
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
	 * Description of the property AccountId.
	 */
	@Id
	private long userId;


	// Start of Account code (Account defined methods for Account)

	// End of Account code
	
	public Account(){
		
	}
	
	public Account(String newEmail, String newPassword, long newAccountId){
		
		this.email = newEmail;
		this.userId = newAccountId;
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
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(long newAccountId) {
		this.userId = newAccountId;
	}

}
