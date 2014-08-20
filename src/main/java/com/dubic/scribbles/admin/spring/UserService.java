/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.spring;

import com.dubic.scribbles.admin.models.User;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dubem
 */
@Named("userService")
public class UserService {

    @Inject
    private DB dao;

    public List<User> getAllUsers(int start,int size) {
        return dao.getAll(User.class, start, size);
    }
}
