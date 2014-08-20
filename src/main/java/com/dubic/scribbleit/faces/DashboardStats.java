/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dubic.scribbleit.faces;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "statsBoard")
public class DashboardStats extends UIOutput {

    @Override
    public String getFamily() {
        return "com.dubic.scribbleit.faces";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String icon = (String) getAttributes().get(PropertyKeys.icon.toString());
        String color = (String) getAttributes().get(PropertyKeys.color.toString());

        String desc = (String) getAttributes().get(PropertyKeys.desc.toString());
        String viewMore = (String) getAttributes().get(PropertyKeys.viewMore.toString());
        String viewMoreRef = (String) getAttributes().get(PropertyKeys.viewMoreRef.toString());
        String viewMoreText = (String) getAttributes().get(PropertyKeys.viewMoreText.toString());
        ////////////////////////////////
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", null);
        writer.writeAttribute("class", "col-lg-3 col-md-3 col-sm-6 col-xs-12", null);
        //handle color
        String colorCls = "dashboard-stat ";
        if (color != null) {
            colorCls = colorCls + color + " ";
        }
        writer.startElement("div", null);
        writer.writeAttribute("class", colorCls, null);
        if (icon != null) {
            writer.startElement("div", null);
            writer.writeAttribute("class", "visual", null);
            writer.startElement("i", null);
            writer.writeAttribute("class", icon, null);
            writer.endElement("i");
            writer.endElement("div");
        }
        writer.startElement("div", null);
        writer.writeAttribute("class", "details", null);
        /////////////////////////
        writer.startElement("div", null);
        writer.writeAttribute("class", "number", null);
        writer.writeText(getConverter().getAsString(context, this, getValue()), null);
        writer.endElement("div");
        /////////////////////////
        writer.startElement("div", null);
        writer.writeAttribute("class", "desc", null);
        writer.writeText(desc, null);
        writer.endElement("div");
        ////////end details////////////
        writer.endElement("div");
        if (Boolean.valueOf(viewMore)) {
            writer.startElement("a", null);
            writer.writeAttribute("class", "more", null);
            if (viewMoreRef != null) {
                writer.writeAttribute("href", viewMoreRef, null);
            }
            writer.writeText(viewMoreText == null ? "View More" : viewMoreText, null);
            writer.startElement("i", null);
            writer.writeAttribute("class", "m-icon-swapright m-icon-white", null);
            writer.endElement("i");
            writer.endElement("a");
        }
        ////end//////////////////////
        writer.endElement("div");
        writer.endElement("div");
    }

   
    protected static enum PropertyKeys {

        icon,
        color,
        desc,
        viewMore,
        viewMoreRef,
        viewMoreText,
        style,
        styleClass;

        String toString;

        private PropertyKeys(String toString) {
            this.toString = toString;
        }

        private PropertyKeys() {
        }

        @Override
        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }

    }
}
