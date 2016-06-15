package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * WeixinPayOrder 
 */
@Entity
@Table(name = "weixinPayOrder")
public class WeixinPayOrder implements java.io.Serializable {

    private static final long serialVersionUID = -3124830997797452582L;
    private Long id;
    private String bankBillNo;
    private String bankType;
    private String feeType;
    private String openId;
    private String orderNo;
    private String payInfo;
    private Date timeEnd;
    private BigDecimal totalFee;
    private String tradeMode;
    private String tradeState;
    private String transId;
    private BigDecimal transportFee;
    private String buyerAlias;
    private BigDecimal discount;
    private String notifyId;
    private BigDecimal productFee;

    public WeixinPayOrder() {
    }

    public WeixinPayOrder(String bankBillNo, String bankType, String feeType, String openId, String orderNo, String payInfo, Date timeEnd, BigDecimal totalFee, String tradeMode,
            String tradeState, String transId, BigDecimal transportFee, String buyerAlias, BigDecimal discount, String notifyId, BigDecimal productFee) {
        this.bankBillNo = bankBillNo;
        this.bankType = bankType;
        this.feeType = feeType;
        this.openId = openId;
        this.orderNo = orderNo;
        this.payInfo = payInfo;
        this.timeEnd = timeEnd;
        this.totalFee = totalFee;
        this.tradeMode = tradeMode;
        this.tradeState = tradeState;
        this.transId = transId;
        this.transportFee = transportFee;
        this.buyerAlias = buyerAlias;
        this.discount = discount;
        this.notifyId = notifyId;
        this.productFee = productFee;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "bankBillNo", length = 100)
    public String getBankBillNo() {
        return this.bankBillNo;
    }

    public void setBankBillNo(String bankBillNo) {
        this.bankBillNo = bankBillNo;
    }

    @Column(name = "bankType", length = 50)
    public String getBankType() {
        return this.bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    @Column(name = "feeType", length = 20)
    public String getFeeType() {
        return this.feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Column(name = "openId", length = 100)
    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "orderNo", length = 100)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "payInfo", length = 100)
    public String getPayInfo() {
        return this.payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timeEnd", length = 19)
    public Date getTimeEnd() {
        return this.timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Column(name = "totalFee")
    public BigDecimal getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    @Column(name = "tradeMode", length = 20)
    public String getTradeMode() {
        return this.tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    @Column(name = "tradeState", length = 20)
    public String getTradeState() {
        return this.tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    @Column(name = "transId", length = 100)
    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    @Column(name = "transportFee")
    public BigDecimal getTransportFee() {
        return this.transportFee;
    }

    public void setTransportFee(BigDecimal transportFee) {
        this.transportFee = transportFee;
    }

    @Column(name = "buyerAlias", length = 100)
    public String getBuyerAlias() {
        return this.buyerAlias;
    }

    public void setBuyerAlias(String buyerAlias) {
        this.buyerAlias = buyerAlias;
    }

    @Column(name = "discount")
    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Column(name = "notifyId", length = 150)
    public String getNotifyId() {
        return this.notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    @Column(name = "productFee")
    public BigDecimal getProductFee() {
        return this.productFee;
    }

    public void setProductFee(BigDecimal productFee) {
        this.productFee = productFee;
    }

}
