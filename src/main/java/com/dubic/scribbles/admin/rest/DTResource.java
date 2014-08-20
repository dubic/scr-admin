/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.rest;

import com.dubic.scribbles.admin.DT;
import com.dubic.scribbles.admin.Utils;
import com.dubic.scribbles.admin.dt.DTSQLBuilder;
import com.dubic.scribbles.admin.dt.JPAQueryBuilder;
import com.dubic.scribbles.admin.dt.Param;
import com.dubic.scribbles.admin.models.User;
import com.dubic.scribbles.admin.spring.DB;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST Web Service
 *
 * @author dubem
 */
@Path("tables")
public class DTResource {

    @Context
    private UriInfo context;

    @Autowired
    private DB dao;
    private final Logger log = Logger.getLogger(getClass());

    /**
     * Creates a new instance of PostsResource
     */
    public DTResource() {
    }

    /**
     * /0/10
     *
     * @param start
     * @param amount
     * @param sortCol
     * @param sortDir
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/users")
    @Produces("application/json")
    public String getJokes(
            @QueryParam("iDisplayStart") int start,
            @QueryParam("iDisplayLength") int amount,
            @QueryParam("iSortCol_0") int sortCol,
            @QueryParam("sSortDir_0") String sortDir,
            @QueryParam("sSearch") String filter) {
        
        String[] cols = new String[]{"u.screen_name", "u.email","u.active","u.create_dt","u.last_login_dt"};
        DTSQLBuilder sql = new DTSQLBuilder("SELECT u.id,u.screen_name,u.email,u.active,u.create_dt,u.last_login_dt FROM idm_user u");
        if (!Utils.isEmpty(filter)) {
            sql.param("u.email LIKE", "%"+filter+"%");
            sql.param("u.screen_name LIKE", "%"+filter+"%");
        }
        if (cols[sortCol] != null) {
            sql.order(cols[sortCol], "asc".equals(sortDir));
        }
        String[] toSQL = sql.limit(start, amount).toSQL();
        log.debug(toSQL[0]);
        List<User> users = dao.createNativeQuery(toSQL[0], User.class).getResultList();
        DT dt = new DT(users.size());
        try {
            for (User u : users) {
                String a = u.isActivated() == false? "<span style='color:red'>"+u.isActivated()+"</span>" : String.valueOf(u.isActivated());
                dt.getAaData().add(new Object[]{"<a href='javascript:showUserDetails("+u.getId()+")'>"+u.getScreenName()+"</a>", u.getEmail(), a, u.getCreateDate(), u.getLastLoginDate()});
            }
            Long count = dao.getCount("SELECT COUNT(u.id) FROM User u");
            dt.setiTotalDisplayRecords(count);
            dt.setiTotalRecords(count);
        } catch (Exception e) {
            log.fatal(e.getMessage(), e);
        }
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat("dd-MMM-yyyy hh:mm:ss a");
        return gb.create().toJson(dt);

    }

    /**
     * PUT method for updating or creating an instance of DTResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
//        EntityManager em;
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> c = cb.createQuery(User.class);
//        Root<User> u = c.from(User.class);
//        c.select(u)
//                .where(cb.equal(u.get("name"), "John Smith"));
    }
}
