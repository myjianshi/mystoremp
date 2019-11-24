package edu.gyc.histore.service.impl;


import com.github.pagehelper.PageInfo;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.service.OrderService;
import edu.gyc.histore.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {

    @Resource
    private OrderService orderService;
    @Test
    void create() {
for(int i=0;i<10;i++){
    Order Order = new Order();
    Order.setBuyerName("鲁梦");
    Order.setBuyerAddress("贵州大学");
    Order.setBuyerPhone("123456789012");
    Order.setBuyerOpenid("520ls");

    //购物车
    List<OrderDetail> orderDetailList = new ArrayList<>();
    OrderDetail o1 = new OrderDetail();
    o1.setProductId(3L);
    o1.setProductQuantity(1+i);

    OrderDetail o2 = new OrderDetail();
    o2.setProductId(4L);
    o2.setProductQuantity(20-i);

    orderDetailList.add(o1);
    orderDetailList.add(o2);

    Order.setOrderDetailList(orderDetailList);

    Order resultOrder = orderService.create(Order);
    log.info("Create order: {}",resultOrder);
}

    }

    @Test
    void testSaveOrder() {
        Order order = new Order();
        order.setId(KeyUtil.genUniqueKey());
        order.setBuyerName("鲁梦");
        order.setBuyerAddress("贵州大学");
        order.setBuyerPhone("123456789012");
        order.setBuyerOpenid("520ls");

        orderService.save(order);

    }
    @Test
    void findList() {
       PageInfo<Order>  orderPageInfo=orderService.findList("110119", 0, 10);
       List<Order> orders=orderPageInfo.getList();
        for (Order order : orders) {
            log.info("Id {} order {}","110119",order.getOrderDetailList());
        }

    }

    @Test
    void cancel() {
        Order order=new Order();
        order.setId(1573227951619768099L);

        orderService.cancel(order);
    }

    @Test
    void finish() {
        Order order=new Order();
        order.setId(1573469367106L);

        orderService.finish(order);
    }

    @Test
    void paid() {
        Order order=new Order();
        order.setId(1573227449562543494L);

        orderService.paid(order);
    }

    @Test
    void testFindList() {
        log.info(orderService.findList(2,3).getList().toString());
    }
}