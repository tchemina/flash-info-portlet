package org.esco.portlet.model;

import java.io.Serializable;

/**
 * Created by jgribonvald on 23/09/16.
 */
public class Rubrique implements Serializable {
    private String name;
    private String color;

    public Rubrique() {
        super();
    }

    public Rubrique(String name, String color) {
        super();
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }
}
