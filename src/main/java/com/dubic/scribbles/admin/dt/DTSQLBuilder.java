/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbles.admin.dt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dubem
 */
public class DTSQLBuilder {

    private final String select;
    private String count;
    private List<Param> params = new ArrayList<Param>();
    private List<Param> orList = new ArrayList<Param>();
    private List<Param> havingList = new ArrayList<Param>();
    private Param order;
    private String group;
    private int first, size = -1;
    private boolean hasWhere;
    private boolean hasHaving;
    private StringBuilder selectBuilder = null;
    private StringBuilder countBuilder = null;

    public DTSQLBuilder(String select) {
        this.select = select;
        this.selectBuilder = new StringBuilder(select);
        this.countBuilder = new StringBuilder();
    }

    public DTSQLBuilder count(String countStr) {
        this.count = countStr;
        this.countBuilder.append(countStr);
        return this;
    }

    public DTSQLBuilder param(String name, Object value) {
        this.params.add(new Param(name, value));
        return this;
    }

    public DTSQLBuilder or(String name, Object value) {
        this.orList.add(new Param(name, value));
        return this;
    }

    public DTSQLBuilder having(String name, Object value) {
        this.havingList.add(new Param(name, value));
        return this;
    }

    public DTSQLBuilder order(String name, boolean value) {
        this.order = new Param(name, value);
        return this;
    }

    public DTSQLBuilder group(String name) {
        this.group = name;
        return this;
    }

    public DTSQLBuilder limit(int first, int size) {
        this.first = first;
        this.size = size;
        return this;
    }

    public String[] toSQL() {
        if (!orList.isEmpty()) {
            whereAnd();
            appendQuery(createOrQuery());
        }
        if (!params.isEmpty()) {
            whereAnd();
            appendQuery(createParamQuery());
        }
        if (!havingList.isEmpty()) {
            havingAnd();
            appendQuery(createHavingQuery());
        }
        groupby();
        this.orderby();
        if (this.first != -1 && this.size != -1) {
            this.selectBuilder.append(" limit ").append(first).append(",").append(size);
        }

        return new String[]{this.selectBuilder.toString(), this.countBuilder.toString()};
    }

    private void whereAnd() {
        if (!this.hasWhere) {
            appendQuery(" WHERE ");
            this.hasWhere = true;
            return;
        }
        appendQuery(" AND ");
    }

    private void havingAnd() {
        if (!this.hasHaving) {
            appendQuery(" HAVING ");
            this.hasHaving = true;
            return;
        }
        appendQuery(" AND ");
    }

    private String createOrQuery() {
        StringBuffer buf = new StringBuffer();
//        whereAnd();
        for (Iterator<Param> it = orList.iterator(); it.hasNext();) {
            Param param = it.next();
            buf.append(param.getName()).append(" ").append(getParamValue(param.getValue()));
            if (it.hasNext()) {
                buf.append(" OR ");
            }
        }
        return buf.toString();
    }

    private String createParamQuery() {
        StringBuffer buf = new StringBuffer();
        for (Iterator<Param> it = params.iterator(); it.hasNext();) {
            Param param = it.next();
            buf.append(param.getName()).append(" ").append(getParamValue(param.getValue()));
            if (it.hasNext()) {
                buf.append(" AND ");
            }
        }
        return buf.toString();
    }

    private String createHavingQuery() {
        StringBuffer buf = new StringBuffer();
        for (Iterator<Param> it = havingList.iterator(); it.hasNext();) {
            Param param = it.next();
            buf.append(param.getName()).append(" ").append(getParamValue(param.getValue()));
            if (it.hasNext()) {
                buf.append(" AND ");
            }
        }
        return buf.toString();
    }

    private Object getParamValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        }
        return value;
    }

    private void appendQuery(String sql) {
        this.selectBuilder.append(sql);
        if (this.countBuilder != null) {
            this.countBuilder.append(sql);
        }
    }

    private void orderby() {
        if (order == null) {
            return;
        }
        this.selectBuilder.append(" ORDER BY ").append(order.getName());
        if ((Boolean) order.getValue() == false) {
            this.selectBuilder.append(" desc");
        }
    }

    private void groupby() {
        if (group == null) {
            return;
        }
        this.selectBuilder.append(" GROUP BY ").append(group);
        this.countBuilder.append(" GROUP BY ").append(group);
    }

    public static void main(String[] args) {
//        JPAQueryBuilder jpa = new JPAQueryBuilder("SELECT w.userId,SUM(w.amount) FROM Winning w");
////        jpa.having("SUM(w.amount) > 0").having("SUM(w.amount) < 500");
//        jpa.addOr(new Param("w.userId","LIKE", "dubic"));
//        jpa.addParam(new Param("w.amount",">", 0));
//        jpa.addOr(new Param("w.transId","LIKE", "dubic"));
//        jpa.addParam(new Param("w.amount","<", 500));
//        jpa.addHaving(new Param("SUM(w.amount)",">", 2000));
//        jpa.addHaving(new Param("SUM(w.amount)","<", 10000));
//        jpa.setOrder(new Param("w.id", false));

        ///
        DTSQLBuilder sql = new DTSQLBuilder("SELECT w.user_id,SUM(w.amount) FROM Winning w");
        sql.param("w.userId LIKE", "%dubic")
                .param("w.amount >", 0)
                .having("w.amount >", 2000)
                .order("w.play_date", false)
                .group("w.id")
                .limit(0, 25)
                .count("SELECT COUNT(w.id) FROM winning w");
        String[] toSQL = sql.toSQL();
        System.out.println(toSQL[0]);
        System.out.println(toSQL[1]);
    }

}
