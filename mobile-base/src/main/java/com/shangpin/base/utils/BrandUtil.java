package com.shangpin.base.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.shangpin.base.vo.Flagship;

public class BrandUtil {

    private static List<Flagship> flagshipList;
    private static Flagship flagship;

    static {
        try {
            flagshipList = FlagshopEntrance.getFlagshopContent();
        } catch (ParseException e) {
            //  Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    public static String getFlagShipUrl(String brandEN) {
        for (int i = 0; i < flagshipList.size(); i++) {
            flagship = flagshipList.get(i);
            if (brandEN.equals(flagship.getBrandEN())) {
                return flagship.getUrl();
            }
        }
        return "";
    }

    public static String getFlagShipName(String brandEN) {
        for (int i = 0; i < flagshipList.size(); i++) {
            flagship = flagshipList.get(i);
            if (brandEN.equals(flagship.getBrandEN())) {
                return flagship.getTitle();
            }
        }
        return "";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String getBrandJson(String brandJson) {
        Gson gson = new Gson();
        Map brandMap = (Map) gson.fromJson(brandJson, Object.class);
        String brandEN = brandMap.get("nameEN").toString();
        boolean flag = false;
        for (int i = 0; i < flagshipList.size(); i++) {
            flagship = flagshipList.get(i);
            if (brandEN.equals(flagship.getBrandEN())) {
                flag = true;
                brandMap.put("type", "5");
                brandMap.put("name", flagship.getTitle());
                brandMap.put("refContent", flagship.getUrl());
                break;
            }
        }
        if (!flag) {
            brandMap.put("type", "3");
            brandMap.put("name", brandMap.get("nameEN"));
            brandMap.put("refContent", brandMap.get("id"));
        }
        brandMap.remove("id");
        return gson.toJson(brandMap);
    }

    public static boolean isFlagship(String brandEN) {
        for (int i = 0; i < flagshipList.size(); i++) {
            flagship = flagshipList.get(i);
            if (brandEN.equals(flagship.getBrandEN())) {
                return true;
            }
        }
        return false;
    }

}
