package com.pddbend.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static com.pddbend.shortlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * @Author: pddbend
 * @Date: 2023-11-28-15:41
 * @Description: 短链接工具类
 */
public class LinkUtil {
    /**
     * 获取短链接缓存有效期时间
     *
     * @param validDate 有效期时间
     * @return 有限期时间戳
     */
    public static long getLinkCacheValidTime(Date validDate) {
        return Optional.ofNullable(validDate)
                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }

    /**
     * 获取用户真实IP
     *
     * @param request 请求
     * @return 用户真实IP
     */
    public static String getActualIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR"
        };
        String ipAddress = null;
        for (String header : headers) {
            ipAddress = request.getHeader(header);
            if (ipAddress != null && !ipAddress.isEmpty() && !"unknown".equalsIgnoreCase(ipAddress)) {
                break;
            }
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * 获取用户访问操作系统
     *
     * @param request 请求
     * @return 访问操作系统
     */
    public static String getOs(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        Map<String, String> osMap = new LinkedHashMap<>();
        osMap.put("windows", "Windows");
        osMap.put("mac", "Mac OS");
        osMap.put("linux", "Linux");
        osMap.put("android", "Android");
        osMap.put("iphone", "iOS");
        osMap.put("ipad", "iOS");

        for (Map.Entry<String, String> os : osMap.entrySet()) {
            if (userAgent.contains(os.getKey())) {
                return os.getValue();
            }
        }

        return "Unknown";
    }

    /**
     * 获取用户访问浏览器
     *
     * @param request 请求
     * @return 访问浏览器
     */
    public static String getBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        Map<String, String> browserMap = new LinkedHashMap<>();
        browserMap.put("edg", "Microsoft Edge");
        browserMap.put("chrome", "Google Chrome");
        browserMap.put("firefox", "Mozilla Firefox");
        browserMap.put("safari", "Apple Safari");
        browserMap.put("opera", "Opera");
        browserMap.put("msie", "Internet Explorer");
        browserMap.put("trident", "Internet Explorer");

        for (Map.Entry<String, String> browser : browserMap.entrySet()) {
            if (userAgent.contains(browser.getKey())) {
                return browser.getValue();
            }
        }

        return "Unknown";
    }
}
