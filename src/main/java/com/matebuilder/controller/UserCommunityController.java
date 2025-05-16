package com.matebuilder.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matebuilder.common.api.R;
import com.matebuilder.entity.UserCommunity;
import com.matebuilder.service.IUserCommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-community")
@Api(tags = "用户社区管理")
public class UserCommunityController {
    
    @Autowired
    private IUserCommunityService userCommunityService;

    @ApiOperation("分页查询用户社区")
    @GetMapping("/list")
    public R<Page<UserCommunity>> list(
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer current,
            @ApiParam("每页数量") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam("用户ID") @RequestParam(required = false) Integer userId) {
        Page<UserCommunity> page = new Page<>(current, size);
        QueryWrapper<UserCommunity> queryWrapper = new QueryWrapper<>();
        if (userId != null) {
            queryWrapper.eq("user_id", userId);
        }
        return R.ok(userCommunityService.page(page, queryWrapper));
    }

    @ApiOperation("获取社区详情")
    @GetMapping("/{id}")
    public R<UserCommunity> getById(@ApiParam("社区ID") @PathVariable Integer id) {
        return R.ok(userCommunityService.getById(id));
    }

    @ApiOperation("创建社区")
    @PostMapping
    public R<Boolean> save(@ApiParam("社区信息") @RequestBody UserCommunity userCommunity) {
        return R.ok(userCommunityService.save(userCommunity));
    }

    @ApiOperation("更新社区")
    @PutMapping
    public R<Boolean> update(@ApiParam("社区信息") @RequestBody UserCommunity userCommunity) {
        return R.ok(userCommunityService.updateById(userCommunity));
    }

    @ApiOperation("删除社区")
    @DeleteMapping("/{id}")
    public R<Boolean> delete(@ApiParam("社区ID") @PathVariable Integer id) {
        return R.ok(userCommunityService.removeById(id));
    }
}
