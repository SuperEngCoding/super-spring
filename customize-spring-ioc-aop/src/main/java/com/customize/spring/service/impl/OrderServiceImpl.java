package com.customize.spring.service.impl;

import com.customize.spring.dao.OrderDao;
import com.customize.spring.dao.StockDao;
import com.customize.spring.pojo.Order;
import com.customize.spring.service.OrderService;

/**
 * 描述:  下单实现类
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:18
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private StockDao stockDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    @Override
    public void order(Order order) {
//        没有IOC容器的情况下
//        OrderDao orderDao = new OrderDaoImpl();
//        // 保存订单
//        orderDao.save(order);
//
//        //扣除库存
//        StockDao stockDao = new StockDaoImpl();
//        stockDao.subStock(order.getName());

        // 有IOC容器的基础上
        orderDao.save(order);

        //扣除库存
        stockDao.subStock(order.getName());
        System.out.println("下单成功");
    }
}