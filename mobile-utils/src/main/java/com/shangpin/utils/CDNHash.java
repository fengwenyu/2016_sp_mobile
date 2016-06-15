package com.shangpin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.zip.CRC32;

/**
 * 一致性hash
 * 
 * @author sundful
 * 
 */
public class CDNHash {
    private static TreeMap<Long, Object> nodes = null;
    // 真实服务器节点信息
    private static List<Object> shards = new ArrayList<Object>();
    // 设置虚拟节点数目
    private static int VIRTUAL_NUM = 4;
    private static CRC32 crc32 = new CRC32();

    public static final String picUrl = PropertyUtil.get("search_pic_cdn").toString();

    /**
     * 初始化一致环，以后表不够的话，在此基础上再添加
     */
    static {
        shards.add("1");
        shards.add("2");
        shards.add("3");
        shards.add("4");
        shards.add("5");
        shards.add("6");

        nodes = new TreeMap<Long, Object>();
        for (int i = 0; i < shards.size(); i++) {
            Object shardInfo = shards.get(i);
            for (int j = 0; j < VIRTUAL_NUM; j++) {
                nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j), j),
                        shardInfo);
            }
        }
    }

    /**
     * 根据key的hash值取得服务器节点信息
     * 
     * @param hash
     * @return
     */
    public static Object getShardInfo(long hash) {
        Long key = hash;
        SortedMap<Long, Object> tailMap = nodes.tailMap(key);
        if (tailMap.isEmpty()) {
            key = nodes.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        return nodes.get(key);
    }

    /**
     * 打印圆环节点数据
     */
    public void printMap() {
        System.out.println(nodes);
    }

    /**
     * 根据2^32把节点分布到圆环上面。
     * 
     * @param digest
     * @param nTime
     * @return
     */
    public static long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
                | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
                | (digest[0 + nTime * 4] & 0xFF);

        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }

    /**
     * Get the md5 of the given key. 计算MD5值
     */
    public static byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return md5.digest();
    }

    public static long getKey(byte[] userId) {
        crc32.reset();
        crc32.update(userId);
        long tempUserId = crc32.getValue();
        return tempUserId;
    }

    /**
     * 返回分表名称
     * 
     * @param pri
     *            分表名称的前面部分
     * @param userId
     *            用户Id
     * @return
     */
    public static String getTableName(String pri, String userId) {
        Long key;
        try {
            key = getKey(userId.getBytes("UTF-8"));
            return pri + getShardInfo(key).toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       return null;
    }
    /**
     * 返回hash图片域名
     * 
     * @param picNo
     *            用户picNo
     * @return
     */
    public static String getUrlHash(String picNo) {
        Long key;
        try {
            key = getKey(picNo.getBytes("UTF-8"));
            if(picUrl.indexOf("11")>-1){
                return picUrl.replace("11", getShardInfo(key).toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       return picUrl;
    }
    public static void main(String[] args) {
        /*String a = "abcde";
        System.out.println(getTableName("pre", a));*/
        System.out.println(getUrlHash("201412303333333333333323"));
    }
}