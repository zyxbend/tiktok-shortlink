package com.pddbend.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.common.convention.result.Results;
import com.pddbend.shortlink.admin.dto.resp.UserActualRespDTO;
import com.pddbend.shortlink.admin.dto.resp.UserRespDTO;
import com.pddbend.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午2:42
 * @Description: 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * 根据用户名查询信息
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable ("username") String username){
        return Results.success(userService.getUserByUsername(username));
    }

    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getUserAactualByUsername(@PathVariable ("username") String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username),UserActualRespDTO.class));
    }


}
