package com.shangpin.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.shangpin.core.entity.main.OnlineManage;


/** 
 *  
 * @author liujie
 * @version  2.1.0
 * @since   2014-8-12 下午14:19
 */
public interface OnlineManageDAO extends JpaRepository<OnlineManage, Long>, JpaSpecificationExecutor<OnlineManage> {
    
    @Query("select o from OnlineManage o where o.productNum=?1 and o.channelNum=?2 and o.inuse=?3")
    OnlineManage findOnline(int productNum,Long channelNum,int inuse);
}
