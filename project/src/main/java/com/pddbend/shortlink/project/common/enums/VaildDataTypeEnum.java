package com.pddbend.shortlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author: pddbend
 * @Date: 2023-11-27-14:21
 * @Description:  短链接有效期枚举类型
 */

@Getter
@RequiredArgsConstructor
public enum VaildDataTypeEnum {

    /**
     * 永久有效
     */
    PERMANENT(0),

    /**
     * 自定义
     */
    CUSTOM(1);
    private final int type;


}
