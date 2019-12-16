package com.lian.gmall.pms.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lian.gmall.pms.entity.Brand;
import com.lian.gmall.pms.mapper.BrandMapper;
import com.lian.gmall.pms.service.BrandService;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
@Service
@Component
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {

}
