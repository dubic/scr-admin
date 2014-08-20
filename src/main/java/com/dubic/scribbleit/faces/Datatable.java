/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbleit.faces;

import com.dubic.scribbles.admin.Model;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "dataTable")
public class Datatable extends UIComponentBase {

    @Override
    public String getFamily() {
        return "com.dubic.scribbleit.faces";
    }

    @Override
    public void decode(FacesContext context) {
        super.decode(context);
        ValueExpression valueExpression = getValueExpression("value");
        HttpServletRequest v = (HttpServletRequest) valueExpression.getValue(context.getELContext());
        System.out.println("value is = " + v.getQueryString());
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ValueExpression valueExpression = getValueExpression("value");
        List<Model> models = (List<Model>) valueExpression.getValue(context.getELContext());
//        System.out.println("value is = " + models);

//        ValueExpression varExpression = getValueExpression("var");
//        String var = (String) varExpression.getValue(context.getELContext());
//        System.out.println("var is = " + var);
        
        for (UIComponent uIComponent : this.getChildren()) {
            System.out.println(((Column)uIComponent).getName());
        }

        ResponseWriter w = context.getResponseWriter();
        w.startElement("table", this);
        w.writeAttribute("class", "table", null);
        Map<String, UIComponent> facets = getFacets();
        for (UIComponent facet : facets.values()) {
            facet.encodeAll(context);
        }
        for (Model model : models) {
            w.startElement("tr", this);
            model.getClass().getField()
            renderCol(w, model.getName());
            renderCol(w, model.getAge());
            renderCol(w, model.getSex());
            w.endElement("tr");
        }
        w.endElement("table");
//        String value = (String) getAttributes().get("value");
//        if (value != null) {
//            ResponseWriter responseWriter = context.getResponseWriter();
//            responseWriter.write(value.toUpperCase());
//        }
    }

    private void renderCol(ResponseWriter w, Object o) throws IOException {
        w.startElement("td", this);
        w.writeText(o, null);
        w.endElement("td");
    }

    protected static enum PropertyKeys {

        source,
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
