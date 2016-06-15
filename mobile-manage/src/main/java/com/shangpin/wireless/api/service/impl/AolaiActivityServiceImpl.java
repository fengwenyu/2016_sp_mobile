package com.shangpin.wireless.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.service.AolaiActivityService;
import com.shangpin.wireless.dao.AolaiActivityDao;
import com.shangpin.wireless.domain.AolaiActivity;

@Service(AolaiActivityService.SERVICE_NAME)
public class AolaiActivityServiceImpl implements AolaiActivityService{
	@Resource(name = AolaiActivityDao.DAO_NAME)
	private AolaiActivityDao aolaiActivityDao;

	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		// TODO Auto-generated method stub
		return aolaiActivityDao.executeSqlToMap(queryListSql);
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		// TODO Auto-generated method stub
		return aolaiActivityDao.executeSqlCount(queryListSql);
	}

	@Override
	public void edit(AolaiActivity model) throws Exception {
		if(model.getId()!=null){
			aolaiActivityDao.update(model);
		}else{
			aolaiActivityDao.save(model);
		}
		
		
	}

	@Override
	public void delete(Long id) throws Exception {
		aolaiActivityDao.delete(id);
		
	}

	@Override
	public AolaiActivity findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return aolaiActivityDao.getById(id);
	}

	@Override
	public Map<String, Object> isExist(Date startTime, Date endTime) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer res = new StringBuffer("SELECT * FROM aolai_activity t WHERE (('");
		res.append(sdf.format(startTime));
		res.append("' between t.startTime and t.endTime) OR  ('");
		res.append(sdf.format(endTime));
		res.append("' between t.startTime and endTime) OR ('");
		res.append(sdf.format(startTime));
		res.append("' <= t.startTime and '");
		res.append(sdf.format(endTime));
		res.append("'>=t.endTime)) ");
		res.append("and t.display='");
		res.append("1");
		res.append("'");
		List recorList = aolaiActivityDao.executeSqlToMap(res.toString());
		if (recorList.size() <= 0) {
			map.put("isExist", "false");
		}else{
			map.put("isExist", "true");
		}
		return map;
	}

	@Override
	public Map<String, Object> isExist(Date startTime, Date endTime, Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer res = new StringBuffer("SELECT * FROM aolai_activity t WHERE t.id!=");
		res.append(id);
		res.append(" and (('");
		res.append(sdf.format(startTime));
		res.append("' between t.startTime and t.endTime) OR  ('");
		res.append(sdf.format(endTime));
		res.append("' between t.startTime and endTime) OR ('");
		res.append(sdf.format(startTime));
		res.append("' <= t.startTime and '");
		res.append(sdf.format(endTime));
		res.append("'>=t.startTime))");
		res.append("and t.display='");
		res.append("1");
		res.append("'");
		List recorList = aolaiActivityDao.executeSqlToMap(res.toString());
		if (recorList.size() <= 0) {
			map.put("isExist", "false");
		}else{
			map.put("isExist", "true");
		}
		return map;
	}
}
