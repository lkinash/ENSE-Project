package com.google.appengine.archetypes.scheduler.entities;

import java.util.HashSet;
import java.util.Set;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Description of Admin.
 * 
 * @author Lindsey
 */
@Entity
public class PageAuth {

	/**
	 * Description of the property Id.
	 */
	@Id
	private long pageAuthId;

	/**
	 * Description of the property page authId.
	 */
	@Index
	private AdminClearances clearance;
	
	/**
	 * Description of the property page authId.
	 */
	private Set<Integer> viewOnly;
	
	/**
	 * Description of the property page authId.
	 */
	private Set<Integer> viewAndEdit;
	
	
	public PageAuth(){
		
	}
	
	public PageAuth(long newId, AdminClearances newClearance){
		
		this.pageAuthId = newId;
		this.clearance = newClearance;
		this.viewOnly = new HashSet<Integer>();
		this.viewAndEdit = new HashSet<Integer>();
	}
	
	/**
	 * Returns page authId.
	 * @return page authId 
	 */
	public long getPageAuthId() {
		return this.pageAuthId;
	}

	/**
	 * Sets a value to attribute page authId. 
	 * @param newpage authId 
	 */
	public void setPageAuthId(long newPageAuthId) {
		this.pageAuthId = newPageAuthId;
	}
	
	public AdminClearances getClearance(){
		return this.clearance;
	}
	
	public void setClearance(AdminClearances newAdminClearance){
		this.clearance = newAdminClearance;
	}
	
	
	public void addView(int newPage){
		this.viewOnly.add(newPage);
	}
	
	public void removeView(int newPage){
		this.viewOnly.remove(newPage);
	}
	
	public boolean canView(Integer page){
		return this.viewOnly.contains(page);
	}
	
	
	
	public void addViewAndEdit(int newPage){
		this.viewAndEdit.add(newPage);
	}
	
	public void removeViewAndEdit(int newPage){
		this.viewAndEdit.remove(newPage);
	}
	
	public boolean canViewAndEdit(Integer page){
		return this.viewAndEdit.contains(page);
	}
	
}
