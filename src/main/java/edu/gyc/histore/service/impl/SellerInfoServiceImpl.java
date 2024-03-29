package edu.gyc.histore.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import edu.gyc.histore.model.SellerInfo;
import edu.gyc.histore.dao.SellerInfoDao;
import edu.gyc.histore.service.SellerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 卖家信息表 服务实现类
 * </p>
 *
 * @author ls
 * @since 2019-11-11
 */
@Service
public class SellerInfoServiceImpl extends ServiceImpl<SellerInfoDao, SellerInfo> implements SellerInfoService {


    @Override
    public SellerInfo getSellerByOpenid(String openid) {
        SellerInfo sellerInfo = this.getOne(Wrappers.<SellerInfo>lambdaQuery().eq(SellerInfo::getOpenid, openid));
        return sellerInfo;
    }
}
