package com.otod.server.otod.controller;

import com.otod.server.otod.model.UserInfo;
import com.otod.server.otod.pojos.CommenOrdersPOJO;
import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.model.User;
import com.otod.server.otod.pojos.PublishOrder;
import com.otod.server.otod.services.ServiceService;
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

        commenOrder.setUserinfoS(userService.getUserInfo(user));
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

    //
    @GetMapping("/reciveOrder")
    private String reciveOrder(@RequestParam (value = "OrderId") Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(userInfo.getId() == commenOrder.getUserinfoS().getId()){
            return "the publisher..";
        }
        return serviceService.reciveOrder(commenOrder, userInfo)? "yeah":"false";
    }

    @GetMapping("/allOrders")
    private List<CommenOrder> getAllOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return serviceService.getAllOrder(userInfo);
    }

    @GetMapping("/allOrderPage")
    private Page<CommenOrder> getAllOrderPage(int currentPage, int size){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return serviceService.getAllOrderPage(userInfo,currentPage,size);
    }
}
