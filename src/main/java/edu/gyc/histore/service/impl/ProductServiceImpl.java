package edu.gyc.histore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.histore.enums.ProductStatusEnum;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.model.Product;
import edu.gyc.histore.dao.ProductDao;
import edu.gyc.histore.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

    @Override
    public List<Product> findUpAll() {

        return lambdaQuery().eq(Product::getProductStatus, ProductStatusEnum.UP).list();
    }

    @Override
    public PageInfo<Product> findAll(int start, int size) {
        PageHelper.startPage(start, size);
        List<Product> products=list();

        PageInfo<Product> data = new PageInfo<>(products);
        return data;
    }




    @Override
    public void increaseStock(List<OrderDetail> orderList) {
        for (OrderDetail orderDetail : orderList) {
            Long id=orderDetail.getProductId();
            Product product = getById(id);
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int n=product.getProductStock()+orderDetail.getProductQuantity();
            boolean updateResult=lambdaUpdate().set(Product::getProductStock,n)
                    .eq(Product::getId,orderDetail.getProductId()).update();
            log.info("Update {} 库存为：{}",product.getProductName(),n);
        }

    }

    @Override
    public void decreaseStock(List<OrderDetail> orderList) {
        for (OrderDetail orderDetail : orderList) {
            Long id=orderDetail.getProductId();
            Product product = getById(id);
            if (product == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            int n=product.getProductStock()-orderDetail.getProductQuantity();
            if(n<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            boolean updateResult=lambdaUpdate().set(Product::getProductStock,n)
                    .eq(Product::getId,orderDetail.getProductId()).update();
            log.info("Update {} 库存为：{}",product.getProductName(),n);
        }
    }

    @Override
    public void onSale(Long productId) {
        Product product = getById(productId);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStatus().equals(ProductStatusEnum.UP.getCode())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        lambdaUpdate().eq(Product::getId, productId).set(Product::getProductStatus, ProductStatusEnum.UP.getCode()).update();
        log.info("update 商品 {} 状态为上架。",product.getProductName());
    }

    @Override
    public void offSale(Long productId) {
        Product product = getById(productId);
        if (product == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        lambdaUpdate().eq(Product::getId, productId).set(Product::getProductStatus, ProductStatusEnum.DOWN.getCode()).update();
        log.info("update 商品 {} 状态为下架。",product.getProductName());
    }
}
