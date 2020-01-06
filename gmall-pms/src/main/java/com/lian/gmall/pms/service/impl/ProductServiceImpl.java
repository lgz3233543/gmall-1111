package com.lian.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.pms.entity.*;
import com.lian.gmall.pms.mapper.*;
import com.lian.gmall.pms.service.ProductService;
import com.lian.gmall.vo.PageInfoVo;
import com.lian.gmall.vo.product.PmsProductParam;
import com.lian.gmall.vo.product.PmsProductQueryParam;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
@Component
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductAttributeValueMapper productAttributeValueMapper;

    @Resource
    ProductFullReductionMapper productFullReductionMapper;

    @Resource
    ProductLadderMapper productLadderMapper;

    @Resource
    SkuStockMapper skuStockMapper;

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Override
    public PageInfoVo productPageInfo(PmsProductQueryParam param) {

        QueryWrapper<Product> productWrapper = new QueryWrapper<>();

        if(param.getBrandId() != null){
            productWrapper.eq("brand_id", param.getBrandId());
        }

        if(!StringUtils.isEmpty(param.getKeyword())){
            productWrapper.like("name", param.getKeyword());
        }

        if(param.getProductCategoryId() != null){
            productWrapper.eq("product_category_id", param.getProductCategoryId());
        }

        if(param.getProductSn() != null){
            productWrapper.like("product_sn", param.getProductSn());
        }

        if(param.getPublishStatus() != null){
            productWrapper.eq("publish_status", param.getPublishStatus());
        }

        if(param.getVerifyStatus() != null){
            productWrapper.eq("verify_status", param.getVerifyStatus());
        }

        IPage<Product> page = productMapper.selectPage(new Page<Product>(param.getPageNum(), param.getPageSize()), productWrapper);

        PageInfoVo pageInfo = new PageInfoVo(page.getTotal(), page.getPages(), param.getPageSize(),
                page.getRecords(), page.getCurrent());

        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveProduct(PmsProductParam productParam) {
        ProductServiceImpl proxy = (ProductServiceImpl) AopContext.currentProxy();

        // 不能 try-catch
        //1）、pms_product 保存商品基本信息
        proxy.saveBaseInfo(productParam);

        //5）、pms_sku_stock：保存 sku 库存表
        proxy.saveSkuStock(productParam);

        // 以下都可以 try-catch 互不影响
        //2）、pms_product_attribute_value：保存商品对应的所有属性的值
        proxy.saveProductAttributeValue(productParam);

        //3）、pms_product_full_reduction：保存商品的满减信息
        proxy.saveFullReduction(productParam);
        //4）、pms_product_ladder：保存商品的阶梯信息
        proxy.saveProductLadder(productParam);
    }

    /**
     * 保存商品基本信息
     * @param productParam
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveBaseInfo(PmsProductParam productParam) {
        Product product = new Product();
        BeanUtils.copyProperties(productParam, product);
        productMapper.insert(product);
        threadLocal.set(product.getId());
    }

    /**
     * 保存商品对应的所有属性的值
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductAttributeValue(PmsProductParam productParam) {
        List<ProductAttributeValue> valueList = productParam.getProductAttributeValueList();
        valueList.forEach((item)->{
            item.setProductId(threadLocal.get());
            productAttributeValueMapper.insert(item);
        });
    }

    /**
     * 保存商品的满减信息
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveFullReduction(PmsProductParam productParam) {
        List<ProductFullReduction> fullReductionList = productParam.getProductFullReductionList();
        fullReductionList.forEach((reduction)->{
            reduction.setProductId(threadLocal.get());
            productFullReductionMapper.insert(reduction);
        });
    }

    /**
     * 保存商品的阶梯信息
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveProductLadder(PmsProductParam productParam) {
        List<ProductLadder> ladderList = productParam.getProductLadderList();
        ladderList.forEach((ladder)->{
            ladder.setProductId(threadLocal.get());
            productLadderMapper.insert(ladder);
        });

        int i = 10/0;
    }

    /**
     * 保存 sku 库存表
     * @param productParam
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveSkuStock(PmsProductParam productParam) {
        List<SkuStock> skuStockList = productParam.getSkuStockList();
        for(int i=1; i<=skuStockList.size(); i++){

            SkuStock skuStock = skuStockList.get(i-1);

            if(StringUtils.isEmpty(skuStock.getSkuCode())){
                skuStock.setSkuCode(threadLocal.get() + "_" + i);
            }
            skuStock.setProductId(threadLocal.get());
            skuStockMapper.insert(skuStock);
        }
    }
}
