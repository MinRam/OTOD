package com.otod.server.otod.services;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.respository.CommenOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by zhang on 2018/6/6.
 */

@Service
public class ServiceService {
    @Autowired
    private CommenOrderRespository commenOrderRespository;


    public List<CommenOrder> getAllCommenOrders(){
//        String sql = "select * from commenorder where order_state=1";
//        return getAllCommenOrders().query(sql,new ItemRowMapper());
        return commenOrderRespository.findAll();
    }

    public Optional<CommenOrder> getCommenOrderById(Long id){
        return commenOrderRespository.findById(id);
    }

    //currentPage 当前页
    //size 每页的数量
    public Page<CommenOrder> getListPage(int currentPage, int size){
        Pageable pageable = PageRequest.of(currentPage, size, Sort.DEFAULT_DIRECTION, "sDate");
        return commenOrderRespository.findAll(pageable);
    }

    public void saveOrder(CommenOrder commenOrder){
        commenOrderRespository.save(commenOrder);
    }


    public boolean reciveOrder(CommenOrder commenOrder, UserInfo userInfo){
        commenOrderRespository.save(commenOrder);
        return true;
    }

    public List<CommenOrder> getAllOrder(UserInfo userInfo){
        return commenOrderRespository.findByUserinfoS(userInfo);
    }

    public Page<CommenOrder> getAllOrderPage(UserInfo userInfo, int currentPage, int size){
        Pageable pageable = new PageRequest(currentPage, size, Sort.DEFAULT_DIRECTION, "sDate");
        commenOrderRespository.findAll(new Specification<CommenOrder>() {
            @Override
            public Predicate toPredicate(Root<CommenOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("userinfoS").as(UserInfo.class),userInfo));
                return null;
            }
        },pageable);
        return commenOrderRespository.findAll(pageable);
    }
}
