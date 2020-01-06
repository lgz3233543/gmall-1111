package com.lian.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.constant.SysCacheConstant;
import com.lian.gmall.pms.entity.ProductCategory;
import com.lian.gmall.pms.mapper.ProductCategoryMapper;
import com.lian.gmall.pms.service.ProductCategoryService;
import com.lian.gmall.vo.product.PmsProductCategoryWithChildrenItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Slf4j
@Service
@Component
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Resource
    ProductCategoryMapper mapper;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public List<PmsProductCategoryWithChildrenItem> listCatagoryWithChildren(Integer i) {

        Object cacheMenu = redisTemplate.opsForValue().get(SysCacheConstant.CATEGORY_MENU_CACHE_KEY);
        List<PmsProductCategoryWithChildrenItem> items = null;

        if(cacheMenu != null){
            // 缓存中有数据
            log.debug("菜单数据命中缓存...");
            items = (List<PmsProductCategoryWithChildrenItem>)cacheMenu;
        }else{
            items = mapper.listCatagoryWithChildren(i);
            redisTemplate.opsForValue().set(SysCacheConstant.CATEGORY_MENU_CACHE_KEY, items);
        }

        return items;
    }
}
