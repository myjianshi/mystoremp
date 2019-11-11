package edu.gyc.histore.service.impl;

import edu.gyc.histore.model.ProductCategory;
import edu.gyc.histore.dao.ProductCategoryDao;
import edu.gyc.histore.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
