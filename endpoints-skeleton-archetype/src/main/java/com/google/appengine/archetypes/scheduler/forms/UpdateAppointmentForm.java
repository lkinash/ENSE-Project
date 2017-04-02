package com.google.appengine.archetypes.scheduler.forms;

public class UpdateAppointmentForm {

	/**
	 * Description of the property appointmentType.
	 */
	private long appointmentId;

	
	/**
	 * Description of the property appointmentType.
	 */
	private long typeId;

	/**
	 * Description of the property service.
	 */
	private long serviceId;

	/**
	 * Description of the property clientId.
	 */
	private long clientId;

	/**
	 * Description of the property clientId.
	 */
	private long employeeId;
	
	/**
	 * Description of the property clientId.
	 */
	private long roomId;
	
	/**
	 * Description of the property serviceId.
	 */
	private int length;
	
	/**
	 * Description of the property end
	 */
	private TimeBlockForm date;
	
	/**
	 * Description of the property end
	 */
	private int hour;
	
	/**
	 * Description of the property end
	 */
	private int minute;
	
	/**
	 * Description of the property appointmentType.
	 */
	private String eventId;

	
	
	public UpdateAppointmentForm(){
		
	}
	
	public UpdateAppointmentForm(String newEventId, long newAppointmentId, long newType, long newService, long newClientId, long newEmployeeId,
			long newRoomId, int newHour, int newMinute, TimeBlockForm newDate, int newLength){

		this.serviceId = newService;
		this.typeId = newType;
		this.clientId = newClientId;
		this.employeeId = newEmployeeId;
		this.date = newDate;
		this.hour = newHour;
		this.minute = newMinute;
		this.length = newLength;
		this.roomId = newRoomId;
		this.appointmentId = newAppointmentId;
		
		this.eventId = newEventId;
	}
	
	
	
	
	public String getEventId(){
		return this.eventId;
	}
	
	public void setEventId(String newEventId){
		this.eventId = newEventId;
	}

	/**
	 * Returns appointmentlong.
	 * @return appointmentlong 
	 */
	public long getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets a value to attribute appointmentlong. 
	 * @param newappointmentlong 
	 */
	public void setTypeId(long newTypeId) {
		this.typeId = newTypeId;
	}

	/**
	 * Returns serviceId.
	 * @return serviceId 
	 */
	public long getServiceId() {
		return this.serviceId;
	}

	/**
	 * Sets a value to attribute serviceId. 
	 * @param newlong 
	 */
	public void setServiceId(long newServiceId) {
		this.serviceId = newServiceId;
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
	 * @param newClientId 
	 */
	public void setClientId(long newClientId) {
		this.clientId = newClientId;
	}
	
	/**
	 * Returns employeeId.
	 * @return employeeId 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute employeeId. 
	 * @param newEmployeeId 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}

	
	/**
	 * Returns RoomId.
	 * @return RoomId 
	 */
	public long getRoomId() {
		return this.roomId;
	}

	/**
	 * Sets a value to attribute RoomId. 
	 * @param newRoomId 
	 */
	public void setRoomId(long newRoomId) {
		this.roomId = newRoomId;
	}
	
	
	
	public int getHour(){
		return this.hour;
	}
	
	public void setHour(int newHour){
		this.hour = newHour;
	}
	
	
	public int getMinute(){
		return this.minute;
	}
	
	public void setMinute(int newMinute){
		this.minute = newMinute;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public void setLength(int newLength){
		this.length = newLength;
	}
	
	
	
	public TimeBlockForm getDate(){
		return this.date;
	}
	
	public void setDate(TimeBlockForm newDate){
		this.date = newDate;
	}
	
	/**
	 * Returns appointmentId.
	 * @return appointmentId 
	 */
	public long getAppointmentId() {
		return this.appointmentId;
	}

	/**
	 * Sets a value to attribute appointmentId. 
	 * @param newappointmentId 
	 */
	public void setAppointmentId(long newAppointmentId) {
		this.appointmentId = newAppointmentId;
	}
	
}
