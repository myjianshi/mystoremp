package edu.gyc.histore.controller;


import edu.gyc.histore.constant.CookieConstant;
import edu.gyc.histore.constant.RedisConstant;
import edu.gyc.histore.enums.ResultEnum;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.model.SellerInfo;
import edu.gyc.histore.service.SellerInfoService;
import edu.gyc.histore.utils.CookieUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 卖家信息表 前端控制器
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@Controller
@RequestMapping("/seller/user")
public class SellerInfoController {

    @Resource
    private SellerInfoService sellerInfoService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/login")
    public String login(String openid, HttpServletResponse response, Model model) {
        SellerInfo sellerInfo = sellerInfoService.getSellerByOpenid(openid);
        if (sellerInfo == null) {
            model.addAttribute("msg", ResultEnum.LOGIN_FAIL.getMessage());
            model.addAttribute("url", "/seller/order/");
            return "common/error";
        }

        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        return "redirect:/seller/order/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {
        //1. 从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 清除redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        model.addAttribute("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        model.addAttribute("url", "/seller/order/");
        return "/login";
    }

    @RequestMapping("/tologin")
    public String tologin() {
        return "/login";
    }
}

