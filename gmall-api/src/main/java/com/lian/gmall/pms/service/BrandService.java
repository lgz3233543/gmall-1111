package com.lian.gmall.pms.service;

import com.lian.gmall.pms.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lian.gmall.vo.PageInfoVo;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author Lfy
 * @since 2019-12-12
 */
public interface BrandService extends IService<Brand> {

    PageInfoVo brandPageInfo(String keyword, Integer pageNum, Integer pageSize);
}
