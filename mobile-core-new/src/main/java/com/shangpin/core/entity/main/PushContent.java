package com.shangpin.core.entity.main;

public class PushContent {

    private String url;
    private String topicid;
    private String actid;
    private String productid;
    private String productSource;
    private String categoryid;
    private String openurl;
    private String otherurl;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the topicid
     */
    public String getTopicid() {
        return topicid;
    }

    /**
     * @param topicid
     *            the topicid to set
     */
    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    /**
     * @return the actid
     */
    public String getActid() {
        return actid;
    }

    /**
     * @param actid
     *            the actid to set
     */
    public void setActid(String actid) {
        this.actid = actid;
    }

    /**
     * @return the productid
     */
    public String getProductid() {
        return productid;
    }

    /**
     * @param productid
     *            the productid to set
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return the productSource
     */
    public String getProductSource() {
        return productSource;
    }

    /**
     * @param productSource
     *            the productSource to set
     */
    public void setProductSource(String productSource) {
        this.productSource = productSource;
    }

    /**
     * @return the categoryid
     */
    public String getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     *            the categoryid to set
     */
    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return the openurl
     */
    public String getOpenurl() {
        return openurl;
    }

    /**
     * @param openurl
     *            the openurl to set
     */
    public void setOpenurl(String openurl) {
        this.openurl = openurl;
    }

    /**
     * @return the otherurl
     */
    public String getOtherurl() {
        return otherurl;
    }

    /**
     * @param otherurl
     *            the otherurl to set
     */
    public void setOtherurl(String otherurl) {
        this.otherurl = otherurl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "PushContent [url=" + url + ", topicid=" + topicid + ", actid=" + actid + ", productid=" + productid + ", productSource=" + productSource + ", categoryid="
                + categoryid + ", openurl=" + openurl + ", otherurl=" + otherurl + "]";
    }

}
