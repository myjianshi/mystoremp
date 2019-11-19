package edu.gyc.histore.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageInfo;
import edu.gyc.histore.exception.SellException;
import edu.gyc.histore.model.Order;
import edu.gyc.histore.model.Product;
import edu.gyc.histore.model.ProductCategory;
import edu.gyc.histore.service.ProductCategoryService;
import edu.gyc.histore.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Resource
    private ProductService productService;

    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public String list(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {

        PageInfo<Product> products = productService.findAll(start, size);
        model.addAttribute("page", products);
        return "product/list";

    }

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "start", defaultValue = "1") Integer start, @RequestParam(value = "size", defaultValue = "2") Integer size, Model model) {

        PageInfo<Product> products = productService.findAll(start, size);
        model.addAttribute("page", products);
        return "product/list";

    }

    /**
     * 商品下架
     *
     * @param id
     * @return
     */

    @GetMapping("/offsale")
    public String offsale(Long id, Model model) {
        try {
            productService.offSale(id);
        } catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/seller/product/list");
            return "common/error";
        }
        model.addAttribute("msg", "下架成功");
        model.addAttribute("url", "/seller/product/list");
        return "common/success";

    }

    @GetMapping("/onsale")
    public String onsale(Long id, Model model) {
        try {
            productService.onSale(id);
        } catch (SellException e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("url", "/seller/product/list");
            return "common/error";
        }
        model.addAttribute("msg", "上架成功");
        model.addAttribute("url", "/seller/product/list");
        return "common/success";

    }

    /**
     * 商品修改
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String update(@RequestParam(value = "id", required=false) Long id, Model model) {
        if (id != null) {
            Product product = productService.getById(id);

            model.addAttribute("product", product);

        }


        List<ProductCategory> productCategories = productCategoryService.list();
        model.addAttribute("categoryList", productCategories);
        return "product/edit";

    }

    @PostMapping("/update")
    public String update(Product product, Model model) {
        if (product.getId() != null) {
            model.addAttribute("msg", "更新成功");
        }else {
            model.addAttribute("msg", "新增成功");
        }
        productService.saveOrUpdate(product);
        // productService.update(product, Wrappers.<Product>lambdaQuery().eq(Product::getId,product.getId()));
        log.info("edit or add product id: {}",product.getId());


        model.addAttribute("url", "/seller/product/list");
        return "common/success";

    }

    /**
     * navigate to add product page
     * @return
     */

    @GetMapping("/index")
    public String index(){
        return "product/edit";
    }
}
