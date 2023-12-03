package com.pddbend.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: pddbend
 * @Date: 2023-12-03-18:58
 * @Description: 短链接浏览器监控响应参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsBrowserRespDTO {

    /**
     * 统计
     */
    private Integer cnt;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 占比
     */
    private Double ratio;
}
