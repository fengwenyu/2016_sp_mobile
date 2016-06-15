package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shangpin.core.entity.WeiXinPacketCash;

/**
 * @author tongwenli
 *微信红包现金券数据处理接口，访问数据库数据
 */
public interface IWeiXinPacketCashDao  extends JpaRepository<WeiXinPacketCash, Long>, JpaSpecificationExecutor<WeiXinPacketCash> {


	
	
	
}
