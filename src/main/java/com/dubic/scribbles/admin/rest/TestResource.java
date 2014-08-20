/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.rest;


import com.dubic.scribbles.admin.DT;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author dubem
 */
@Path("test")
public class TestResource {

    @Context
    private UriInfo context;
//    @Autowired
//    private IdentityService id;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.dubic.scribbleit.TestResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("tget")
    @Produces("application/json")
    public String getJson(@QueryParam("iDisplayStart") String start,@QueryParam("iDisplayLength") String amount) {
        //TODO return proper representation object
        System.out.println("start - "+start);
        System.out.println("length - "+amount);
        DT dt = new DT();
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{"peter petrelli","pp@gmail.com","activated","2014-07-16","2014-07-16"});
        data.add(new Object[]{"peter petrelli","pp@gmail.com","activated","2014-07-16","2014-07-16"});
        data.add(new Object[]{"peter petrelli","pp@gmail.com","activated","2014-07-16","2014-07-16"});
        dt.setAaData(data);
        dt.setiTotalDisplayRecords(0L+data.size());
        dt.setiTotalRecords(0L+data.size());
        return new Gson().toJson(dt);
    }
//
//    @GET
//    @Path("pram")
//    @Produces("application/json")
//    public String queryparam(@QueryParam("email") String email) {
//        //TODO return proper representation object
//        return new Gson().toJson(id.findUserByEmail(email));
//    }
//    /**
//     * PUT method for updating or creating an instance of TestResource
//     *
//     * @param content representation for the resource
//     * @return an HTTP response with content of the updated or created resource.
//     */
//    @PUT
//    @Consumes("application/json")
//    public void putJson(String content) {
//    }
}
