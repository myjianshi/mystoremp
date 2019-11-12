package edu.gyc.histore.controller;


import com.github.pagehelper.PageInfo;
import com.sun.tools.corba.se.idl.constExpr.Or;
import edu.gyc.histore.converter.OrderForm2OrderConverter;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.form.OrderForm;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.service.OrderService;
import edu.gyc.histore.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public Object createOrder(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Create order 参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        Order order = OrderForm2OrderConverter.convert(orderForm);
        if (order.getOrderDetailList().isEmpty()) {
            log.error("Create order 购物车没有东西");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        Order createRueslt = orderService.create(order);
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", createRueslt.getId());
        return ResultVOUtil.success(map);
    }

    @GetMapping("/list")
    public Object getOrderList(@RequestParam("openid") String openid,
                               @RequestParam(value = "start", defaultValue = "0") int start,
                               @RequestParam(value = "size", defaultValue = "10") int size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("openid is empty");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageInfo<Order> orderPageInfo = orderService.findList(openid, start, size);

        return ResultVOUtil.success(orderPageInfo.getList());
    }

    @GetMapping("/detail")
    public Object detail(String openid, Long orderId) {
        Order order = orderService.findOrderOne(openid, orderId);
        return  ResultVOUtil.success(order);
    }

    @GetMapping("/cancel")
    public Object cancelOrder(String openid, Long orderId) {
        Order order = orderService.findOrderOne(openid, orderId);
        orderService.cancel(order);
        return  ResultVOUtil.success();
    }
}

