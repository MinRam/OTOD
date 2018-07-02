package com.otod.server.otod.controller;

import com.otod.server.otod.model.user.UserInfo;
import com.otod.server.otod.model.user.User;
import com.otod.server.otod.model.OrderEval;
import com.otod.server.otod.pojos.CommenOrdersPOJO;
import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.pojos.OrderEvalPOJO;

import com.otod.server.otod.pojos.PublishOrder;
import com.otod.server.otod.services.ServiceService;
import com.otod.server.otod.services.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by zhang on 2018/6/6.
 */

/*
0 待支付
1 待接单
2 进行中/已接单
3 待评价
4 已完成
5 失败订单
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
        CommenOrder commenOrder;
        if(publishOrder.getId() != null){
            Optional<CommenOrder> c = serviceService.getCommenOrderById(publishOrder.getId());
            commenOrder = c.get();
            if(commenOrder == null){
                return "null order!";
            }
            if(!commenOrder.getOrderState().equals("1")){
                return "wrong state!";
            }
//            System.out.println("editOrder------------------->" + commenOrder.getTitle() + "---------id------------->" + commenOrder.getId());
        } else {
            commenOrder = new CommenOrder();
        }
        commenOrder.setTitle(publishOrder.getTitle());
        commenOrder.setContent(publishOrder.getContent());
        commenOrder.setOrderState("1");
        commenOrder.setProfits(publishOrder.getProfit());
        System.out.println(publishOrder.getDeadline());
//        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        String edateStr = publishOrder.getDeadline().substring(0,10);
        Date sdate = new Date();
        commenOrder.setsDate(sdate);

        User user = userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        commenOrder.setUserinfoS(userService.getUserInfo(user));
        try{
//            Date edate = dateFormat1.parse(edateStr);
//            Date edate = new Date(edateStr);
            commenOrder.setDeadline(publishOrder.getDeadline());
            System.out.println(sdate + " -- "+ publishOrder.getDeadline());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        commenOrder.setUrgency(publishOrder.getUrgency());
        commenOrder.setContributers(publishOrder.getContributers());
        serviceService.saveOrder(commenOrder);
        return "fun";
    }

    //订单列表 -》 接单！
    @GetMapping("/reciveOrder")
    private String reciveOrder(@RequestParam (value = "OrderId") Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(userInfo.getId() == commenOrder.getUserinfoS().getId()){
            return "the publisher..";
        }
        List<UserInfo> list = commenOrder.getUserinfoR();
        for(UserInfo temp: list){
            if(temp.getId() == userInfo.getId()){
                return "already recived..";
            }
        }
        if(!commenOrder.getOrderState().equals("1")){
            return "state wrong";
        }
        list.add(userInfo);
        commenOrder.setUserinfoR(list);
        if(commenOrder.getContributers() - commenOrder.getContributersRecive() >= 1){
            commenOrder.setContributersRecive(commenOrder.getContributersRecive() + 1);
        }
        if(commenOrder.getContributersRecive() == commenOrder.getContributers()){
            commenOrder.setOrderState("2");
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

    @GetMapping("/waitingRequests")
    private List<CommenOrder> getwaitingRequests() {
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getAllOrder(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getOrderState().equals("1")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    @GetMapping("/runningOrders")
    private List<CommenOrder> getRunningOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getAllOrder(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getOrderState().equals("2")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    @GetMapping("/cancelOrderS")
    private String cancelOrderS(@RequestParam(value = "OrderId")Long id,@RequestParam(value = "reason")String reason){
        CommenOrder commenOrder = getCommenorder(id);
        if(commenOrder == null) {
            return "null Order";
        }
        commenOrder.setReason(reason);
        commenOrder.setOrderState("5");
        serviceService.saveOrder(commenOrder);
        return "order canceled";
    }

    @GetMapping("/waitingEvaluateS")
    private  List<CommenOrder> getWaitingEvaluateS(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getAllOrder(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderState().equals("3")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的求助 -》 待评价 -》 评价订单
    @PostMapping("/sCommentOrder")
    private String sCommentOrder(@RequestBody OrderEvalPOJO orderEvalPOJO){
        OrderEval orderEval;
        Optional<CommenOrder> c = serviceService.getCommenOrderById(orderEvalPOJO.getOrderId());
        CommenOrder commenOrder = c.get();
        if(commenOrder.getOrderEval() != null){
            orderEval = commenOrder.getOrderEval();
        }
        else {
            orderEval = new OrderEval();
        }
        orderEval.setsContent(orderEvalPOJO.getsContent());
        orderEval.setsTitle(orderEvalPOJO.getsTitle());
        orderEval.setsNum(orderEvalPOJO.getsNum());
        orderEval.setsDate(new Date());
        if(orderEval.getrContent() != null){
            commenOrder.setOrderState("4");
        }
        if(serviceService.saveComment(commenOrder,orderEval)){
            return "comment saved!";
        }
        return "false";
    }

    @GetMapping("/finishedOrderS")
    private List<CommenOrder> getFinishedOrderS() {
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getAllOrder(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderState().equals("4")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }

    @GetMapping("/failedOrderS")
    private  List<CommenOrder> getFailedOrderS(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getAllOrder(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderState().equals("5")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的订单 -》 所有订单
    @GetMapping("/rmyAllOrders")
    private List<CommenOrder> getRMyAllOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        return serviceService.getRMyAllOrders(userInfo);
    }
    //我的订单 -》 已接受
    @GetMapping("/rRecivedOrders")
    private List<CommenOrder> getRRecivedOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getRMyAllOrders(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getOrderState().equals("2")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的订单 -》 待评价
    @GetMapping("/rWaitingCommentOrders")
    private List<CommenOrder> getRWaitingCommentOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getRMyAllOrders(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getOrderState().equals("3")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的订单 -》 已完成
    @GetMapping("/rDoneOrder")
    private List<CommenOrder> getDoneOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getRMyAllOrders(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getOrderState().equals("4")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的订单 —》 失败订单
    @GetMapping("/rFailedOrders")
    private List<CommenOrder> getRFailedOrders(){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        List<CommenOrder> list = serviceService.getRMyAllOrders(userInfo);
        List<CommenOrder> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getOrderState().equals("5")) {
                CommenOrder commenOrder = list.get(i);
                result.add(commenOrder);
            }
        }
        return result;
    }
    //我的订单 -》 删除订单
    @GetMapping("/sDeleteOrder")
    private String sDeleteOrder(@RequestParam (value = "OrderId") Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        if(commenOrder == null){
            return "null order";
        }
        if(commenOrder.getOrderState().equals("1")){
            if(serviceService.sDeleteOrder(commenOrder)){
                return "delete!";
            }
            else {
                return "can not delete!";
            }
        } else {
            return "state failed!";
        }
    }
    //我的订单 -》 完成订单
    @GetMapping("/rFinishOrder")
    private String rFinishOrder(@RequestParam (value = "OrderId") Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        if(commenOrder == null){
            return "null order";
        }
        if(!commenOrder.getOrderState().equals("2")){
            return "not the running order";
        }
        if(commenOrder.getOrderState().equals("2")){
            commenOrder.setOrderState("3");
            serviceService.saveOrder(commenOrder);
            return "order finished!";
        }
        return "false";
    }
    //获得订单
    @GetMapping("/getCommenOrder")
    private CommenOrder getCommenOrder(@RequestParam (value = "OrderId") Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        return commenOrder;
    }
    //我的订单 -》 已接受 -》 取消订单
    @GetMapping("/rCancelOrder")
    private String rCancelOrder(@RequestParam (value = "OrderId") Long id){
        UserInfo userInfo = userService.getUserInfo(userService.getUser(SecurityContextHolder.getContext().getAuthentication().getName()));
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        if(commenOrder == null){
            return "null order";
        }
        if(!commenOrder.getOrderState().equals("2")){
            return "not the running order";
        }
        if(commenOrder.getOrderState().equals("2")){
            int i = 0;
            List<UserInfo> list = commenOrder.getUserinfoR();
            int total = list.size();
            for(i = 0; i < list.size(); i++){
                if(list.get(i).getId() == userInfo.getId()){
                    break;
                }
            }
            if(i == total){
                return "no such userinfo";
            }
//            list.remove(i);
            commenOrder.setOrderState("5");
            serviceService.saveOrder(commenOrder);
            return "order canceled!";
        }
        return "false";
    }

    //我的订单 -》 待评价 -》 评价订单
    @PostMapping("/rCommentOrder")
    private String rCommentOrder(@RequestBody OrderEvalPOJO orderEvalPOJO){
        OrderEval orderEval;
        Optional<CommenOrder> c = serviceService.getCommenOrderById(orderEvalPOJO.getOrderId());
        CommenOrder commenOrder = c.get();
        if(commenOrder.getOrderEval() != null){
            orderEval = commenOrder.getOrderEval();
        }
        else {
            orderEval = new OrderEval();
        }
        orderEval.setrContent(orderEvalPOJO.getrContent());
        orderEval.setrTitle(orderEvalPOJO.getrTitle());
        orderEval.setrNum(orderEvalPOJO.getrNum());
        orderEval.setrDate(new Date());
        if(orderEval.getsContent() != null){
            commenOrder.setOrderState("4");
        }
        if(serviceService.saveComment(commenOrder,orderEval)){
            return "comment saved!";
        }
        return "false";
    }

    private CommenOrder getCommenorder(Long id){
        Optional<CommenOrder> c = serviceService.getCommenOrderById(id);
        CommenOrder commenOrder = c.get();
        if(commenOrder == null){
            return null;
        }
        else return commenOrder;
    }
}
