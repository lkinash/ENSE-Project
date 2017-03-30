package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

public class UpdateEmployeeForm {


	/**
	 * Description of the property serivces.
	 */
	private List<Long> typeIds;


	/**
	 * Description of the property serivces.
	 */
	private long employeeId;

	
	public UpdateEmployeeForm(){
		
	}
	
	public UpdateEmployeeForm(List<Long> newTypeIds, long newEmployeeId){
		
		this.typeIds = newTypeIds;
		this.employeeId = newEmployeeId;
		
	}
	

	/**
	 * Returns types.
	 * @return types 
	 */
	public List<Long> gettypeIds() {
		return typeIds;
	}

	/**
	 * Sets a value to attribute types. 
	 * @param newtypes 
	 */
	public void settypeIds(List<Long> newtypeIds) {
		this.typeIds = newtypeIds;
	}
	
	
	/**
	 * Returns employee.
	 * @return employee 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute employee. 
	 * @param newemployee 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}
	
	
	
}
