/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;
// End of user code

/**
 * Description of Client.
 * 
 * @author Lindsey
 */
@Entity
public class Client{

	
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
	 * Description of the property calendarId.
	 */
	private String calendarId;

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
	private long birthdayId;

	/**
	 * Description of the property adminClearance.
	 */
	private AdminClearances clearance;

	
	/**
	 * Description of the property AccountId.
	 */
	@Id
	private long clientId;
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String userId;
	
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String email;
	

	
	public Client(){
		
	}
	
	public Client(String newFirstName, String newLastName, int newPhoneNumber, long newBirthday, List<Long> newAppointments,
			List<Long> newClearanceIds, String newCalendarId, String newUserId, long newClientId  , String newEmail){

		this.userId = newUserId;
		this.birthdayId = newBirthday;
		this.appointmentIds = newAppointments;
		this.calendarId = newCalendarId;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.phoneNumber = newPhoneNumber;
		this.clearanceIds = newClearanceIds;
		this.clientId = newClientId;
		this.clearance = AdminClearances.client;
		this.email = newEmail;
	}
	
	/**
	 * Returns Email.
	 * @return Email 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute Email. 
	 * @param newEmail 
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getClientId() {
		return this.clientId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setClientId(long newClientId) {
		this.clientId = newClientId;
	}

	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(String newAccountId) {
		this.userId = newAccountId;
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
	 * Returns calendarId.
	 * @return calendarId 
	 */
	public String getCalendarId() {
		return this.calendarId;
	}

	/**
	 * Sets a value to attribute calendarId. 
	 * @param newCalendarId 
	 */
	public void setCalendarId(String newCalendarId) {
		this.calendarId = newCalendarId;
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
	public long getBirthday() {
		return this.birthdayId;
	}

	/**
	 * Sets a value to attribute birthday. 
	 * @param newBirthday 
	 */
	public void setBirthday(long newBirthday) {
		this.birthdayId = newBirthday;
	}

	
	
	/**
	 * Returns adminClearance.
	 * @return adminClearance 
	 */
	public AdminClearances getAdminClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute adminClearance. 
	 * @param newAdminClearance 
	 */
	public void setAdminClearance(AdminClearances newAdminClearance) {
		this.clearance = newAdminClearance;
	}
}
