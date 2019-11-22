package edu.gyc.histore.auth;

import edu.gyc.histore.constant.CookieConstant;
import edu.gyc.histore.constant.RedisConstant;
import edu.gyc.histore.exception.SellerAuthException;
import edu.gyc.histore.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution( public * edu.gyc.histore.controller.Seller*.*(..))"+
                "&&!execution(public * edu.gyc.histore.controller.SellerInfoController.*(..))")
    public void verify() {


    }

    @Before("verify()")
    public void doVerify() {
      ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //查询cookie

        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("登录校验，Cookie获取不到cookie");
            throw new SellerAuthException();
        }

        //去Redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("登录校验，redis中查询不到token");
            throw new SellerAuthException();
        }
    }
}
