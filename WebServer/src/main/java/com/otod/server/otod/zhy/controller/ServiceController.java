package com.otod.server.otod.zhy.controller;

import com.otod.server.otod.zhy.model.CommenOrder;
import com.otod.server.otod.model.User;
import com.otod.server.otod.zhy.pojos.CommenOrdersPOJO;
import com.otod.server.otod.zhy.pojos.PublishOrder;
import com.otod.server.otod.zhy.services.ServiceService;
import com.otod.server.otod.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    private UserService userService;

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

//    String title, String content, String deadline, String urgency, int contributers
    @PostMapping("/saveOrder")
    private String saveOrder(@RequestBody PublishOrder publishOrder){
        CommenOrder commenOrder = new CommenOrder();
        commenOrder.setTitle(publishOrder.getTitle());
        commenOrder.setContent(publishOrder.getContent());
        System.out.println(publishOrder.getDeadline());
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String edateStr = publishOrder.getDeadline().substring(0,10);
        Date sdate = new Date();
        commenOrder.setsDate(sdate);

        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        commenOrder.setUserinfo_s(userService.getUserInfo(user));
        try{
            Date edate = dateFormat1.parse(edateStr);
            commenOrder.setsDate(edate);
//            System.out.println(sdate + " -- "+ edateStr);
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
