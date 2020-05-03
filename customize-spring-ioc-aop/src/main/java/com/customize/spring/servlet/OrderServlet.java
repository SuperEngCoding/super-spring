package com.customize.spring.servlet;

import com.customize.spring.factory.BeanFactory;
import com.customize.spring.pojo.Order;
import com.customize.spring.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 描述:  servlet
 *
 * @author SuperEngCoding
 * @create 2020-05-03 17:35
 */
@WebServlet(name="orderServlet",urlPatterns = "/order")
public class OrderServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码
        req.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("userId");
        String name = req.getParameter("name");

        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setName(name);
        try {
            OrderService orderService = (OrderService) BeanFactory.getBean("orderService");
            // 2. 调用service层方法
            orderService.order(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().print("下单成功");
    }
}