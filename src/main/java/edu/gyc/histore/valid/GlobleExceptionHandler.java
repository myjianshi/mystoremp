package edu.gyc.histore.valid;


import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

//全局异常处理适合用在ajax的异常处理，这个项目用不到
@RestControllerAdvice
@Component
public class GlobleExceptionHandler {



    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String defultExcepitonHandler(Exception ex, Model model) {

        ex.printStackTrace();
        if(ex instanceof BindException){
            //处理返回的错误信息
            StringBuffer errorMsg = new StringBuffer();
            BindException c = (BindException) ex;
            List<ObjectError> errors = c.getBindingResult().getAllErrors();
            for (ObjectError error : errors) {
                errorMsg.append(error.getDefaultMessage()).append(";");
            }

            model.addAttribute("errors", errors);
            return errorMsg.toString();

        }
        return "";

    }

}

