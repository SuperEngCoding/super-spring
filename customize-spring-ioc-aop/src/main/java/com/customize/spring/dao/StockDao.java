package com.customize.spring.dao;

/**
 * 描述:  库存
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:12
 */
public interface StockDao {

    /**
     * 减库存
     * @param name
     */
    void subStock(String name);
}