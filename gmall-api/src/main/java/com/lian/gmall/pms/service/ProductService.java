package com.lian.gmall.pms.service;

import com.lian.gmall.pms.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lian.gmall.vo.PageInfoVo;
import com.lian.gmall.vo.product.PmsProductParam;
import com.lian.gmall.vo.product.PmsProductQueryParam;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
public interface ProductService extends IService<Product> {

    /**
     * 根据复杂查询条件返回分页数据
     * @param productQueryParam
     * @return
     */
    PageInfoVo productPageInfo(PmsProductQueryParam productQueryParam);

    void saveProduct(PmsProductParam productParam);
}
