package edu.gyc.histore.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.histore.model.ProductCategory;
import edu.gyc.histore.dao.ProductCategoryDao;
import edu.gyc.histore.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryDao, ProductCategory> implements ProductCategoryService {

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        List<ProductCategory> productCategories = new ArrayList<>();
        for (Integer type : categoryTypeList) {
            productCategories.add(getOne(Wrappers.<ProductCategory>lambdaQuery().eq(ProductCategory::getCategoryType, type)));
        }
        return productCategories;
    }
}
