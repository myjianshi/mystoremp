package edu.gyc.histore.service.impl;

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
        return null;
    }

    @Override
    public Order cancel(Order Order) {
        return null;
    }

    @Override
    public Order finish(Order Order) {
        return null;
    }

    @Override
    public Order paid(Order Order) {
        return null;
    }

    @Override
    public PageInfo<Order> findList(int start, int size) {
        return null;
    }
}
