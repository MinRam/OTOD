package com.otod.server.otod.services;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.respository.CommenOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        Pageable pageable = new PageRequest(currentPage, size, Sort.DEFAULT_DIRECTION, "sDate");
        return commenOrderRespository.findAll(pageable);
    }

    public void saveOrder(CommenOrder commenOrder){
        commenOrderRespository.save(commenOrder);
    }

}
