package edu.gyc.histore.utils;

import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 处理表单校验异常的工具类
 */

public class FormValidateUtil {
    public static void formVaildate(BindingResult bindingResult, Model model) {
        //处理返回的错误信息
        StringBuffer errorMsg = new StringBuffer();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getDefaultMessage()).append("；<br>");
        }

        model.addAttribute("errors", errorMsg.toString());
    }
}
