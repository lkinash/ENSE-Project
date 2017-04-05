package com.google.appengine.archetypes.scheduler.forms;



public class ClientClearanceForm {

	/**
	 * Description of the property service.
	 */
    private long serviceId;


	/**
	 * Description of the property serivces.
	 */
	private long clientId;

	
	public ClientClearanceForm(){
		
	}
	
	public ClientClearanceForm(long newClientId, long newServiceId){
		
		this.serviceId = newServiceId;
		this.clientId = newClientId;

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
	
	
	/**
	 * Returns service.
	 * @return service 
	 */
	public long getServiceId() {
		return this.serviceId;
	}

	/**
	 * Sets a value to attribute service. 
	 * @param newservice 
	 */
	public void setServiceId(long newServiceId) {
		this.serviceId = newServiceId;
	}
	
	
}
