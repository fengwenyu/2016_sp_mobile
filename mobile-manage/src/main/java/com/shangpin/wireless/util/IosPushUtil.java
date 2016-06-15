package com.shangpin.wireless.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.json.JSONException;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.transmission.PushQueue;

public class IosPushUtil {
    public static String keystore = null;
    public static String password = null;
    public static String host = null;
    public static Boolean production = true;//true：production false: sandbox
    public static final int numberOfThreads = 8;
    static{
        Properties propertie = new Properties();
        InputStream inputStream;
         
        try {
            inputStream = IosPushUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            propertie.load(inputStream);
            keystore = propertie.getProperty("certificatePath","D:/shangpinpush_production.p12");
            password = propertie.getProperty("certificatePassword","1");
            host = propertie.getProperty("host","gateway.push.apple.com");
            production = Boolean.valueOf(propertie.getProperty("production", "true"));
            inputStream.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 推送一个简单消息
     * @param msg 消息
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushMsgNotification(String msg,Object devices) throws CommunicationException, KeystoreException{
        Push.alert(msg, keystore, password, production, devices);
    }
    /**
     * 推送一个标记
     * @param badge 标记
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushBadgeNotification(int badge,Object devices) throws CommunicationException, KeystoreException{
        Push.badge(badge, keystore, password, production, devices);
    }
    /**
     * 推送一个语音
     * @param sound 语音
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushSoundNotification(String sound,Object devices) throws CommunicationException, KeystoreException{
        Push.sound(sound, keystore, password, production, devices);
    }
    /**
     * 推送一个alert+badge+sound通知
     * @param message 消息
     * @param badge 标记
     * @param sound 声音
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushCombinedNotification(String message,int badge,String sound,Object devices) throws CommunicationException, KeystoreException{
        Push.combined(message, badge, sound, keystore, password, production, devices);
    }
    /**
     * 通知Apple的杂志内容
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void contentAvailable(Object devices) throws CommunicationException, KeystoreException{
        Push.contentAvailable(keystore, password, production, devices);
    }
    /**
     * 推送有用的调试信息
     * @param devices 设备
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void test(Object devices) throws CommunicationException, KeystoreException{
        Push.test(keystore, password, production, devices);
    }
    /**
     * 推送自定义负载
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws JSONException
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushPayload(List<Device> devices, String msg,Integer badge,String sound,Map<String,String> map) throws JSONException, CommunicationException, KeystoreException{
        PushNotificationPayload payload = customPayload(msg, badge, sound, map);
        Push.payload(payload, keystore, password, production, devices);
    }
    /**
     * 用内置线程推送负载信息
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws Exception
     */
    public static void pushPayLoadByThread(List<Device> devices, String msg,Integer badge,String sound,Map<String,String> map) throws Exception{
        PushNotificationPayload payload = customPayload(msg, badge, sound, map);
        Push.payload(payload, keystore, password, production, numberOfThreads, devices);
    }
    /**
     * 推送配对信息
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws JSONException
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushPayloadDevicePairs(List<Device> devices,String msg,Integer badge,String sound,Map<String,String> map) throws JSONException, CommunicationException, KeystoreException{
        List<PayloadPerDevice> payloadDevicePairs = new ArrayList<PayloadPerDevice>();
        PayloadPerDevice perDevice = null;
        for (int i = 0; i <devices.size(); i++) {
            perDevice = new PayloadPerDevice(customPayload(msg+"--->"+i, badge, sound, map), devices.get(i));
            payloadDevicePairs.add(perDevice);
        }
        Push.payloads(keystore, password, production, payloadDevicePairs);
    }
    /**
     * 用线程推配对信息
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws Exception
     */
    public static void pushPayloadDevicePairsByThread(List<Device> devices,String msg,Integer badge,String sound,Map<String,String> map) throws Exception{
        List<PayloadPerDevice> payloadDevicePairs = new ArrayList<PayloadPerDevice>();
        PayloadPerDevice perDevice = null;
        for (int i = 0; i <devices.size(); i++) {
            perDevice = new PayloadPerDevice(customPayload(msg+"--->"+i, badge, sound, map), devices.get(i));
            payloadDevicePairs.add(perDevice);
        }
        Push.payloads(keystore, password, production,numberOfThreads, payloadDevicePairs);
    }
    /**
     * 队列多线程推送
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws KeystoreException
     * @throws JSONException
     */
    public static void queue(List<Device> devices,String msg,Integer badge,String sound,Map<String,String> map) throws KeystoreException, JSONException{
        PushQueue queue = Push.queue(keystore, password, production, numberOfThreads);
        queue.start();
        PayloadPerDevice perDevice = null;
        for (int i = 0; i <devices.size(); i++) {
            perDevice = new PayloadPerDevice(customPayload(msg+"--->"+i, badge, sound, map), devices.get(i));
            queue.add(perDevice);
        }
    }
    /**
     * 自定义负载
     * @param msg
     * @param badge
     * @param sound
     * @param map 自定义字典
     * @return
     * @throws JSONException
     */
    private static PushNotificationPayload customPayload(String msg,Integer badge,String sound,Map<String,String> map) throws JSONException{
        PushNotificationPayload payload = PushNotificationPayload.complex();
        if(StringUtils.isNotEmpty(msg)){
            payload.addAlert(msg);         
        }
        if(badge != null){         
            payload.addBadge(badge);
        }
        payload.addSound(StringUtils.defaultIfEmpty(sound, "default"));
        if(map!=null && !map.isEmpty()){
            Object[] keys = map.keySet().toArray();    
            Object[] vals = map.values().toArray();
            if(keys!= null && vals != null && keys.length == vals.length){
                for (int i = 0; i < map.size(); i++) {                  
                    payload.addCustomDictionary(String.valueOf(keys[i]),String.valueOf(vals[i]));
                }
            }
        }
        return payload;
    }
}
