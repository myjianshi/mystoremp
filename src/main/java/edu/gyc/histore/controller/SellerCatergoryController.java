package edu.gyc.histore.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.histore.model.ProductCategory;
import edu.gyc.histore.service.ProductCategoryService;
import edu.gyc.histore.utils.FormValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCatergoryController {

    @Resource
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<ProductCategory> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(required = false) Integer id, Model model) {
        if (id!=null) {//有id值传过来
            ProductCategory category = categoryService.getOne(Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getCategoryId, id));
            model.addAttribute("category", category);
        }
        return "category/edit";
    }

    @PostMapping("/update")
    public String update(@Valid ProductCategory category, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            FormValidateUtil.formVaildate(bindingResult,model);
            return "category/edit";
        }

        if (category.getCategoryId() !=null) {
            categoryService.update(category, Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getCategoryId, category.getCategoryId()));
            model.addAttribute("msg", "类目更新成功！");
        }else {
            categoryService.saveOrUpdate(category);
            model.addAttribute("msg", "新建类目成功！");
        }
        model.addAttribute("url", "/mystore/seller/category/list");

        return "common/success";
    }
}
