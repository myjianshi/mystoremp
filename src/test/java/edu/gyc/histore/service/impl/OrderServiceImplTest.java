package edu.gyc.histore.service.impl;

import com.baomidou.mybatisplus.annotation.TableField;
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

        Order Order = new Order();
        Order.setBuyerName("鲁梦");
        Order.setBuyerAddress("贵州大学");
        Order.setBuyerPhone("123456789012");
        Order.setBuyerOpenid("520ls");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId(1L);
        o1.setProductQuantity(3);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId(2L);
        o2.setProductQuantity(5);

        orderDetailList.add(o1);
        orderDetailList.add(o2);

        Order.setOrderDetailList(orderDetailList);

        Order resultOrder = orderService.create(Order);
        log.info("Create order: {}",resultOrder);
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
    }

    @Test
    void cancel() {
    }

    @Test
    void finish() {
    }

    @Test
    void paid() {
    }

    @Test
    void testFindList() {
    }
}