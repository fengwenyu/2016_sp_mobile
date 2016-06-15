package com.shangpin.core.entity.main;

import java.util.Date;

public class PushInfo {

    private long id;
    private String title;
    private String action;
    private String actionarg;
    private Date showTime;
    private Aps aps;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the actionarg
     */
    public String getActionarg() {
        return actionarg;
    }

    /**
     * @param actionarg
     *            the actionarg to set
     */
    public void setActionarg(String actionarg) {
        this.actionarg = actionarg;
    }

    /**
     * @return the showTime
     */
    public Date getShowTime() {
        return showTime;
    }

    /**
     * @param showTime
     *            the showTime to set
     */
    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    /**
     * @return the aps
     */
    public Aps getAps() {
        return aps;
    }

    /**
     * @param aps
     *            the aps to set
     */
    public void setAps(Aps aps) {
        this.aps = aps;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PushInfo [id=" + id + ", title=" + title + ", action=" + action + ", actionarg=" + actionarg + ", showTime=" + showTime + ", aps=" + aps + "]";
    }

}
