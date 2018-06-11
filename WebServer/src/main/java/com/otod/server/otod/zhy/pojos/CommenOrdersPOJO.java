package com.otod.server.otod.zhy.pojos;

import com.otod.server.otod.zhy.model.CommenOrder;

import java.util.List;

/**
 * Created by zhang on 2018/6/7.
 */
public class CommenOrdersPOJO {

    private List<CommenOrder> commenOrders;

    public CommenOrdersPOJO(List<CommenOrder> list){
        this.commenOrders = list;
    }

    public List<CommenOrder> getCommenOrders() {
        return commenOrders;
    }

    public void setCommenOrders(List<CommenOrder> commenOrders) {
        this.commenOrders = commenOrders;
    }
}
