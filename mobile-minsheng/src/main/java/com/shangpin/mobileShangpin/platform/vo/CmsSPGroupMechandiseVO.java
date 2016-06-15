package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;


/**
 * CMS分组商品传输数据对象，用于前台展示
 * 
 */
public class CmsSPGroupMechandiseVO {
	private CmsTopGroupVO gmsTopGroupVO;	//商品分组
	private List<SPMerchandiseVO>	sPMerchandiseVO;//商品
	public CmsTopGroupVO getGmsTopGroupVO() {
		return gmsTopGroupVO;
	}
	public void setGmsTopGroupVO(CmsTopGroupVO gmsTopGroupVO) {
		this.gmsTopGroupVO = gmsTopGroupVO;
	}
	public List<SPMerchandiseVO> getsPMerchandiseVO() {
		return sPMerchandiseVO;
	}
	public void setsPMerchandiseVO(List<SPMerchandiseVO> sPMerchandiseVO) {
		this.sPMerchandiseVO = sPMerchandiseVO;
	}
	
	
}
