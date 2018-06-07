package com.otod.server.otod.services;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.respository.CommenOrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return commenOrderRespository.findAll();
    }

    public Optional<CommenOrder> getCommenOrderById(Long id){
        return commenOrderRespository.findById(id);
    }


}
