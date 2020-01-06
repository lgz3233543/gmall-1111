package com.lian.gmall.pms;

import com.lian.gmall.pms.entity.Brand;
import com.lian.gmall.pms.entity.Product;
import com.lian.gmall.pms.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPmsApplicationTests {

    @Autowired
    ProductService productService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisTemplate<Object, Object> redisTemplateObj;

    @Test
    public void contextLoads() {
        Product byId = productService.getById(1);
        System.out.println(byId.getBrandName());
    }

    @Test
    public void redisTemplate() {
        redisTemplate.opsForValue().set("hello", "world");

        System.out.println("保存成功");

        String str = redisTemplate.opsForValue().get("hello");

        System.out.println("缓存中读的数据：" + str);
    }

    @Test
    public void redisTemplateObj() {

        Brand brand = new Brand();
        brand.setName("哈哈哈");

        redisTemplateObj.opsForValue().set("abc", brand);

        System.out.println("保存成功");

        Brand brand1 = (Brand) redisTemplateObj.opsForValue().get("abc");
        System.out.println("缓存中的数据：" + brand1);
    }
}
