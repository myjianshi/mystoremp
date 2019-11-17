package edu.gyc.histore.service.impl;

import com.github.pagehelper.PageInfo;
import edu.gyc.histore.model.OrderDetail;
import edu.gyc.histore.model.Product;
import edu.gyc.histore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ProductServiceImplTest {

    @Resource
    private ProductService productService;

    @Test
    void findUpAll() {
        log.info(productService.findUpAll().toString());
    }

    @Test
    void findAll() {
        for (int i = 0; i < 3; i++) {
            List<Product> productInfos=productService.findAll(i+1,3).getList();
            for (Product productInfo : productInfos) {
                log.info(productInfo.toString());
            }
        }
    }
    @Test
    public void saveTest() {

        for (int i = 0; i < 10; i++) {
            Product productInfo = new Product();

            productInfo.setProductName("美女"+i);
            productInfo.setProductPrice(new BigDecimal(666));
            productInfo.setProductStock(100);
            productInfo.setProductDescription("很好的order");
            productInfo.setProductIcon("http://xxxxx.jpg");
            productInfo.setProductStatus(0);
            productInfo.setCategoryType(1);

            productService.save(productInfo);
        }

    }
    @Test
    void findOne() {
    }

    @Test
    void increaseStock() {
        OrderDetail o1=new OrderDetail();
        o1.setProductId(1L);
        o1.setProductQuantity(2);

        OrderDetail o2=new OrderDetail();
        o2.setProductId(2L);
        o2.setProductQuantity(3);

        List<OrderDetail> orderDetails = Arrays.asList(o1, o2);

        productService.increaseStock(orderDetails);
    }

    @Test
    void decreaseStock() {

        OrderDetail o1=new OrderDetail();
        o1.setProductId(1L);
        o1.setProductQuantity(2);

        OrderDetail o2=new OrderDetail();
        o2.setProductId(2L);
        o2.setProductQuantity(3);

        List<OrderDetail> orderDetails = Arrays.asList(o1, o2);

        productService.decreaseStock(orderDetails);
    }

    @Test
    void onSale() {
        productService.onSale(2L);
    }

    @Test
    void offSale() {
        productService.offSale(1L);
    }
}