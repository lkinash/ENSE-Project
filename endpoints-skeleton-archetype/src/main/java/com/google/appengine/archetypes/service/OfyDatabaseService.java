/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.service;

import com.google.appengine.archetypes.entities.*;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Description of OfyDatabaseService.
 * 
 * @author Lindsey
 */
public class OfyDatabaseService {


    /**
     * This static block ensure the entity registration.
     */
    static {
        factory().register(Admin.class);
        factory().register(Appointment.class);
        factory().register(Clearances.class);
        factory().register(Client.class);
        factory().register(Employee.class);
        factory().register(Product.class);
        factory().register(Room.class);
        factory().register(Service.class);
        factory().register(SaleItem.class);
        factory().register(Account.class);
        factory().register(Type.class);
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
