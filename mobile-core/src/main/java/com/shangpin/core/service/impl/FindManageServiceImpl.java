package com.shangpin.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangpin.core.dao.IFindManageDAO;
import com.shangpin.core.entity.FindManage;
import com.shangpin.core.service.IFindManageService;

@Service
@Transactional
public class FindManageServiceImpl implements IFindManageService {

    @Autowired
    private IFindManageDAO findManageDAO;

    @Override
    public List<FindManage> findManageData(Date date) {
        List<FindManage> list = this.findManageDAO.findManageData(date);
        return list;
    }

    @Override
    public FindManage addFindManage(FindManage findMnage) {
        return this.findManageDAO.save(findMnage);
    }

    @Override
    public FindManage findManageById(Long id) {
        return this.findManageDAO.findOne(id);
    }

    @Override
    public void deleteFindManage(Long id) {
        this.findManageDAO.delete(id);
    }

    @Override
    public List<FindManage> findByActivityManage(Date date, int isSlider, String type) {
        List<FindManage> list = this.findManageDAO.findByActivityManage(date, isSlider, type);
        return list;
    }

    @Override
    public List<FindManage> findStaticActivity(Date date, int display, String type) {
        List<FindManage> list = this.findManageDAO.findStaticActivity(date, display, type);
        return list;
    }

    @Override
    public List<FindManage> findByConditions(Date date, String type) {
        Specification<FindManage> specification = this.buildManageSpecification(date, type);
        return this.findManageDAO.findAll(specification);
    }

    /**
     * 
     * @param date
     * @param type
     * @param sortMap
     * @return
     */
    private Specification<FindManage> buildManageSpecification(final Date date, final String type) {
        return new Specification<FindManage>() {
            public Predicate toPredicate(Root<FindManage> root, CriteriaQuery<?> q, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (date != null) {
                    Path<Date> np = root.get("showEndDate");
                    predicates.add(cb.greaterThan(np, date));
                }
                q.where(predicates.get(0));
                if (type.equals("0")) {
                    q.orderBy(cb.asc(root.get("sort").as(Integer.class)), cb.desc(root.get("isSlider").as(Integer.class)), cb.desc(root.get("showStartDate").as(Date.class)));
                } else {
                    q.orderBy(cb.asc(root.get("sort").as(Integer.class)), cb.desc(root.get("showStartDate").as(Date.class)));
                }
                return q.getRestriction();
            }

        };
    }

    @Override
    public List<FindManage> findFocus(Date date, int slider) {
        final Date dateFinal = date;
        final int sliderFinal = slider;
        List<FindManage> list = findManageDAO.findAll(new Specification<FindManage>() {
            @Override
            public Predicate toPredicate(Root<FindManage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Date> showStartDatePath = root.get("showStartDate");
                Path<Date> showEndDatePath = root.get("showEndDate");
                Path<Date> mobilePreTimePath = root.get("mobilePreTime");
                Path<String> isSliderPath = root.get("isSlider");
                Path<String> typePath = root.get("type");
                Path<Integer> sortPath = root.get("sort");
                List<Predicate> plist = new ArrayList<Predicate>();
                // 开始时间到结束时间
                plist.add(cb.or(cb.and(cb.greaterThan(showEndDatePath, dateFinal), cb.lessThan(showStartDatePath, dateFinal)),
                        cb.and(cb.greaterThan(mobilePreTimePath, dateFinal), cb.lessThan(showStartDatePath, dateFinal))));
                plist.add(cb.notEqual(typePath, "STATICATC"));
                if (sliderFinal == 1) {
                    plist.add(cb.equal(isSliderPath, sliderFinal));
                }

                query.orderBy(cb.asc(cb.abs(sortPath)), cb.desc(showStartDatePath));
                if (plist.size() > 0) {
                    return cb.and(plist.toArray(new Predicate[plist.size()]));
                }
                return cb.conjunction();
            }

        });

        return list;
    }

    @Override
    public List<FindManage> findDefaultSlider(Date date) {
        Pageable pageable = new PageRequest(0, 5);
        Page<FindManage> page = findManageDAO.findDefaultSlider(date, pageable);
        List<FindManage> beanList = new ArrayList<FindManage>();
        Iterator<FindManage> itertor = page.iterator();
        while (itertor.hasNext()) {
            FindManage bean = itertor.next();
            beanList.add(bean);
        }
        return beanList;
    }

}
