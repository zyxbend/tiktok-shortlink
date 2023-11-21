package com.pddbend.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pddbend.shortlink.admin.dao.entity.GroupDO;
import com.pddbend.shortlink.admin.dao.mapper.GroupMapper;
import com.pddbend.shortlink.admin.service.GroupService;
import com.pddbend.shortlink.admin.toolkit.RandomGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午9:57
 * @Description: 短链接分组接口层实现
 */

@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    /**
     * 新增短链接分组
     *
     * @param groupName 短链接分组名称
     */
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do {
            gid = RandomGenerator.generatorRandom();

        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
                .gid(RandomGenerator.generatorRandom())
                .name(groupName)
                .build();
        baseMapper.insert(groupDO);
    }

    /**
     * 判断gid是否存在
     * @param gid 分组标识
     * @return 是否存在
     */
    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }
}
