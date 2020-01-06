package com.lian.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.pms.entity.ProductAttribute;
import com.lian.gmall.pms.mapper.ProductAttributeMapper;
import com.lian.gmall.pms.service.ProductAttributeService;
import com.lian.gmall.vo.PageInfoVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 商品属性参数表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
@Component
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements ProductAttributeService {

    @Resource
    ProductAttributeMapper mapper;

    @Override
    public PageInfoVo getCategoryAttributes(Long cid, Integer type, Integer pageSize, Integer pageNum) {

        QueryWrapper<ProductAttribute> wrapper = new QueryWrapper<ProductAttribute>()
                                                .eq("product_attribute_category_id", cid)
                                                .eq("type", type);

        IPage<ProductAttribute> page = mapper.selectPage(new Page<ProductAttribute>(pageNum, pageSize), wrapper);
        return PageInfoVo.getVo(page, pageSize.longValue());
    }
}
