package edu.gyc.histore.service;

import edu.gyc.histore.model.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
public interface OrderDetailService extends IService<OrderDetail> {
    List<OrderDetail> findOrderDetailsByOrderId(Long orderId);
}
