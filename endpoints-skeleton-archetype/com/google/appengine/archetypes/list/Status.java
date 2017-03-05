/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.list;


import com.googlecode.objectify.annotation.Entity;


/**
 * Description of Status.
 * 
 * @author Lindsey
 */
@Entity
public enum Status {
					/**
					 * Description of booked.
					 */
					booked,

					/**
					 * Description of confirmed.
					 */
					confirmed,

					/**
					 * Description of present.
					 */
					present,
					
					/**
					 * Description of present.
					 */
					inProgress,

					/**
					 * Description of checkout.
					 */
					checkout,

	// Start of user code (user defined enum literals for Status)

	// End of user code
}
