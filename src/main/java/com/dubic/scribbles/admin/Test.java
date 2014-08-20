/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author dubem
 */
@ManagedBean(name = "test")
@RequestScoped
public class Test {
    private List<Model> models = new ArrayList<Model>();

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
    
    

    public Test() {
        models.add(new Model("dubic",27,'M'));
        models.add(new Model("sandra",23,'F'));
//        System.out.println("models created");
    }

      public void rends(){
          models.add(new Model("monday",35,'M'));
      }
}
