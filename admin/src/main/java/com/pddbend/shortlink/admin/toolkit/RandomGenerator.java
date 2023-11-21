package com.pddbend.shortlink.admin.toolkit;

import java.util.Random;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午10:11
 * @Description: 分组ID随机数生成器
 */
public final class RandomGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成随机分组ID
     * @param count 随机数长度
     * @return 分组ID
     */
    public static String generatorRandomString(int count) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (count-- != 0) {
            int character = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * 生成随机分组ID
     * @return 分组ID
     */
    public static String generatorRandom() {
        return generatorRandomString(6);
    }
}