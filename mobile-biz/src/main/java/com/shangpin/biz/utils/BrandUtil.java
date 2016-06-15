package com.shangpin.biz.utils;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.shangpin.biz.bo.Flagship;
import com.shangpin.biz.bo.MallBrand;
import com.shangpin.biz.bo.RulesEnum;


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
    
	/**
	 * 此方法只针对com.shangpin.base.vo.MallBrand类，为其添加通用规则属性
	 * 
	 * @param id
	 * 		品牌id
	 * @param nameEN
	 * 		品牌名称
	 * @return
	 */
	public static MallBrand setRulesByConfig(MallBrand mb) {
		String id = mb.getId();
		String nameEN = mb.getNameEN();
		boolean flag = false;
        for (int i = 0; i < flagshipList.size(); i++) {
            flagship = flagshipList.get(i);
            if (nameEN.equals(flagship.getBrandEN())) {
                flag = true;
                mb.setType(RulesEnum.CLIENT_TYPE_FIVE.getInfo());
                mb.setName(flagship.getTitle());
                mb.setRefContent(flagship.getUrl());
                break;
            }
        }
        if (!flag) {
        	mb.setType(RulesEnum.CLIENT_TYPE_THREE.getInfo());
        	mb.setName(nameEN);
        	mb.setRefContent(id);
        }
        
		return mb;
	}

}
