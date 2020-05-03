package com.customize.spring.pojo;

/**
 * 描述:  库存
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:12
 */
public class Stock {

    private Integer num = 1000;
    private String name;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}