package edu.gyc.histore.service;

import com.github.pagehelper.PageInfo;
import edu.gyc.histore.model.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.gyc.histore.model.Product;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
public interface OrderService extends IService<Order> {
    public Order create(Order Order);



    /** 查询订单列表. */
    PageInfo<Order> findList(String buyerOpenid, int start, int size);

    /** 取消订单. */
    Order cancel(Order Order);

    /** 完结订单. */
    Order finish(Order Order);

    /** 支付订单. */
    Order paid(Order Order);

    /** 查询订单列表. */
    PageInfo<Order> findList(int start, int size);

    //根据openid查询订单详情
    public Order findOrderOne(String openid, Long orderId);
}
