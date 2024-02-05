package com.pddbend.shortlink.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pddbend.shortlink.project.dao.entity.LinkStatsTodayDO;
import com.pddbend.shortlink.project.dao.mapper.LinkStatsTodayMapper;
import com.pddbend.shortlink.project.service.LinkStatsTodayService;
import org.springframework.stereotype.Service;

/**
 * @Author: pddbend
 * @Date: 2024-02-05-20:48
 * @Description:  短链接今日统计接口实现层
 */
@Service
public class LinkStatsTodayServiceImpl extends ServiceImpl<LinkStatsTodayMapper, LinkStatsTodayDO> implements LinkStatsTodayService {
}