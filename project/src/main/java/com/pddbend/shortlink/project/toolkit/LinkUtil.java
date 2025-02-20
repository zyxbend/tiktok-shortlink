package com.pddbend.shortlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
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

    /**
     * 获取用户访问设备
     *
     * @param request 请求
     * @return 访问设备
     */
    public static String getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("mobile")) {
            return "Mobile";
        }
        return "PC";
    }

    /**
     * 获取用户访问网络
     *
     * @param request 请求
     * @return 访问设备
     */
    public static String getNetwork(HttpServletRequest request) {
        String actualIp = getActualIp(request);
        // 这里简单判断IP地址范围，您可能需要更复杂的逻辑
        // 例如，通过调用IP地址库或调用第三方服务来判断网络类型
        return actualIp.startsWith("192.168.") || actualIp.startsWith("10.") ? "WIFI" : "Mobile";
    }

     /**
     * 获取原始链接中的域名
     * 如果原始链接包含 www 开头的话需要去掉
     *
     * @param url 创建或者修改短链接的原始链接
     * @return 原始链接中的域名
     */
    public static String extractDomain(String url) {
        String domain = null;
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (StrUtil.isNotBlank(host)) {
                domain = host;
                if (domain.startsWith("www.")) {
                    domain = host.substring(4);
                }
            }
        } catch (Exception ignored) {
        }
        return domain;
    }
}
