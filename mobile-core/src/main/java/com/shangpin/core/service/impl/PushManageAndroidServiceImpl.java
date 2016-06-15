package com.shangpin.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IPushManageAndroidDAO;
import com.shangpin.core.entity.PushManageAndroid;
import com.shangpin.core.service.IPushManageAndroidService;

@Service
@Transactional
public class PushManageAndroidServiceImpl implements IPushManageAndroidService {

    @Autowired
    private IPushManageAndroidDAO dao;

    @Override
    public PushManageAndroid add(PushManageAndroid pushManageAndroid) {
        return dao.save(pushManageAndroid);
    }

    @Override
    public PushManageAndroid modify(PushManageAndroid pushManageAndroid) {
        return dao.save(pushManageAndroid);
    }

    @Override
    public void deleteById(Long id) {
        dao.delete(id);
    }

    @Override
    public PushManageAndroid findById(Long id) {
        return dao.findOne(id);
    }

    @Override
    public List<PushManageAndroid> findByUserId(String userId, Long productNum) {
        return dao.findByUserIdAndProductNum(userId, productNum);
    }

    @Override
    public PushManageAndroid modifyTypeById(Long id) {
        PushManageAndroid bean = findById(id);
        bean.setPushType(1);
        return modify(bean);
    }

    @Override
    public List<PushManageAndroid> findByGender(int gender, Long productNum) {
        final int finalGender = gender;
        final Long finalProductNum = productNum;
        return dao.findAll(new Specification<PushManageAndroid>() {
            @Override
            public Predicate toPredicate(Root<PushManageAndroid> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> plist = new ArrayList<Predicate>();
                Path<String> usernamePath = root.get("username");
                Path<Long> productNumPath = root.get("productNum");
                Path<Date> showtimePath = root.get("showTime");
                Path<Date> endTimePath = root.get("endTime");
                Path<Integer> msgTypePath = root.get("msgType");
                Path<Date> createtimePath = root.get("createTime");
                // username为空
                plist.add(cb.equal(usernamePath, ""));
                plist.add(cb.equal(productNumPath, finalProductNum));
                // 显示时间小于当前时间
                plist.add(cb.lessThan(showtimePath, cb.currentDate()));
                // 结束时间大于当前时间
                plist.add(cb.greaterThan(endTimePath, cb.currentDate()));
                if (finalGender == 0) {
                    plist.add(cb.or(cb.equal(msgTypePath, 0), cb.equal(msgTypePath, 2)));
                } else if (finalGender == 1) {
                    plist.add(cb.or(cb.equal(msgTypePath, 1), cb.equal(msgTypePath, 2)));
                } else {
                    plist.add(cb.equal(msgTypePath, 2));
                }
                // 排序
                query.orderBy(cb.asc(createtimePath));
                if (plist.size() > 0) {
                    return cb.and(plist.toArray(new Predicate[plist.size()]));
                }
                return cb.conjunction();
            }
        });
    }

	@Override
	public List<PushManageAndroid> findByPage(int pageIndex, int pageSize) {
		return dao.findByPushType(0, new PageRequest(pageIndex - 1, pageSize, new Sort(Direction.DESC, "showTime")));
	}

}
