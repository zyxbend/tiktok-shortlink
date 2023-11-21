package com.pddbend.shortlink.admin.controller;

import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.common.convention.result.Results;
import com.pddbend.shortlink.admin.dto.req.GroupSaveReqDTO;
import com.pddbend.shortlink.admin.dto.resp.GroupRespDTO;
import com.pddbend.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午10:02
 * @Description: 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新建分组
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> saveGroup(@RequestBody GroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * 查询分组
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<GroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }
}
