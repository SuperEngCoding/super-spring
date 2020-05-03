package com.customize.spring.dao.impl;

import com.customize.spring.dao.StockDao;

/**
 * 描述:
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:15
 */
public class StockDaoImpl implements StockDao {

    @Override
    public void subStock(String name) {
        System.out.println("商品:" + name + " 库存扣减成功");
    }
}