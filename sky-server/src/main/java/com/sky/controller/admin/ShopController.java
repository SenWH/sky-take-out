package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/admin/shop")
@Api(tags="店铺相关接口")
public class ShopController {
    private static final  String key = "SHOP_STATUS";
    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation("获取商品状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(key);
        log.info("获取的状态为:{}",status==1? "营业中":"打烊了");
        return Result.success(status);
    }

    @PutMapping("/{status}")
    @ApiOperation("设置商品状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("设置的状态为:{}",status==1? "营业中":"打烊了");
        redisTemplate.opsForValue().set(key,status);
        return Result.success();
    }
}
