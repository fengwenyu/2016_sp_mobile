package com.shangpin.wireless.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;

public class Test {

    public static void main(String[] args) {
        long startTime = new Date().getTime();
        List<String> list = new ArrayList<String>(40000);
        ApnsService service = APNS.newService().withCert("D:/shangpinpush_development.p12", "1").withAppleDestination(false).asPool(15).withConnectTimeout(30000).build();
        PayloadBuilder builder = APNS.newPayload();
        builder.alertTitle("【尚品网】");
        builder.alertBody("VERSACE春夏男装来了，赶紧把自家男人打扮起来。");
        builder.alertAction("【尚品网】");
        builder.sound("default");
        builder.badge(1);

        String payload = builder.build();
        BufferedReader br = null; 
        service.push("fa5ef3e5aebf5e9b14345b650c2d6ffaf4b5e6f2f54d6f89147fd6a7953974a7", payload);
        
        try {
            // 构造BufferedReader对象
            br = new BufferedReader(new FileReader("D:/tokens.txt"));

            String token = null;
            while ((token = br.readLine()) != null) {
                list.add(token);
                if(checkList(list)){
                    service.push(list, payload);
                    list.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
            
        }finally {
            // 关闭BufferedReader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            service.stop();
        }
        long endTime = new Date().getTime();
        System.out.println("push end , cost time:" + (endTime - startTime)  + "ms");
//        Map<String, Date> inactiveDevices = service.getInactiveDevices();
//        System.out.println(inactiveDevices.size());
        // for (String deviceToken : inactiveDevices.keySet()) {
        // Date inactiveAsOf = inactiveDevices.get(deviceToken);
        // System.out.println(deviceToken);
        // }
        // SSLContext sslContext;
        // try {
        // sslContext = Utilities.newSSLContext(new FileInputStream(new
        // File("D:/shangpin.p12")), "1", "PKCS12", "sunx509");
        // ApnsService service =
        // APNS.newService().withSSLContext(sslContext).withAppleDestination(true).build();
        // Map<String, Date> inactiveDevices = service.getInactiveDevices();
        // System.out.println(inactiveDevices.size());
        // for (String deviceToken : inactiveDevices.keySet()) {
        // Date inactiveAsOf = inactiveDevices.get(deviceToken);
        // System.out.println(inactiveAsOf);
        // }
        // } catch (InvalidSSLConfig e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // };

    }

    private static boolean checkList(List<String> list) {
        if(list ==null){
            return false;
        }
        if(list.size()==40000){
            return true;
        }
        return false;
    }
}
