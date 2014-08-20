/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author dubem
 */
public class ChartValues {
    private String month = Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    private List<Pair> pairs = new ArrayList<Pair>();

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    
    
    public static void main(String[] hd){
//        ChartValues cv = new ChartValues();
//        cv.getImap().put("1", 2);
//        cv.getImap().put("2", 25);
//        cv.getImap().put("3", 6);
//        System.out.println(new Gson().toJson(cv));
    }
}
