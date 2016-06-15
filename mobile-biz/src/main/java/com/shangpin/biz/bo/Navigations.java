package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "navigations")
public class Navigations implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Navigation> navigation;

    public List<Navigation> getNavigation() {
        return navigation;
    }

    public void setNavigation(List<Navigation> navigation) {
        this.navigation = navigation;
    }

}
