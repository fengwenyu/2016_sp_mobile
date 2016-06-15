package com.shangpin.biz.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommentService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.CommentDetail;
import com.shangpin.biz.bo.CommentInit;
import com.shangpin.biz.bo.CommentItem;
import com.shangpin.biz.bo.CommentItemAttr;
import com.shangpin.biz.bo.Picture;
import com.shangpin.biz.bo.base.ResultObjOne;
import com.shangpin.biz.service.ASPBizCommentService;
import com.shangpin.biz.utils.Constants;
import com.shangpin.utils.JsonUtil;
@Service
public class ASPBizCommentServiceImpl implements ASPBizCommentService{
	@Autowired
	private CommentService commentService;
	@Autowired
	private ShangPinService shangPinService;
	@Override
	public String findCommentInitInfo(String subOrderNo, String productNo,
			String userId,String orderDetailNo,String skuNo,String categoryNo) {
		String data = commentService.findCommentInitInfo(subOrderNo, productNo, userId,orderDetailNo,skuNo,categoryNo);
		if(StringUtils.isEmpty(data)){
			return null;
		}
		ResultObjOne<CommentInit> commentInitResultObjOne = JsonUtil.fromJson(data, new TypeToken<ResultObjOne<CommentInit>>() {});
		if(!Constants.SUCCESS.equals(commentInitResultObjOne.getCode())){
			return null;
		}
		CommentInit commentInit = commentInitResultObjOne.getContent();
		CommentDetail commentDetail = commentInit.getOrderdetails();
		String prictTitle = commentDetail.getPriceTitle();
		if("商品金额".equals(prictTitle)){
			commentDetail.setPriceTitle("价格");
		}
		
		String price = "";
		if(commentDetail!=null){
			price = commentDetail.getPrice()==null?"0":commentDetail.getPrice();
			if(price.contains(".")){
				commentDetail.setPrice(price.substring(0, price.indexOf(".")));
			}
			
		}
		List<CommentItem> commentItemList = commentInit.getCommentItemList();
		Iterator<CommentItem> it = commentItemList.iterator();
		boolean userInfo = false;//身高体重标示，默认没有
		CommentItem commentItemSingle = new CommentItem();
		 
		List<CommentItemAttr> itemList = new ArrayList<CommentItemAttr>(); 
		List<CommentItem> deleteItemList = new ArrayList<CommentItem>();//需要删除分散开的身高和体重，整合到一起
		List<CommentItem> commentItemListNew = new ArrayList<CommentItem>();
		List<CommentItem> commentItemListStar = new ArrayList<CommentItem>();
		List<CommentItem> commentItemListInfo = new ArrayList<CommentItem>();
		while(it.hasNext()){
			CommentItem commentItem = it.next();
			String type = commentItem.getType();
			if("1".equals(type)){//类型是1，代表的是身高和体重
				userInfo = true;
				deleteItemList.add(commentItem);
				
				commentItemSingle.setName("身高/体重");
				commentItemSingle.setType(type);
				commentItemSingle.setUnit("cm/kg");
				CommentItemAttr commentItemAttr = new CommentItemAttr();
				commentItemAttr.setId(commentItem.getId());
				if("6".equals(commentItem.getId())){
					commentItemAttr.setDefaultValue("145");
					commentItemAttr.setName(commentItem.getName()+"(cm)");
					List<String> strList = new ArrayList<String>();
					
					strList.add("145");strList.add("146");strList.add("147");strList.add("148");strList.add("149");
					
					strList.add("150");strList.add("151");strList.add("152");strList.add("153");strList.add("154");
					strList.add("155");strList.add("156");strList.add("157");strList.add("158");strList.add("159");
					strList.add("160");strList.add("161");strList.add("162");strList.add("163");strList.add("164");
					strList.add("165");strList.add("166");strList.add("167");strList.add("168");strList.add("169");
					strList.add("170");strList.add("171");strList.add("172");strList.add("173");strList.add("174");
					strList.add("175");strList.add("176");strList.add("177");strList.add("178");strList.add("179");
					strList.add("180");strList.add("181");strList.add("182");strList.add("183");strList.add("184");
					strList.add("185");strList.add("186");strList.add("187");strList.add("188");strList.add("189");
					strList.add("190");strList.add("191");strList.add("192");strList.add("193");strList.add("194");
					strList.add("195");strList.add("196");strList.add("197");strList.add("198");strList.add("199");
					strList.add("200");
					commentItemAttr.setValue(strList);
				}else if("7".equals(commentItem.getId())){
					commentItemAttr.setDefaultValue("30");
					commentItemAttr.setName(commentItem.getName()+"(kg)");
					List<String> strList = Arrays.asList("30", "31", "32","33","34","35","36","37","38","39",
							"40","41","42","43","44","45","46","47","48","49",
							"50","51","52","53","54","55","56","57","58","59",
							"60","61","62","63","64","65","66","67","68","69",
							"70","71","72","73","74","75","76","77","78","79",
							"80","81","82","83","84","85","86","87","88","89",
							"90","91","92","93","94","95","96","97","98","99",
							"100","101","102","103","104","105","106","107","108","109",
							"110","111","112","113","114","115","116","117","118","119",
							"120","121","122","123","124","125","126","127","128","129",
							"130","131","132","133","134","135","136","137","138","139",
							"140","141","142","143","144","145","146","147","148","149",
							"150");
//					strList.add("50");
//					strList.add("51");
//					strList.add("52");
//					strList.add("53");
//					strList.add("54");
//					strList.add("55");
//					strList.add("56");
//					strList.add("57");
//					strList.add("58");
//					strList.add("59");
//					strList.add("60");
//					strList.add("61");
//					strList.add("62");
//					strList.add("63");
//					strList.add("64");
//					strList.add("65");
//					strList.add("66");
					commentItemAttr.setValue(strList);
				}
				itemList.add(commentItemAttr);
				
			}else if("5".equals(type)){
				commentItemListStar.add(commentItem);
			}else if("3".equals(type)){
				commentItem.setType("2");
				commentItemListInfo.add(commentItem);
			}else{
				commentItemListInfo.add(commentItem);
			}
		}
		//如果有身高体重标示，加入新的节点
		if(userInfo){
			commentItemList.removeAll(deleteItemList);
			//List<CommentItemAttr> commentItemAttrList = new ArrayList<CommentItemAttr>();
			commentItemSingle.setCommentItemAttr(itemList);
			commentItemListInfo.add(commentItemSingle);
		}
		CommentItem commentItem = new CommentItem();
		commentItem.setType("0");

		commentItemListStar.add(commentItem);//加入横线标示，type为0
		if(commentItemListInfo.size()>0){
			
			commentItemListInfo.add(commentItem);//加入横线标示，type为0
		}
		commentItemListNew.addAll(commentItemListStar);//加入打分项，星标打分
		commentItemListNew.addAll(commentItemListInfo);//加入个人信息项，尺码，复制或身高体重
		
		CommentItem commentItemContent = new CommentItem();
		commentItemContent.setType("9");//代表评论描述
		commentItemListNew.add(commentItemContent);
		
		commentInit.setCommentItemList(commentItemListNew);
		commentInitResultObjOne.setContent(commentInit);
		return commentInitResultObjOne.toJsonNullable();
	}

	@Override
	public String CommentListByOrderNo(String subOrderNo, String userId) {
		String data = commentService.CommentListByOrderNo(subOrderNo, userId);
		return data;
	}

	@Override
	public String submitComment(String commentItemList, String commentContent, String imageList, String orderNo,String orderDetailNo,String skuNo,String productNo,String userId,String categoryNo) {
		String data = commentService.submitComment(commentItemList, commentContent, imageList, orderNo, orderDetailNo, skuNo,productNo,userId,categoryNo);
		return data;
	}

	@Override
	public Picture uploadPic(String extension, String picturetype,
			String picFileContent) {
		String json = shangPinService.uploadPic(extension, picturetype, picFileContent);
		if(StringUtils.isEmpty(json)){
			return null; 
		}
		ResultObjOne<Picture> picResultObjOne = JsonUtil.fromJson(json, new TypeToken<ResultObjOne<Picture>>() {});
		if(!Constants.SUCCESS.equals(picResultObjOne.getCode())){
			return null;
		}
		Picture picture = picResultObjOne.getContent();
		return picture;
	}

    @Override
    public String commentProductList(String userId, String pageIndex, String pageSize) {
        String data = commentService.commentProductList(userId,pageIndex, pageSize);
        return data;
    }
	
}
