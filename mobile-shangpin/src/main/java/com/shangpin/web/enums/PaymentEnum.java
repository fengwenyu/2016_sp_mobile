package com.shangpin.web.enums;

import com.shangpin.web.vo.Payment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  liushaoqing
 * M 站支付方式枚举汇总
 */
public enum PaymentEnum {

    //国内
    //微信app支付
    WEIXINPUB ("27", "58", "微信支付", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.MWX_CHANNEL, PaymentEnum.WEY_ONLION,"weixinPay","/pay/weixin/WEIXINPUB"),
    //微信app 调微信海外
    WEIXINSEAWAP ("32", "111", "微信支付", PaymentEnum.ABOROAD_PAY,
            PaymentEnum.MWX_CHANNEL, PaymentEnum.WEY_ONLION,"weixinPay","/pay/weixin/WEIXINPUBSEA"),

    //微信wap页支付
    WEIXINWAP ("27", "117", "微信支付", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL, PaymentEnum.WEY_ONLION,"weixinPay","/pay/weixin/WEIXINWAP"),

    ALIWAP  ("20", "37", "支付宝支付", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL, PaymentEnum.WEY_ONLION,"alipay","/pay/alipay/ALIWAP"),
    //直接跳转到银联安装页面
    UNWAP    ("19", "49", "银联支付", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL, PaymentEnum.WEY_ONLION,"unionPay","/order/install"),

    POS     ("2", "41", "货到付款POS机", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL,PaymentEnum.WEY_OUTLION,"postPay","/pay/hdpay/success"),

    CASH    ("2", "41", "货到付款现金", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL,PaymentEnum.WEY_OUTLION,"cashPay","/pay/hdpay/success"),

 /*   PUFABK  ("2", "41", "浦发银行", PaymentEnum.DOMESTIC_PAY,
            PaymentEnum.M_CHANNEL,PaymentEnum.WEY_OUTLION,"cashPay","/pay/hdpay/success"),*/

    ALISEA  ("30", "121", "支付宝支付", PaymentEnum.ABOROAD_PAY,
            PaymentEnum.M_CHANNEL, PaymentEnum.WEY_ONLION,"alipay","/pay/alipay/ALIWAP"),
    //分账
    ALIFZ  ("30", "121", "支付宝支付", PaymentEnum.ALIPART_PAY,
            PaymentEnum.M_CHANNEL, PaymentEnum.WEY_ONLION,"alipay","/pay/alipay/ALIFZWAP");

    public static final String DOMESTIC_PAY ="1";//国内支付
    public static final String ABOROAD_PAY = "2";//国外支付
    public static final String ALIPART_PAY = "3";//阿里分账
    public static final String M_CHANNEL="0"; //M站
    public static final String MWX_CHANNEL="1";// M站微信
    public static final String WEY_ONLION = "0";//线上支付
    public static final String WEY_OUTLION = "1";//线下支付

    public static final PaymentEnum DOMESTIC_DEFAULT = WEIXINWAP ;
    public static final PaymentEnum ABOROASD_DEFAULT = ALISEA;
    public static final PaymentEnum ALIPAERT_DEFAULT = ALIFZ;
    public static final PaymentEnum WEIXIN_DEFAULT = WEIXINPUB;



    PaymentEnum(String id, String subId, String name, String type, String channel, String wey,String clazz,String url) {
        this.id = id;
        this.subId = subId;
        this.name = name;
        this.type = type;
        this.channel = channel;
        this.wey = wey;
        this.clazz = clazz;
        this.url = url;
    }

    //支付id
    final private String id;
    //子的支付id
    final private String subId;
    //支付名称
    final private String name;
    //1国内 2海外 3分账
    private String type;
    //渠道(m站，和微信浏览器m站)
    final private String channel;
    //1 线上支付 2线下支付
    final private String wey;

    final private String clazz;//图标样式

    final private String url;

    /**
     * 通过类型和渠道,是否支持线下方式获取可用的支付方式
     * @param type
     * @param channel
     * @param wey
     * @param 有无浦发银行
     * @return
     */
    public static List<Payment> getPayment(String type, String channel, boolean wey){

       List<Payment> payments = new ArrayList<Payment>();

       //获取默认的方式
       PaymentEnum defaultSelectPay = getDefaultSelectPay(type, channel);
       List<PaymentEnum> payEnumList = getPayEnumList(type, channel, wey);

       for (PaymentEnum payenum: payEnumList) {

          Payment pay = new Payment();
          pay.setId(payenum.id);
          pay.setSubpayCode(payenum.subId);
          pay.setName(payenum.name);
           pay.setWay(payenum.wey);
          //设置默认选中
          if(defaultSelectPay==payenum) {
             pay.setIsSelected("1");
          }else{
             pay.setIsSelected("0");
          }
          pay.setClazz(payenum.clazz);
          pay.setUrl(payenum.url);
          payments.add(pay);
       }


       return payments;
    }

    private static List<PaymentEnum> getPayEnumList(String type,String channel,boolean wey){

        List<PaymentEnum> lists = new ArrayList<PaymentEnum>();
        if(channel.equals(PaymentEnum.MWX_CHANNEL)){
            if(type.equals(DOMESTIC_PAY)){
                lists.add(WEIXINPUB);
            }else if(type.equals(ABOROAD_PAY)){
                lists.add(WEIXINSEAWAP);
            }
            return lists;
        }

        if(type.equals(PaymentEnum.DOMESTIC_PAY)){
            lists.add(WEIXINWAP);
            lists.add(ALIWAP);
            lists.add(PaymentEnum.UNWAP);
            if(wey){
                lists.add(CASH);
                lists.add(POS);
            }
        }else if(type.equals(PaymentEnum.ABOROAD_PAY)){
            lists.add(ALISEA);
        }else if(type.equals(PaymentEnum.ALIPART_PAY)){
            lists.add(ALIFZ);
        }
        return lists;
    }

    /**
     * 通过类型和渠道获取默认的支付方式
     * @param type
     * @param channel
     * @return
     */
    private static PaymentEnum getDefaultSelectPay(String type,String channel){

        if(type.equals(ALIPART_PAY)){
            return PaymentEnum.ALIPAERT_DEFAULT;
        }else{
            if(channel.equals(MWX_CHANNEL)){
                if(type.equals(DOMESTIC_PAY)){
                    return PaymentEnum.WEIXINPUB;
                }else{
                    return PaymentEnum.WEIXINSEAWAP;
                }
            }else if(type.equals(DOMESTIC_PAY) ){
                return PaymentEnum.DOMESTIC_DEFAULT;
            }else if(type.equals(ABOROAD_PAY)){
                return PaymentEnum.ABOROASD_DEFAULT;
            }else{
                return null;
            }
        }
    }

    public static void main(String[] args) {
        List<Payment> payment = getPayment(PaymentEnum.DOMESTIC_PAY, PaymentEnum.MWX_CHANNEL, true);
        for (Payment p : payment) {
            System.out.println(p.getName()+p.getId()+":"+p.getIsSelected());
        }
    }
}
