/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbles.admin;

/**
 *
 * @author dubem
 */
public class Model {
    private String name;
        private int age;
        private char sex;
        public Model(String n,int a,char s) {
            this.age = a;
            this.name = n;
            this.sex = s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public char getSex() {
            return sex;
        }

        public void setSex(char sex) {
            this.sex = sex;
        }
}
