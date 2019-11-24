package edu.gyc.histore.converter;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.form.OrderForm;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 廖师兄
 * 2017-06-18 23:41
 */
@Slf4j
public class OrderForm2OrderConverter {

    public static Order convert(OrderForm orderForm) {
        Gson gson = new Gson();
        Order order = new Order();

        order.setBuyerName(orderForm.getName());
        order.setBuyerPhone(orderForm.getPhone());
        order.setBuyerAddress(orderForm.getAddress());
        order.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        order.setOrderDetailList(orderDetailList);

        return order;
    }
}
