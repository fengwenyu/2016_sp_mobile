package com.shangpin.core.entity.main;

public class Aps {

    private String sound;
    private String alert;
    private String badge;

    /**
     * @return the sound
     */
    public String getSound() {
        return sound;
    }

    /**
     * @param sound
     *            the sound to set
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * @return the alert
     */
    public String getAlert() {
        return alert;
    }

    /**
     * @param alert
     *            the alert to set
     */
    public void setAlert(String alert) {
        this.alert = alert;
    }

    /**
     * @return the badge
     */
    public String getBadge() {
        return badge;
    }

    /**
     * @param badge
     *            the badge to set
     */
    public void setBadge(String badge) {
        this.badge = badge;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Aps [sound=" + sound + ", alert=" + alert + ", badge=" + badge + "]";
    }

}
