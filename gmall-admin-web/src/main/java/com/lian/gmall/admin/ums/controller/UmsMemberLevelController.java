package com.lian.gmall.admin.ums.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lian.gmall.to.CommonResult;
import com.lian.gmall.ums.entity.MemberLevel;
import com.lian.gmall.ums.service.MemberLevelService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UmsMemberLevelController {

    @Reference
    MemberLevelService memberLevelService;

    /**
     * 查出所有会员登机
     * @return
     */
    @GetMapping("/memberLevel/list")
    public CommonResult memberLevelList(){
        List<MemberLevel> list = memberLevelService.list();
        return new CommonResult().success(list);
    }
}
