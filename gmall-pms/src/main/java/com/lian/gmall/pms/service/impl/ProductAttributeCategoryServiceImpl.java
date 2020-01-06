package com.lian.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.pms.entity.ProductAttributeCategory;
import com.lian.gmall.pms.mapper.ProductAttributeCategoryMapper;
import com.lian.gmall.pms.service.ProductAttributeCategoryService;
import com.lian.gmall.vo.PageInfoVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>
 * 产品属性分类表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
@Component
public class ProductAttributeCategoryServiceImpl extends ServiceImpl<ProductAttributeCategoryMapper, ProductAttributeCategory> implements ProductAttributeCategoryService {

    @Resource
    ProductAttributeCategoryMapper mapper;

    @Override
    public PageInfoVo productAttributeCategoryServicePageInfo(Integer pageNum, Integer pageSize) {

        IPage<ProductAttributeCategory> page = mapper.selectPage(new Page<ProductAttributeCategory>(pageNum, pageSize), null);
        return PageInfoVo.getVo(page, pageSize.longValue());
    }
}
