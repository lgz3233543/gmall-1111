package com.lian.gmall.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.pms.entity.ProductFullReduction;
import com.lian.gmall.pms.mapper.ProductFullReductionMapper;
import com.lian.gmall.pms.service.ProductFullReductionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品满减表(只针对同商品) 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
public class ProductFullReductionServiceImpl extends ServiceImpl<ProductFullReductionMapper, ProductFullReduction> implements ProductFullReductionService {

}
