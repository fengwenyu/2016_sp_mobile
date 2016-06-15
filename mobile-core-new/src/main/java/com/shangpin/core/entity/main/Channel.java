package com.shangpin.core.entity.main;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.shangpin.core.entity.Idable;

/**
 * 渠道管理实体
 * @author cuibinqiang
 */
@Entity
@Table(name = "manage_channel")
public class Channel implements Idable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "channel_name", length = 40)
    private String channelName;// 渠道名称

    @Column(name = "channel_num")
    private Long channelNum;// 渠道编号

    @Column(name = "invitation_code", length = 20)
    private String invitationCode;// 邀请码

    @Column(name = "create_time", length = 19)
    private Date createTime;// 创建时间

    public Channel() {
    }

    public Channel(String channelName, Long channelNum, String invitationCode, Date createTime) {
        this.channelName = channelName;
        this.channelNum = channelNum;
        this.invitationCode = invitationCode;
        this.createTime = createTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Long getChannelNum() {
        return this.channelNum;
    }

    public void setChannelNum(Long channelNum) {
        this.channelNum = channelNum;
    }

    public String getInvitationCode() {
        return this.invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}