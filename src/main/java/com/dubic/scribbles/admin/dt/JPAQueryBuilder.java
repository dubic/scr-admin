package com.dubic.scribbles.admin.dt;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Parameter;
import javax.persistence.Query;

public class JPAQueryBuilder {
   private StringBuilder strBuilder = null;
    private boolean hasWhere;
    private boolean hasHaving;
    
    private List<Param> params = new ArrayList<Param>();//t.provider = 'common';:prov
    private List<Param> orList = new ArrayList<Param>();//t.provider = 'common';:prov
    private List<Param> havingList = new ArrayList<Param>();//t.provider = 'common';:prov
    private Param order;
    private String countp;
    private int counter = 0;

    public JPAQueryBuilder(String sql) {
         this.strBuilder = new StringBuilder(sql);
    }
    
    public void addParam(Param param){
        this.params.add(param);
    }
    
    public void addOr(Param or){
        this.orList.add(or);
    }
    public void addHaving(Param h){
        this.havingList.add(h);
    }

    public void setOrder(Param order) {
        this.order = order;
    }

    public void setCountp(String countp) {
        this.countp = countp;
    }
    
    
    
    public String toSQL(){
        if (!orList.isEmpty()) {
            whereAnd();
            this.strBuilder.append(createOrQuery());
        }
        if (!params.isEmpty()) {
            whereAnd();
            this.strBuilder.append(createParamQuery());
        }
        if (!havingList.isEmpty()) {
            havingAnd();
            this.strBuilder.append(createHavingQuery());
        }
        order(this.order.getName(), (Boolean)order.getValue());
        
        
        return this.strBuilder.toString();
    }
    
    public Query toJPQL(Query q){
        counter = 0;
        for (Param param : orList) {
            q.setParameter("p"+counter, param.getValue());
            counter++;
        }
        for (Param param : params) {
            q.setParameter("p"+counter, param.getValue());
            counter++;
        }
        for (Param param : havingList) {
            q.setParameter("p"+counter, param.getValue());
            counter++;
        }
        return q;
    }
    
    private String createOrQuery() {
        StringBuffer buf = new StringBuffer();
//        whereAnd();
        for (Iterator<Param> it = orList.iterator(); it.hasNext();) {
            Param param = it.next();
            buf.append(param.getName()).append(" ").append(param.getOperator()).append(" ").append(":p").append(counter);
            counter++;
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
            buf.append(param.getName()).append(" ").append(param.getOperator()).append(" ").append(":p").append(counter);
            counter++;
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
            buf.append(param.getName()).append(" ").append(param.getOperator()).append(" ").append(":p").append(counter);
            counter++;
            if (it.hasNext()) {
                buf.append(" AND ");
            }
        }
        return buf.toString();
    }
    
    

    public JPAQueryBuilder eq(String key, String value) {
        /*  32 */ whereAnd();
        /*  33 */ this.strBuilder.append(key).append("=").append(value).append("");
        /*  34 */ return this;
    }

    public JPAQueryBuilder param(String condition) {
        whereAnd();
        this.strBuilder.append(condition);
        return this;
    }

    public JPAQueryBuilder eq(String key, int value) {
        /*  48 */ whereAnd();
        /*  49 */ this.strBuilder.append(key).append("=").append(value);
        /*  50 */ return this;
    }

    public JPAQueryBuilder gt(String key, int value) {
        /*  54 */ whereAnd();
        /*  55 */ this.strBuilder.append(key).append(" > ").append(value);
        /*  56 */ return this;
    }

    public JPAQueryBuilder like(String key, String value) {
        /*  66 */ whereAnd();
        /*  67 */ this.strBuilder.append(key).append(" like ").append("'").append(value).append("'");
        /*  68 */ return this;
    }

    public JPAQueryBuilder contains(String key, String value) {
        /*  78 */ whereAnd();
        /*  79 */ this.strBuilder.append(key).append(" like ").append("'%").append(value).append("%'");
        /*  80 */ return this;
    }

    public JPAQueryBuilder groupBy(String stmt) {
        /*  84 */ this.strBuilder.append(" GROUP BY ").append(stmt);
        /*  85 */ return this;
    }

    public JPAQueryBuilder having(String stmt) {
        havingAnd();
        this.strBuilder.append(stmt);
        return this;
    }

    public JPAQueryBuilder order(String key, boolean asc) {
        /*  99 */ this.strBuilder.append(" ORDER BY ").append(key);
        /* 100 */ if (!asc) {
            /* 101 */ this.strBuilder.append(" desc");
        }
        /* 103 */ return this;
    }

    public JPAQueryBuilder limit(int n) {
        /* 107 */ this.strBuilder.append(" limit ").append(n);
        /* 108 */ return this;
    }

    public String toString() {
        /* 113 */ return this.strBuilder.toString();
    }

    private void whereAnd() {
        if (!this.hasWhere) {
            this.strBuilder.append(" WHERE ");
            this.hasWhere = true;
            return;
        }
        this.strBuilder.append(" AND ");
    }
    
    private void havingAnd() {
        if (!this.hasHaving) {
            this.strBuilder.append(" HAVING ");
            this.hasHaving = true;
            return;
        }
        this.strBuilder.append(" AND ");
    }

    public static void main(String[] args) {
        JPAQueryBuilder jpa = new JPAQueryBuilder("SELECT w.userId,SUM(w.amount) FROM Winning w");
//        jpa.having("SUM(w.amount) > 0").having("SUM(w.amount) < 500");
        jpa.addOr(new Param("w.userId","LIKE", "dubic"));
        jpa.addParam(new Param("w.amount",">", 0));
        jpa.addOr(new Param("w.transId","LIKE", "dubic"));
        jpa.addParam(new Param("w.amount","<", 500));
        jpa.addHaving(new Param("SUM(w.amount)",">", 2000));
        jpa.addHaving(new Param("SUM(w.amount)","<", 10000));
        jpa.setOrder(new Param("w.id", false));
        System.out.println(jpa.toSQL());
    }

    
}
//SELECT e
//FROM Employee e JOIN FETCH e.address