package com.shangpin.biz.service.abstraction;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.BrandActivityContent;
import com.shangpin.biz.bo.BrandActivityHead;
import com.shangpin.utils.JSONUtils;

public abstract class AbstractBizBrandActivityService {
	
	@Autowired
	private ShangPinService shangPinService;
	
	public BrandActivityHead headInfo(String userid, String id, String type) {
		String json = shangPinService.findHeadInfo(userid, id, type);
//		String json = "{\"code\":\"0\",\"msg\":\"成功\",\"content\":{\"activity\":{},\"head\":{\"id\":\"B0508\",\"name\":\"NICOLA DOLANI\",\"logo\":\"\",\"isCollected\":\"0\",\"about\":\"\",\"share\":{\"title\":\"\",\"desc\":\"\",\"url\":\"http://www.shangpin.com/women/brand/B0508\",\"pic\":\"20111222125713593791\"}}}}";
//		String json = "{\"code\":\"0\",\"msg\":\"成功\",\"content\":{}}";
//		String json = "{\"code\":\"0\",\"msg\":\"成功\",\"content\":{\"head\":{}}}";
		if(StringUtils.isEmpty(json)){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode;
		try {
			jsonNode = mapper.readTree(json);
			String code = jsonNode.path("code").asText();
			if(!"0".equals(code)){
				return null;
			}
			BrandActivityContent content = JSONUtils.json2pojo(json, BrandActivityContent.class);
			BrandActivityHead head = content.getContent();
			return head;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
