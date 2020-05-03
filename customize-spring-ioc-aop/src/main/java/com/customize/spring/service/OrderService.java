package com.customize.spring.service;

import com.customize.spring.pojo.Order;

/**
 * 描述:  下单服务
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:17
 */
public interface OrderService {

    /**
     * 下单
     * @param order
     */
    void order(Order order);

}