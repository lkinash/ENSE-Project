/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)

// End of user code

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
					 * Description of checkout.
					 */
					checkout,

	// Start of user code (user defined enum literals for Status)

	// End of user code
}
