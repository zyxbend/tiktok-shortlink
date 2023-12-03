package com.pddbend.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: pddbend
 * @Date: 2023-12-03-18:59
 * @Description: 短链接访客监控响应参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsUvRespDTO {

    /**
     * 统计
     */
    private Integer cnt;

    /**
     * 访客类型
     */
    private String uvType;

    /**
     * 占比
     */
    private Double ratio;
}
