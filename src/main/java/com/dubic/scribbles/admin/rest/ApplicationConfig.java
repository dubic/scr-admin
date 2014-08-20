/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

/**
 *
 * @author dubem
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        // Now you can expect validation errors to be sent to the client.
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        // @ValidateOnExecution annotations on subclasses won't cause errors.
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        register(ValidationConfigurationContextResolver.class);
                
        register(com.dubic.scribbles.admin.rest.TestResource.class);
        register(com.dubic.scribbles.admin.rest.DTResource.class);
        register(com.dubic.scribbles.admin.rest.ChartResource.class);

    }

}
