package edu.gyc.histore.service;

import com.github.pagehelper.PageInfo;

import edu.gyc.histore.model.Order;
import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.model.Product;
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
public interface ProductService extends IService<Product> {
    List<Product> findUpAll();

    PageInfo<Product> findAll(int start, int size);



    //加库存
    void increaseStock(List<OrderDetail> orderList);

    //减库存
    void decreaseStock(List<OrderDetail> orderList);

    //上架
    void onSale(Long productId);

    //下架
    void offSale(Long productId);
}
