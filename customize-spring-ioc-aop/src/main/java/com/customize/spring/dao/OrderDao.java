package com.customize.spring.dao;

import com.customize.spring.pojo.Order;

/**
 * 描述:  订单接口
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:08
 */
public interface OrderDao {

    /**
     * 保存订单详情
     * @param order
     */
    void save(Order order);



}