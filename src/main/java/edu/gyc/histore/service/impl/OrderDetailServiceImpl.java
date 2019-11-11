package edu.gyc.histore.service.impl;

import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.dao.OrderDetailDao;
import edu.gyc.histore.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetail> implements OrderDetailService {

    @Override
    public List<OrderDetail> findOrderDetailsByOrderId(Long orderId) {
       List<OrderDetail> orderDetails =lambdaQuery().eq(OrderDetail::getOrderId,orderId).list();
        return orderDetails;
    }
}
