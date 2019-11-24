package edu.gyc.histore.controller;

import edu.gyc.histore.model.Whore;
import edu.gyc.histore.utils.FormValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/whore")
public class WhoreController {

    @RequestMapping("/")
    public String hi() {
        return "product/whore";
    }

    @RequestMapping("/reg")
    public String reg(@Valid Whore whore, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            FormValidateUtil.formVaildate(bindingResult,model);

            return "product/whore";
        }
        model.addAttribute("msg", whore.toString());
        return "common/success";
    }

    @RequestMapping("/hireg")
    public String hireg(@Valid Whore whore,  Model model) {

        model.addAttribute("msg", whore.toString());
        return "common/success";
    }
}
