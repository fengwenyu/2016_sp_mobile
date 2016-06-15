package com.shangpin.wireless.api.service.impl;



import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shangpin.wireless.api.service.AppPicturesService;
import com.shangpin.wireless.dao.AppPicturesDao;
import com.shangpin.wireless.domain.AppPictures;



@Service(AppPicturesService.SERVICE_NAME)
public class AppPicturesServiceImpl implements AppPicturesService{
	@Resource(name = AppPicturesDao.DAO_NAME)
	private AppPicturesDao appPicturesDao;


	@Override
	public void update(AppPictures appPictures) throws Exception {
		appPicturesDao.update(appPictures);
		
	}

	@Override
	public void save(AppPictures appPictures) throws Exception {
		appPicturesDao.save(appPictures);
		
	}

	@Override
	public List executeSqlToMap(String queryListSql, int page, int rows) throws Exception {
		return appPicturesDao.executeSqlToMap(queryListSql, page, rows);
	}

	@Override
	public Integer executeSqlCount(String queryListSql) throws Exception {
		return appPicturesDao.executeSqlCount(queryListSql);
	}

	@Override
	public void upadte(AppPictures model) throws Exception {
		appPicturesDao.update(model);
		
	}

	@Override
	public void delete(Long id) throws Exception {
		appPicturesDao.delete(id);
		
	}

	@Override
	public AppPictures findById(Long id) {
		// TODO Auto-generated method stub
		return appPicturesDao.findById(id);
	}

	@Override
	public void add(AppPictures model) throws Exception {
		appPicturesDao.save(model);
		
	}

}
