package edu.gyc.histore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.histore.enums.OrderStatusEnum;
import edu.gyc.histore.enums.PayStatusEnum;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.dao.OrderDao;
import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.model.Product;
import edu.gyc.histore.service.OrderDetailService;
import edu.gyc.histore.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gyc.histore.service.ProductService;
import edu.gyc.histore.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements OrderService {

    @Resource
    private ProductService productService;

    @Resource
    private OrderDetailService orderDetailService;



    @Override
    @Transactional
    public Order create(Order order) {
        //查询商品数量和价格
        BigDecimal amount = BigDecimal.ZERO;
        List<OrderDetail> orderDetails=order.getOrderDetailList();
        if(orderDetails.isEmpty()){
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        for (OrderDetail orderDetail : orderDetails) {
            Long productId=orderDetail.getProductId();
            Product product = productService.getById(productId);
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            amount = product.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(amount);

            BeanUtils.copyProperties(product,orderDetail);
        }

        //减库存
        productService.decreaseStock(orderDetails);

        //订单和订单详情入库
        Long orderId= KeyUtil.genUniqueKey();
        order.setId(orderId);
        order.setOrderAmount(amount);
        order.setOrderStatus(OrderStatusEnum.NEW.getCode());
        order.setPayStatus(PayStatusEnum.WAIT.getCode());

        save(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(orderId);
            orderDetailService.save(orderDetail);
        }

        return order;
    }





    @Override
    public PageInfo<Order> findList(String buyerOpenid, int start, int size) {
        PageHelper.startPage(start, size);
        List<Order> orders=lambdaQuery().eq(Order::getBuyerOpenid,buyerOpenid).list();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(order.getId());
            order.setOrderDetailList(orderDetails);
         }
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }

    @Override
    @Transactional
    public Order cancel(Order order) {
        Order order1 = getById(order.getId());
        if (order1 == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (order1.getOrderStatus().equals(OrderStatusEnum.CANCEL.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //获取订单所属订单详情
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(order1.getId());
        if (orderDetails.isEmpty()) {
            new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
       //增加库存
       productService.increaseStock(orderDetails);

       //更新订单状态为取消
     boolean r=  lambdaUpdate().set(Order::getOrderStatus,OrderStatusEnum.CANCEL.getCode())
               .eq(Order::getId,order1.getId()).update();
       if(!r){
           throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
       }
       order1.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
       //若已支付还要退钱

        return order1;
    }

    @Override
    @Transactional
    public Order finish(Order order) {
        Order order1 = getById(order.getId());
        if (order1 == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        if (order1.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())) {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        boolean result=lambdaUpdate().set(Order::getOrderStatus,OrderStatusEnum.FINISHED.getCode())
                .eq(Order::getId,order1.getId()).update();
        if(!result){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        order1.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        return order1;
    }

    @Override
    @Transactional
    public Order paid(Order order) {
        Order order1 = getById(order.getId());
        if (order1 == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断订单状态
        if (!order1.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.info("Order {} 订单status: {}",order1.getId(),order1.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!order1.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.info("Order {} 支付status: {}",order1.getId(),order1.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //更新订单支付状态为成功
        boolean r=  lambdaUpdate().set(Order::getPayStatus,PayStatusEnum.SUCCESS.getCode())
                .eq(Order::getId,order1.getId()).update();
        if(!r){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        order1.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        //若已支付还要退钱

        return order1;
    }

    @Override
    public PageInfo<Order> findList(int start, int size) {
        PageHelper.startPage(start, size);
        List<Order> orders=list();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailService.findOrderDetailsByOrderId(order.getId());
            order.setOrderDetailList(orderDetails);
        }
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        return orderPageInfo;
    }
}
