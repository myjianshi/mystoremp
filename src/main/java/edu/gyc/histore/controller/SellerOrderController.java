package edu.gyc.histore.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
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
}
