package com.shangpin.core.entity;

// Generated 2014-5-26 18:24:56 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BestpayorderShangpin 
 */
@Entity
@Table(name = "bestpayorder_shangpin")
public class BestpayorderShangpin implements java.io.Serializable {

    private static final long serialVersionUID = 7026442490334479476L;
    private Long id;
    private String partnerid;
    private String partnername;
    private String supplyorgcode1;
    private String supplyorgcode2;
    private String supplyorgcode3;
    private String productno;
    private String partnerorderid;
    private String orderid;
    private String txnamount;
    private String rating;
    private String goodsname;
    private String goodscount;
    private String sig;

    public BestpayorderShangpin() {
    }

    public BestpayorderShangpin(String partnerid, String partnername, String supplyorgcode1, String supplyorgcode2, String supplyorgcode3, String productno, String partnerorderid,
            String orderid, String txnamount, String rating, String goodsname, String goodscount, String sig) {
        this.partnerid = partnerid;
        this.partnername = partnername;
        this.supplyorgcode1 = supplyorgcode1;
        this.supplyorgcode2 = supplyorgcode2;
        this.supplyorgcode3 = supplyorgcode3;
        this.productno = productno;
        this.partnerorderid = partnerorderid;
        this.orderid = orderid;
        this.txnamount = txnamount;
        this.rating = rating;
        this.goodsname = goodsname;
        this.goodscount = goodscount;
        this.sig = sig;
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

    @Column(name = "partnerid", length = 50)
    public String getPartnerid() {
        return this.partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    @Column(name = "partnername", length = 30)
    public String getPartnername() {
        return this.partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }

    @Column(name = "supplyorgcode1", length = 30)
    public String getSupplyorgcode1() {
        return this.supplyorgcode1;
    }

    public void setSupplyorgcode1(String supplyorgcode1) {
        this.supplyorgcode1 = supplyorgcode1;
    }

    @Column(name = "supplyorgcode2", length = 30)
    public String getSupplyorgcode2() {
        return this.supplyorgcode2;
    }

    public void setSupplyorgcode2(String supplyorgcode2) {
        this.supplyorgcode2 = supplyorgcode2;
    }

    @Column(name = "supplyorgcode3", length = 30)
    public String getSupplyorgcode3() {
        return this.supplyorgcode3;
    }

    public void setSupplyorgcode3(String supplyorgcode3) {
        this.supplyorgcode3 = supplyorgcode3;
    }

    @Column(name = "productno", length = 15)
    public String getProductno() {
        return this.productno;
    }

    public void setProductno(String productno) {
        this.productno = productno;
    }

    @Column(name = "partnerorderid", length = 15)
    public String getPartnerorderid() {
        return this.partnerorderid;
    }

    public void setPartnerorderid(String partnerorderid) {
        this.partnerorderid = partnerorderid;
    }

    @Column(name = "orderid", length = 15)
    public String getOrderid() {
        return this.orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    @Column(name = "txnamount", length = 12)
    public String getTxnamount() {
        return this.txnamount;
    }

    public void setTxnamount(String txnamount) {
        this.txnamount = txnamount;
    }

    @Column(name = "rating", length = 12)
    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Column(name = "goodsname", length = 100)
    public String getGoodsname() {
        return this.goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    @Column(name = "goodscount", length = 6)
    public String getGoodscount() {
        return this.goodscount;
    }

    public void setGoodscount(String goodscount) {
        this.goodscount = goodscount;
    }

    @Column(name = "sig", length = 2000)
    public String getSig() {
        return this.sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

}
