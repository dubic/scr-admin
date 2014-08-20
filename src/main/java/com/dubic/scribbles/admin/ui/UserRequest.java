/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.ui;

import com.dubic.scribbles.admin.spring.DB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author dubem
 */
@ManagedBean(name = "userRequest")
@RequestScoped
public class UserRequest {
    private Long allUsers;
    private Long activeUsers;
    private Long lockedUsers;
    private DB dao;

    public Long getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(Long allUsers) {
        this.allUsers = allUsers;
    }

    public Long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Long activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Long getLockedUsers() {
        return lockedUsers;
    }

    public void setLockedUsers(Long lockedUsers) {
        this.lockedUsers = lockedUsers;
    }

    
    /**
     * Creates a new instance of UserRequest
     */
    public UserRequest() {
        WebApplicationContext ctx = FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance());
        dao = ctx.getBean(DB.class);
        try {
            allUsers = dao.getCount("SELECT COUNT(u.id) FROM User u");
            
//            allUsers = Integer.parseInt(all*2356+"");
            activeUsers = dao.getCount("SELECT COUNT(u.id) FROM User u WHERE u.activated = true");
            lockedUsers = dao.getCount("SELECT COUNT(u.id) FROM User u WHERE u.locked = true");
            
        } catch (Exception ex) {
            Logger.getLogger(UserRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
