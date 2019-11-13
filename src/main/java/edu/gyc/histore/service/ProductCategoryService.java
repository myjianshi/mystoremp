package edu.gyc.histore.service;

import edu.gyc.histore.model.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
