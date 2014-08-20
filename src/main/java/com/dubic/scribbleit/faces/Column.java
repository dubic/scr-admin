/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbleit.faces;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIColumn;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "column")
public class Column extends UIColumn {

    @Override
    public String getFamily() {
        return "com.dubic.scribbleit.faces";
    }

    public String getName() {
        return (String) getStateHelper().eval(PropertyKeys.name);
    }

    public void setName(String name) {
        getStateHelper().put(PropertyKeys.name, name);
    }

//    @Override
//    public void encodeBegin(FacesContext context) throws IOException {
//        String value = (String) getAttributes().get("value");
//        if (value != null) {
//            ResponseWriter responseWriter = context.getResponseWriter();
//            responseWriter.write(value.toUpperCase());
//        }
//    }
    protected static enum PropertyKeys {

        name,
        footerClass,
        headerClass,
        rowHeader;

        String toString;

        private PropertyKeys(String toString) {
            this.toString = toString;
        }

        private PropertyKeys() {
        }

        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }

    }
}
