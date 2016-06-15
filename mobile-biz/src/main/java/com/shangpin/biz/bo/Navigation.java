package com.shangpin.biz.bo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="navigation")
public class Navigation implements Serializable, Cloneable {
    private static final long serialVersionUID = -1397118043106425158L;
    private String id;
    private String text;
    private String link;
    private String parentId;

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getLink() {
        return link;
    }

    public String getParentId() {
        return parentId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Object clone() {
        Navigation o = null;
        try {
            o = (Navigation) super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        return o;
    }
}
