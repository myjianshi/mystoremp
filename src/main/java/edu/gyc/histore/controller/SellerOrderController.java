package edu.gyc.histore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.model.HiTime;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {

        PageInfo<Order> orders = orderService.findList(start, size);
        model.addAttribute("page", orders);
        return "list";

    }

    @GetMapping("/")
    public String index(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {

        PageInfo<Order> orders = orderService.findList(start, size);

        model.addAttribute("page", orders);
        return "index";

    }

    @GetMapping("/admin")
    public String admin(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {

        PageInfo<Order> orders = orderService.findList(start, size);
        model.addAttribute("page", orders);
        return "admin";

    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam("id") Long orderId,Model model) {
        Order order = orderService.getById(orderId);
        if (order == null) {
            log.error("卖家端取消订单，查询不到订单");
            model.addAttribute("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            model.addAttribute("url", "/mystore/seller/order/");
            return "common/error";
        }
        try {
            orderService.cancel(order);
        } catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/mystore/seller/order/");
            return "common/error";
        }

        model.addAttribute("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        model.addAttribute("url", "/mystore/seller/order/");
        return "common/success";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") Long orderId,Model model) {
        Order order = orderService.findOneOrder(orderId);

        if (order == null) {
            log.error("卖家端订单详情，查询不到订单");
            model.addAttribute("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            model.addAttribute("url", "/mystore/seller/order/");
            return "common/error";
        }

        log.info("Order: {}",order);
        model.addAttribute("order", order);

        return "detail";
    }

    @GetMapping("/finish")
    public String finish(@RequestParam("id") Long orderId,Model model) {
        Order order = orderService.getById(orderId);

        if (order == null) {
            log.error("卖家端完结订单，查询不到订单");
            model.addAttribute("msg", ResultEnum.ORDER_NOT_EXIST.getMessage());
            model.addAttribute("url", "/mystore/seller/order/");
            return "common/error";
        }
        orderService.finish(order);
        model.addAttribute("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        model.addAttribute("url", "/mystore/seller/order/");
        return "common/success";

    }

    @GetMapping("/hitime")
    @ResponseBody
    public Object hi() {
        HiTime hiTime = new HiTime("柳如是", LocalDateTime.now());
        return hiTime;
    }
}
