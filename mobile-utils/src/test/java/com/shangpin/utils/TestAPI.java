package com.shangpin.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAPI implements Runnable {

    public static Logger logger = LoggerFactory.getLogger(TestAPI.class);

    private static Map<Integer, String> apiMap = new HashMap<Integer, String>();
    static {
        apiMap.put(1, "http://mobile.apiv2.shangpin.com/shangpin/SPProductDetail/?productid=05355113&pich=%7Bh%7D&picw=%7Bw%7D");
        apiMap.put(2, "http://mobile.apiv2.aolai.com/aolai/subjectproductlist/?pagesize=500&subjectNo=40516505&pageindex=1&picurlh=376&picurlw=282");
        apiMap.put(
                3,
                "http://mobile.apiv2.shangpin.com/shangpin/GetPicUrlsByGet?pictype=product&pich=%7Bh%7D&picw=%7Bw%7D&picnos=20140126183410757879%2C20140126183410898292%2C20131220110724239227%2C20131220110724239227%2C20131220110820337475%2C20131220110820337475%2C20130519211843058646%2C20130519211843152356%2C20131220114544853341%2C20131220114544853341%2C20140218163011312540%2C20140218163114734317%2C20140428213230373729%2C20140428213230482934%2C20140214121008202895%2C20140312133037451892%2C20140521005252650610%2C20140521002610309370%2C20130927221307917457%2C20130927221407509525%2C20131220110408306688%2C20131220110408306688%2C20140304151456761995%2C20140304151500412361%2C20140511002001694181%2C20140510234759021961%2C20140510234824106864%2C20140510234829052010%2C20140510234859113266%2C20140510234904105261%2C20121227214315007860%2C20121227214514620566%2C20140425160443048045%2C20140425160443157387%2C20140507124711403034%2C20140507124716410615%2C20140325233026054763%2C20140325233125740416%2C20131010161709276737%2C20131010161709276737%2C20140314175304562268%2C20140314175304671473%2C20140214120952150451%2C20140312133037124234%2C20131220110601434398%2C20131220110601434398%2C20140126135219516987%2C20140126135219626182%2C20140320131936190485%2C20140320131936299638%2C20140428213250404114%2C20140428213250513318%2C20140430115739296219%2C20140430115739405424%2C20140303103956609962%2C20140303104000400716%2C20140430115258464543%2C20140430115258573748%2C20140520233210183784%2C20140520233700640696%2C20140227141251275240%2C20140227141254426426%2C20140311165955978210%2C20140311165956072020%2C20140227141210761941%2C20140227141214537228%2C20140317173517810123%2C20140317173517919318%2C20140519112053824465%2C20140518153507284519%2C20140227141226674056%2C20140227141230542879%2C20140520233100092756%2C20140520233110107922%2C20140320132006329776%2C20140320132015908158%2C20140510232417063889%2C20140510230640520852%2C20140321191752048393%2C20140307182120820477");
        apiMap.put(
                4,
                "http://mobile.apiv2.shangpin.com/aolai/AddConsigneeAddress/?area=1061&consigneename=%E5%85%B0%E7%82%9C&address=%E7%A6%8F%E5%BB%BA%E7%9C%81%E7%A6%8F%E5%B7%9E%E5%B8%82%E6%99%8B%E5%AE%89%E5%8C%BA%E4%BA%94%E5%9B%9B%E5%8C%97%E4%B8%89%E7%9B%9B%E6%9E%9C%E5%B2%AD%E7%94%9F%E6%B4%BB6%23308%EF%BC%88%E5%8D%97%E5%B9%B3%E4%B8%9C%E8%B7%AF100%E5%8F%B7%EF%BC%89&tel=15960186716&province=13&userid=9F43531919CCF825A4797E06D80CED4D&setd=true&postcode=350012&city=115");
        apiMap.put(5, "http://mobile.apiv2.shangpin.com/aolai/getorderdetail/?pich=160&picw=120&userid=03999AA34440894DCC7B04F923C2CC23&orderid=20140521808856");
        apiMap.put(6, "http://mobile.apiv2.shangpin.com/aolai/getorder/?userid=168063FEECAED40ED067FD7A0C09B7ED&orderid=20140521807049");
        apiMap.put(7,
                "http://mobile.apiv2.shangpin.com/aolai/ordermanage/?pagesize=10&flag=1&picw=120&pich=160&pageindex=1&userid=017D667B77CBB299BAFD9EA376982404&ordertype=1&statustype=all");
        apiMap.put(
                8,
                "http://mobile.apiv2.shangpin.com/aolai/ConfirmOrder/?orderfrom=19&buysids=011451288c244b8990fdd73f1616332c%7Cf9768b8d775e40a79c3c2e96b100c0d3%7Ca1d8b70ca26a4be990ea7ddb0bf8b2fb&couponflag=&userid=AAC64226B327CEA64E9442EA6D899FF0&ordertype=1&express=1&invoicetype=1&errorskus=&invoicecontent=%E5%95%86%E5%93%81%E5%85%A8%E7%A7%B0&invoiceflag=1&shoptype=1&invoiceaddrid=144680&addrid=144680&invoicetitle=&coupon=0");
        apiMap.put(9, "http://mobile.apiv2.shangpin.com/aolai/payconfirmpayment/?paytypechildId=37&paytypeId=20&orderno=20140219665872");
        apiMap.put(10,
                "http://mobile.apiv2.shangpin.com/aolai/SelectShoppingCartList/?detailpicw=450&detailpich=600&shoptype=1&picw=120&pich=160&userid=03836BA897C030CE658DBD5D0E0DCB99");
        apiMap.put(
                11,
                "http://mobile.apiv2.shangpin.com/aolai/sendverifycode/?msgtemplate=%E5%B0%8A%E6%95%AC%E7%9A%84%E5%AE%A2%E6%88%B7%EF%BC%8C%E5%B0%86%E6%89%8B%E6%9C%BA%E5%8F%B7%E7%A0%81%E4%B8%8E%E6%82%A8%E7%9A%84%E8%B4%A6%E6%88%B7%E8%BF%9B%E8%A1%8C%E7%BB%91%E5%AE%9A%E7%9A%84%E9%AA%8C%E8%AF%81%E7%A0%81%E4%B8%BA%7B%24verifyCode%24%7D&userid=1D0069944B82918705CC15C3B7FAC9AD&phonenum=13656843155");
        apiMap.put(12, "http://mobile.apiv2.shangpin.com/aolai/userbuyinfo/?shoptype=1&userid=017D667B77CBB299BAFD9EA376982404");
        apiMap.put(13, "http://mobile.apiv2.shangpin.com/aolai/userbuyinfo/?shoptype=1&userid=5B1A488B8018279EAE7113C3A54D60FA");
        apiMap.put(14, "http://mobile.apiv2.shangpin.com/shangpin/coupons/?pagesize=10&shoptype=1&pageindex=1&userid=034D44A8C0DC300670F39015AE1215C8&coupontype=0");
        apiMap.put(15, "http://mobile.apiv2.shangpin.com/shangpin/flagship/?brandno=B0059");
        apiMap.put(16,
                "http://mobile.apiv2.shangpin.com/shangpin/Logistics/?pagesize=20&isall=1&flag=1&picw=120&pich=160&userid=02CC3B1252F194C14B2578699C7A1D16&pageindex=1&ordertype=1");
        apiMap.put(17, "http://mobile.apiv2.shangpin.com/shangpin/man9grids/?picw=640&pich=320&gender=1");
        apiMap.put(18, "http://mobile.apiv2.shangpin.com/shangpin/OrderLogistic/?flag=1&picw=120&pich=160&userid=017D667B77CBB299BAFD9EA376982404&orderid=20140520791063");
        apiMap.put(20, "http://mobile.apiv2.shangpin.com/shangpin/selectCoupon/?pagesize=1000&shoptype=1&pageindex=1&userid=03999AA34440894DCC7B04F923C2CC23");
        apiMap.put(21, "http://mobile.apiv2.shangpin.com/shangpin/SPLogisticsDetail/?userid=017D667B77CBB299BAFD9EA376982404&ticketno=904297551640&orderid=20140520791063");
        apiMap.put(22,
                "http://mobile.apiv2.shangpin.com/shangpin/SPNewProducts/?categoryid=A01&pich=422&picw=318&userid=03308FD61EE3B6C333ED4B481A9ECF31&pageSize=20&pageindex=1&gender=0");
        apiMap.put(23, "http://mobile.apiv2.shangpin.com/shangpin/SPNewTopicProducts/?pagesize=20&topicid=20130401564&picw=318&pich=422&filtersold=0&pageindex=1&userid=&gender=0");
        apiMap.put(
                24,
                "http://mobile.apiv2.shangpin.com/shangpin/SPProductsInCateAndBrand/?pagesize=20&categoryid=A01B01C01&pich=422&picw=318&filtersold=0&userid=1B047D7136AA4277FE9B4DB24106E7E6&pageindex=10&gender=0");
        apiMap.put(25,
                "http://mobile.apiv2.shangpin.com/shangpin/updateConfirmOrderInfo/?buysids=05da08cd484a453686119a346f160b13&couponflag=1&userid=3FB3EEFAB461F102333CF75E6B5354D9&addrid=");
        apiMap.put(26, "http://mobile.apiv2.shangpin.com/aolai/chagePayMode/?subpaymode=37&mainpaymode=20&userid=168063FEECAED40ED067FD7A0C09B7ED&orderid=20140521807049");
        apiMap.put(27, "http://mobile.apiv2.shangpin.com/aolai/cancelorder/?userid=42EE5D55FB85A17A6A227C475D5EAAE4&orderid=20140521807730");
        apiMap.put(28, "http://mobile.apiv2.shangpin.com/aolai/provincelist/");
        apiMap.put(29,
                "http://mobile.apiv2.shangpin.com/aolai/SelectShoppingCartList/?detailpicw=450&detailpich=600&shoptype=1&picw=120&pich=160&userid=03836BA897C030CE658DBD5D0E0DCB99");
        apiMap.put(30, "http://mobile.apiv2.shangpin.com/aolai/sendretrievepwdemail/?mailaddress=");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(new TestAPI());
            t.start();
        }

    }

    @Override
    public void run() {
        Map<Integer, Long> sortMap = new HashMap<Integer, Long>();
        Iterator<Map.Entry<Integer, String>> it = apiMap.entrySet().iterator();
        while (it.hasNext()) {
            long startTime = System.currentTimeMillis();
            Map.Entry<Integer, String> entry = it.next();
            Integer key = entry.getKey();
            String url = entry.getValue();
            HttpClientUtil.doGet(url, null);
            long endTime = System.currentTimeMillis(); // 获取结束时间
            long useTime = (endTime - startTime) / 1000;
            if (useTime >= 10) {
                if (sortMap.containsKey(key)) {
                    Long max = sortMap.get(key);
                    if (useTime > max) {
                        sortMap.put(key, useTime);
                    }
                } else {
                    sortMap.put(key, useTime);
                }
                // logger.info(key + " 程序运行时间： " + useTime + "s");
            }
        }
        printMap(sortMap);

    }

    /**
     * 
     * @param sortMap
     */
    private void printMap(Map<Integer, Long> sortMap) {
        Iterator<Map.Entry<Integer, Long>> it = sortMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Long> entry = it.next();
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
    }
}
