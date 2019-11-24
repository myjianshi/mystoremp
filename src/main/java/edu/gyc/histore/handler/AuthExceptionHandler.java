package edu.gyc.histore.handler;

import edu.gyc.histore.exception.SellerAuthException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(value = SellerAuthException.class)
    public String handlerAuthException() {
        return "redirect:/mystore/seller/user/tologin";
    }
}
