package com.otod.server.otod.controller;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.pojos.CommenOrdersPOJO;
import com.otod.server.otod.pojos.PublishOrder;
import com.otod.server.otod.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private CommenOrdersPOJO getAllCommenOrders(){
        List<CommenOrder> commenOrders =  serviceService.getAllCommenOrders();
        CommenOrdersPOJO commenOrdersPOJO = new CommenOrdersPOJO(commenOrders);
        return commenOrdersPOJO;
    }

    @GetMapping("/serviceById")
    private Optional<CommenOrder> getCommenOrderById(Long id){
        return serviceService.getCommenOrderById(id);
    }

    @GetMapping("/listServices")
    private Page<CommenOrder> getListServices(int currentPage, int size){
        return serviceService.getListPage(currentPage,size);
    }

//    String title, String content, String deadline, String urgency, String contributers
    @PostMapping("/saveOrder")
    private String saveOrder(@RequestBody PublishOrder publishOrder){
        CommenOrder commenOrder = new CommenOrder();
        commenOrder.setTitle(publishOrder.getTitle());
        commenOrder.setContent(publishOrder.getContent());
        System.out.println(publishOrder.getDeadline());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String sdateStr = publishOrder.getDeadline().substring(0,10);
        try{
            Date sdate = dateFormat1.parse(sdateStr);
            commenOrder.setsDate(sdate);
            System.out.println(sdate + " -- "+ sdateStr);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        commenOrder.setUrgency(publishOrder.getUrgency());
        commenOrder.setContributers(publishOrder.getContributers());
        serviceService.saveOrder(commenOrder);
        return "fun";
    }
}
