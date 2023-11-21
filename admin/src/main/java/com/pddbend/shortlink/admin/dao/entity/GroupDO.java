package com.pddbend.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.pddbend.shortlink.admin.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午9:54
 * @Description: 分组持久层实体
 */

@Data
@TableName("t_group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDO extends BaseDO {
    /**
     * id
     */
    private Long id;

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 创建分组用户名
     */
    private String username;

    /**
     * 分组排序
     */
    private Integer sortOrder;

}
