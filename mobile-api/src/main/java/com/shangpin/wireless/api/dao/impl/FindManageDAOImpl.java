package com.shangpin.wireless.api.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.FindManageDAO;
import com.shangpin.wireless.api.domain.DisplayEnum;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.IsSliderEnum;
import com.shangpin.wireless.api.domain.TypeEnum;
import com.shangpin.wireless.api.util.DateUtil;

@Repository(FindManageDAO.DAO_NAME)
public class FindManageDAOImpl extends BaseDaoImpl<FindManage> implements FindManageDAO {
	public List<FindManage> findAll(){
		String sql = "from FindManage where ?<showEndDate and type!=? order by ABS(sort) asc,showStartDate desc";
		return  getSession("").createQuery(sql).setString(0, DateUtil.getDateTime()).setParameter(1, TypeEnum.STATICATC.name()).list();
	}

	@Override
	public List<FindManage> findByActivityManage(IsSliderEnum isSlider) {
		String sql = "from FindManage where ?<showEndDate and isSlider=? and type!=? order by ABS(sort) asc,showStartDate desc";
		return  getSession("").createQuery(sql).setString(0, DateUtil.getDateTime()).setParameter(1, isSlider).setParameter(2, TypeEnum.STATICATC.name()).list();
	}

	@Override
	public  List<FindManage> findStaticActivity() {
		String sql = "from FindManage f where :date between f.showStartDate and f.showEndDate and f.display=:display and f.type=:ftype order by ABS(f.sort) asc,f.showStartDate desc";
		String date = DateUtil.getDateTime();
		return  getSession("").createQuery(sql).setString("date", date).setParameter("display", DisplayEnum.YES).setParameter("ftype", TypeEnum.STATICATC.name()).list();
	}

	@Override
	public List<FindManage> findActManageByVer(String verType) {
		String sql;
		if(verType.equals("0")){
			 sql = "from FindManage where ?<showEndDate  order by ABS(sort) asc, isSlider desc,showStartDate desc";
		}else{
			sql = "from FindManage where ?<showEndDate  order by ABS(sort) asc, showStartDate desc";
		}
		
		return  getSession("").createQuery(sql).setString(0, DateUtil.getDateTime()).list();
		
	}
}
