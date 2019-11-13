package edu.gyc.histore.controller;


import edu.gyc.histore.VO.ProductVO;
import edu.gyc.histore.VO.ProductInfoVO;

import edu.gyc.histore.model.Product;
import edu.gyc.histore.model.ProductCategory;
import edu.gyc.histore.service.ProductCategoryService;
import edu.gyc.histore.service.ProductService;
import edu.gyc.histore.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/buyer/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @Resource
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public Object getProductList() {
        List<Product> productInfos=productService.findUpAll();

        List<Integer> categoryTypeList = new ArrayList<>();
        for (Product productInfo : productInfos) {
            if(!categoryTypeList.contains(productInfo.getCategoryType())){
                categoryTypeList.add(productInfo.getCategoryType());
            }

        }

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVo = new ProductVO();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVoList = new ArrayList<>();
            for (Product product : productInfos) {
                if (product.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVo=new ProductInfoVO();
                    BeanUtils.copyProperties(product,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }

            productVo.setProductInfoVOList(productInfoVoList);
            productVoList.add(productVo);
        }

        //查询所有上架商品和类目

        return ResultVOUtil.success(productVoList);
    }
}

