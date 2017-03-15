package com.google.appengine.archetypes.scheduler.forms;

import java.util.HashSet;
import java.util.Set;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

public class PageAuthForm {

		/**
		 * Description of the property page authId.
		 */
		private AdminClearances clearance;
		
		/**
		 * Description of the property page authId.
		 */
		private Set<Integer> viewOnly;
		
		/**
		 * Description of the property page authId.
		 */
		private Set<Integer> viewAndEdit;
		
		
		public PageAuthForm(){
			
		}
		
		public PageAuthForm(long newId, AdminClearances newClearance){
		
			this.clearance = newClearance;
			this.viewOnly = new HashSet<Integer>();
			this.viewAndEdit = new HashSet<Integer>();
		}
		
		
		public AdminClearances getClearance(){
			return this.clearance;
		}
		
		public void setClearance(AdminClearances newAdminClearance){
			this.clearance = newAdminClearance;
		}
		
		
		public void setView(Set<Integer> newPages){
			this.viewOnly = newPages;
		}
		
		public Set<Integer> getView(){
			return this.viewOnly;
		}
		

		public void setViewAndEdit(Set<Integer> newPages){
			this.viewAndEdit = newPages;
		}
		
		public Set<Integer> getViewAndEdit(){
			return this.viewAndEdit;
		}
		

		


	
}
