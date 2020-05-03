package com.customize.spring.dao.impl;

import com.customize.spring.dao.OrderDao;
import com.customize.spring.pojo.Order;

/**
 * 描述:  订单
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:14
 */
public class OrderDaoImpl implements OrderDao {

    @Override
    public void save(Order order) {
        System.out.println("订单Id:" + order.getOrderId() + "保存成功");
    }
}