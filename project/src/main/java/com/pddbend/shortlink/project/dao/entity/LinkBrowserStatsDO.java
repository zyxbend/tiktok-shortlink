package com.pddbend.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pddbend.shortlink.project.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: pddbend
 * @Date: 2023-12-01-14:57
 * @Description: 浏览器统计访问实体
 */
@Data
@TableName("t_link_browser_stats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkBrowserStatsDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * 完整短链接
     */
    private String fullShortUrl;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 日期
     */
    private Date date;

    /**
     * 访问量
     */
    private Integer cnt;

    /**
     * 浏览器
     */
    private String browser;
}
