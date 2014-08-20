/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.rest;

import com.dubic.scribbles.admin.dto.ChartValues;
import com.dubic.scribbles.admin.dto.Pair;
import com.dubic.scribbles.admin.spring.DB;
import com.google.gson.Gson;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST Web Service
 *
 * @author dubem
 */
@Path("charts")
public class ChartResource {

    @Context
    private UriInfo context;
    private Logger log = Logger.getLogger(getClass());
    @Autowired
    private DB dao;
//    @Size(min = 8, max = 10, message = "month must be between 8 - 10")
    @QueryParam("month")
    private int monthAdd;
    private Calendar cal = Calendar.getInstance();
//    @NotNull(message = "vtest must be not be a null value string")
//    @QueryParam("vtest")
//    private String vtest;

    /**
     * Creates a new instance of ChartResource
     */
    public ChartResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.dubic.scribbles.admin.rest.ChartResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/registration")
    @Produces("application/json")
    public String registration() {
//        log.debug("VTEST - "+vtest);
        cal.add(Calendar.MONTH, monthAdd);

//        int m = month == null ? cal.get(Calendar.MONTH)+1 : month;//month not set,use current
        List<Object[]> res = dao.createNativeQuery("SELECT DAY(u.create_dt),COUNT(u.id) FROM idm_user u WHERE MONTH(u.create_dt) = " + (cal.get(Calendar.MONTH) + 1) + " GROUP BY DAY(u.create_dt)")
                .getResultList();
        ChartValues cv = new ChartValues();
        cv.setMonth(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));

        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH)+1; i++) {
            Object[] r = getResult(res, i);
            if (r != null) {
                cv.getPairs().add(new Pair((Integer)r[0] + "", (Long) r[1]));
            }else{
                cv.getPairs().add(new Pair(i + "", 0L));
            }
        }

        return new Gson().toJson(cv);
    }

    /**
     * PUT method for updating or creating an instance of ChartResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    private Object[] getResult(List<Object[]> res, int i) {
        for (Object[] o : res) {
            if ((Integer) o[0] == i) {
                return o;
            }
        }
        return null;
    }
}
