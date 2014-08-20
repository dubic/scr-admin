/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dubic.scribbleit.faces;

import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author dubem
 */
@FacesComponent(createTag = true, tagName = "datePicker")
public class Datepicker extends UIComponentBase {
    
    @Override
    public String getFamily() {
        return "com.dubic.scribbleit.faces";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        String format = (String) getAttributes().get(Datepicker.PropertyKeys.format.toString());
        String styleClass = (String) getAttributes().get(Datepicker.PropertyKeys.styleClass.toString());
        
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", null);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("class", "datepicker "+styleClass, null);
        if (format != null) {
            writer.writeAttribute("data-date-format", "dd-mm-yyyy", null);
        }
        writer.endElement("input");
    }
    protected static enum PropertyKeys {

        format,
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
