package com.otod.server.otod.controller;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by zhang on 2018/6/6.
 */

@RestController
@CrossOrigin
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/allServices")
    private List<CommenOrder> getAllCommenOrders(){
        return serviceService.getAllCommenOrders();
    }

    @GetMapping("/serviceById")
    private Optional<CommenOrder> getCommenOrderById(Long id){
        return serviceService.getCommenOrderById(id);
    }


}
