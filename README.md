# 一、项目介绍

TikTok 短链接系统，为视频博主的运营团队提供了一个高效、可靠的短链接管理平台。该平台不仅简化了长链接管理和分享过程，还提供深入分析和跟踪功能，用户可以灵活管理和优化其链接，从而实现更好的营销效果和业务成果。

短链接是一种服务，它通过特定算法将长URL转换成更短的版本，以便于分享和记忆。这个过程大致可以分为以下几个步骤：

- 生成短码：当提供一个长URL给短链接服务时，它会创建一个短小而唯一的标识码。
- 关联长短URL：这个标识码会与原始的长URL关联起来，并保存在一个存储系统中，例如数据库。
- 构建短链接：将短链接服务的域名与生成的标识码组合，形成可以访问的短链接。
- 实现重定向：当用户点击短链接时，服务会查询到与之关联的长URL，并将用户重定向到相应网页。
- 统计分析：短链接服务也能提供分析功能，记录链接被点击的次数、来源及访问者的地理位置信息等。

![image-20240316101410256](.\imgs\image-20240316101410256.png)

在视频运营方面，锻链接的主要作用包括以下几个方面：

- 方便分享：短链接更加简洁，易于在社交媒体、文字描述或视频说明中分享。这可以提升用户体验并增加点击率。 
- 易于追踪：短链接服务通常包含追踪和分析工具，可以汇总用户点击短链接的数据。团队可以看到点击量、访问者来源、设备类型等信息。 
- 提升品牌形象：使用自定义短链接可以加强品牌识别度。例如，将品牌名作为短链接的一部分，增强品牌曝光。 
- 助力营销活动：短链接易于记忆，有助于创建记忆点，从而提高营销活动中的目标URL的识别度和参与度。 
- 管理有效性：通过短链接可以对外部发布的链接进行集中管理，比如快速更改目标URL或下线不再使用的链接。 
- 提高安全性：短链接服务还可能包括安全功能，如防止恶意网站的跳转，确保用户体验的安全性。 
- 分析用户行为：通过跟踪短链接的点击和转化情况，团队可以了解用户的行为和偏好，进一步优化内容策略和提升观众参与度。。

# 二、项目部署

### 3.1.1 项目需求分析

能够实现快速服务横向扩展，以资源利用率为核心指标，具有可观测性的架构。

### 3.1.2 项目资源预计

项目支持以聚合服务和分布式微服务两种方式进行部署：

- 聚合服务：需要安装必要的中间件，如MySQL + Redis + Nacos，以及部署聚合服务 aggregation-service 还有网关服务 gateway-service，云服务器至少满足需要2核4G 内存。
- 分布式微服务：微服务部署支持引入限流，链路追踪等组件如 Sentinel、SkyWalking ，云服务器至少满足需要2核8G 内存。

项目部署网址：[www.tklink.ink](www.tklink.ink)

# 三、项目实现

## 3.1 技术选型

| 技术框架            | 名称               | 版本           | 官网                                                         |
| ------------------- | ------------------ | -------------- | ------------------------------------------------------------ |
| Spring Boot         | 基础框架           | 3.0.7          | https://spring.io/projects/spring-boot                       |
| MyBatis-Plus        | 持久层框架         | 3.5.3.1        | https://baomidou.com                                         |
| Redis               | 分布式缓存数据库   | Latest         | https://redis.io                                             |
| RocketMQ            | 消息队列           | 2.2.3          | https://rocketmq.apache.org                                  |
| ShardingSphere      | 数据库生态系统     | 5.3.2          | https://shardingsphere.apache.org                            |
| SpringCloud Alibaba | 分布式框架         | 2022.0.0.0-RC2 | https://github.com/alibaba/spring-cloud-alibaba              |
| SpringCloud Gateway | 网关框架           | 2022.0.3       | https://spring.io/projects/spring-cloud-gateway              |
| TTL                 | 增强版 ThreadLocal | 2.14.3         | https://github.com/alibaba/transmittable-thread-local        |
| FastJson2           | JSON 序列化工具    | 2.0.36         | https://github.com/alibaba/fastjson2                         |
| HuTool              | 小而全的工具集项目 | 5.8.2          | https://hutool.cn                                            |
| Redisson            | Redis Java 客户端  | 3.21.3         | [https://redisson.org](https://redisson.org/)                |
| Sentinel            | 流控防护框架       | 1.8.6          | https://github.com/alibaba/Sentine                           |
| SkyWalking          | 分布式链路追踪框架 | 9.5.0          | [https://skywalking.apache.org](https://skywalking.apache.org/) |

## 3.2 架构设计

### 3.2.1 架构设想

- **海量并发**：可能会面对大量用户同时访问的情况，尤其在高峰期，这会对系统的性能和响应速度提出很高的要求。
- **海量存储**：可能需要存储大量的用户数据，包括数据库、缓存等，需要足够的存储空间和高效的存储管理方案。
- **多租户场景**：通常支持多个租户共享同一套系统，需要保证租户间的数据隔离、安全性和性能。
- **数据安全性**：需要保证用户数据的安全性和隐私，防止未经授权的访问和数据泄露。
- **扩展性&可伸缩性**：需要具备良好的扩展性，以应对用户数量和业务规模的增长。

### 3.2.2 技术架构

TikTok短链接系统的技术架构图如下所示。

![img](https://camo.githubusercontent.com/a400869d9530e9d4ae0386a0ef315049486891d69af07c0897adc67d461f0439/68747470733a2f2f696d616765732d6d616368656e2e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f696d6167652d32303233313032363133323630363138302e706e67)

### 3.2.2 系统模块设计

从业务上来说分为以下几个模块：

- **网关服务**：服务请求分发和鉴权等。
- **用户服务**：用户登录、注册以及个人信息查看等功能。
- **分组服务**：短链接分组的增删改查功能。
- **短链服务**：包含短链接创建、修改以及访问监控等。

## 3.3 数据库设计

### 3.3.1 数据库表设计

![image-20240316122538731](.\imgs\image-20240316122538731.png)

- t_user 表：存储用户实体信息，包括用户id，用户名，密码，用户真实姓名，手机号，邮箱等字段；
- t_link 表：存储短链接实体信息，包括短链接id，域名，短链接标识，完整短链接，原始短链接，分组id，有效状态，过期时间等字段；
- t_group 表：存储短链接分组信息，包含分组id，分组名，所属用户，排序优先级，等字段
- t_link_goto表；路由表，用于解决分表情况下短链接跳转时查询对应的原始链接；
- t_link_access_stats表：存储常规统计信息，包括短链接点击的`pv`,`uv`,`uip`,每小时点击数，每周点击数；
- t_link_access_logs表：存储访客点击短链接的统计信息，包括访客`ip`,浏览器，地区，网络类型，设备等；
- t_link_stats_today表：存储今日点击访问信息统计，记录今日的`pv`, `uv`,`uip`等；
- t_link_os_stats表：存储访客点击的操作系统信息统计等；
- t_link_browser_stats表：存储访客点击的浏览器信息统计等；
- t_link_locale_stats表：存储访客点击的地区信息统计等；
- t_link_network_stats表：存储访客点击的网络类型信息统计等；
- t_link_device_stats表：存储访客点击的设备类型信息统计等；

### 3.3.2 数据库索引设计

1. t_user表对`username`添加唯一索引，防止用户名重复

   ```sql
   UNIQUE INDEX `idx_unique_username`(`username`) USING BTREE
   ```

2. `t_link`表中添加唯一索引来保证短链接全局唯一。因为后续分表时修改短链接分组`gid`要先删除再新增，可能导致新增的短链接路由到相同的表导致唯一索引冲突。所以设置的`del_time`和`full_short_url`组成唯一索引可以有效避免这种冲突。

   ```sql
   UNIQUE KEY `idx_unique_full-short-url` (`full_short_url`,`del_time`) USING BTREE
   ```

3. t_group表中添加`gid`和`username`的联合索引，加快查询效率。

   ```sql
   UNIQUE INDEX `idx_unique_username_gid`(`gid`, `username`) USING BTREE
   ```

4. t_link_access_stats表添加`full_short_url`, `gid`, `date`,`hour`联合索引，满足短链接统计信息按日期查询 

   ```sql
   UNIQUE INDEX `idx_unique_access_stats`(`full_short_url`, `gid`, `date`, `hour`) USING BTREE
   ```

5. t_link_stats_today添加`gid`，`full_short_url`, `date`联合索引，满足统计短链接今日查询时的连接查询

   ```sql
   UNIQUE KEY `idx_unique_today_stats` (`full_short_url`,`gid`,`date`) USING BTREE
   ```

6. 其他统计表简历索引都是为了满足日期查询，不再赘述

### 3.3.3 分库分表

> 为什么要分表？ 

考虑到业务数据量，已经未来的增长趋势，对t_user, t_link, t_group, t_link_goto,t_link_stats_today进行分表，以短链接的表 `t_link_0-15`举例，一共 16 张表。我们假设一张表能存 2000万，乘以 16，那就是 3 亿两千万。如果全部查询操作要走索引，查询效率依旧很高

> 分片键的选择

1. t_user表选择用`username`作为分片键，每次登录会把`username`作为参数传递，但是不会传递`uid`，所以`username`适合作为分片键
1. t_link表选择使用`gid`作为分片键，用户登录后会显示默认分组下的全部短链接，如果把`full_short_url`作为分片键，那么这个查询就会导致读扩散扫描所有表，去所有数据表中找到对应的短链接，效率太低。但是短链接跳转过程中，不会传入`gid`参数，因此引入路由表t_link_goto，通过`full_short_url`先去路由表中查询对应的`gid`然后找到对应的原始链接。
1. t_group表选择`gid`作为分片键。
1. 其余统计表都按照`gid`作为分片键。

## 3.4 缓存存储架构

### 3.4.1 引入缓存的必要性

短链接访问是标准的读多写少的场景，同时访问接口的并发量巨大，需要引入Redis加快访问的查询效率，提升接口响应时间，提升用户使用体验。

### 3.4.2 缓存一致性策略

为了保障缓存与数据库的同步，项目采用的是通过更新数据库删除缓存策略，保障短链接缓存与数据库之间的数据一致性功能。

1. 当应用程序进行写操作时，首先将数据写入数据库。
2. 然后，立即删除相应的缓存数据（或使缓存数据失效）。
3. 当下一个读取请求到达时，会发现缓存中没有相应的数据，于是从数据库中读取最新的数据，并将其存储在缓存中。![image-20240317150301404](.\imgs\image-20240317150301404.png)



## 3.4 项目特色介绍

### 3.4.1 用户注册防止缓存穿透

![](.\imgs\image-jichuan.png)

海量用户如果说查询的用户名存在或不存在，全部请求数据库，会将数据库直接打满。因此需要通过一些策略快速判断当前用户名是否已存在。项目中使用布隆过滤器来进行用户名的快速判断。如果布隆过滤器返回不存在则一定不存在于数据库中，如果返回存在，可能由于一定的误判，将不存在于数据库中的用户名判断为存在。在用户注册场景下，我们完全可以容忍这种误判的发生，如果误判发生，可以让用户进行重试。

### 3.4.2 用户敏感信息加密存储

为了保护用户数据隐私，将用户手机号码在数据库中进行加密存储，使用ShardingSphere配置相关加密规则。

加密原理流程由 ShardingSphere 进行了代理：

![](.\imgs\image_encode.png)

### 3.4.2 短链接生成算法

1. 生成哈希码

通过 Hash 算法将原始连接转换成一个 Hash 码，这里使用了 Google 出品的 MurmurHash 算法。因为生成的 Hash 码是十进制的，整体较长不利于短链接传播。为此，进一步将十进制转换为 62 进制。

> 为什么使用MurmurHash算法？

目前主流的哈希函数包含 MD5、SHA 等加密算法。其实我们并不关心反向解密的难度，更重要的是关注哈希的运算速度和冲突概率。

最终使用由 Google 开发的 MurmurHash 算法。MurmurHash 是一种非加密型哈希函数，适用于一般的哈希检索操作。与其他流行的哈希函数相比，MurmurHash 在处理规律性较强的键时具有更好的随机分布特性。由于它是非加密型的，相比 MD5、SHA 等加密算法，MurmurHash 的性能要高得多。

2. 进制转换

项目中使用Base62 编码进制转换的编码方式，Base62 是将数据转换为只包含数字和字母的一种方法。它使用了 62 个字符，分别是 0-9、a-z、A-Z，可以作为 URL 短链接、文件名等场景的字符串表示，相对于 16 进制或Base 64 进制等其他编码，Base62 具有更高的可读性和稳定性。6位的Base62 编码最多能生成568 亿左右组合数，完全满足短链接的生成需求。

### 3.4.3 保证短链接全局唯一

生成的短链接是需要保障在当前域名下唯一的，当我们发现哈希冲突后，将原始长链接与一个随机生成的 UUID 字符串拼接，通过拼接后的内容继续查询布隆过滤器，直到不存在为止。为了防止短链接频繁创建失败，还需要对重试设置最大次数，超过最大次数直接返回。

每次重试不能走数据库，会给数据库有很大压力，用布隆过滤器来判断

存在一种情况，短链接入库成功，但是并没有添加到布隆过滤器中（可能因为进程挂掉等等原因，由于没加事务，短链接入库不会回滚）。也就是说实际上入库了，但布隆过滤器显示短链不存在，此时再次插入该短链不就越过布隆过滤器，然后被唯一索引给拦截了。 因为这种情况出现的概率极低，所以把唯一索引称为兜底策略。

![image-20240317153136038](.\imgs\image-20240317153136038.png)

### 3.4.4 短链接缓存预热

如果一个短链接创建后可能会有大量用户访问（创建出来后第一时间同时访问），那么就会造成缓存击穿问题。

缓存击穿指在高并发的系统中，**一个热点数据缓存过期或者在缓存中不存在**，导致大量并发请求直接访问数据库，从而给数据库造成巨大压力，甚至可能引起宕机。

具体来说，当某个热点数据在缓存中过期时，**如果此时有大量并发请求同时访问这个数据**，由于缓存中不存在，所有请求都会直接访问数据库，导致数据库负载急剧增加。

![](.\imgs\image_yure.png)

缓存预热有很多种方式，比如定时任务从数据库中查询进行预热等。项目中在创建完短链接后就将短链接记录新增到缓存中。将缓存预热的话，还需要额外考虑过期时间。对于设置了过期时间的短链接，我们在缓存中也设置对应的时间即可。

如果短链接设置的永久有效，默认设置一个月的过期时间。如果一个月后还有人访问，再去数据库加载数据，再设置一个月的过期时间即可。

### 3.4.5 短链接跳转防止缓存击穿

首先通过短链接获取到对应的长链接，对长链接进行 302 重定向，最终访问原始网址实现短链接跳转。

![image-20240317154628735](.\imgs\image-20240317154628735.png)

> 为什么不用301？

就是 301 的话表示永久性的重定向，会在浏览器上缓存该响应永久重定向就在访问这个 URL 的时候，它只会第一次去调用短链接，调用完一次之后，缓存就会被保存下来。后面的访问就去调用缓存的短连接去拿到对应的目标地址。

> 如何防止缓存击穿？

主要使用双重判定所来防止缓存击穿，流程如下：

1. 获取锁：在查询数据库前，首先尝试获取一个分布式锁。只有一个线程能够成功获取锁，其他线程需要等待。
2. 查询数据库：如果双重判断确认数据确实不存在于缓存中，那么就执行查询数据库的操作，获取数据。
3. 写入缓存：获取到数据后，将数据写入缓存，并设置一个合适的过期时间，以防止缓存永远不会被更新。
4. 释放锁：最后，释放获取的锁，以便其他线程可以继续使用这个锁

![](.\imgs\image_doublecheck.png)

### 3.4.6 RocketMQ 削峰 短链接监控

海量访问短链接，监控统计信息如果直接访问数据库，会导致数据库负载变高，甚至数据库宕机。为此，需要引入消息队列削峰。

通过异步线程从RocketMQ 拉取短链接监控统计信息，然后存入数据库中。

![image-20240317160318360](.\imgs\image-20240317160318360.png)

### 3.4.7 防止消息队列重复消费

当消息队列由于网络问题或者是生产重试，会出现重复消费问题情况，为了保证数据的准确性需要对消息队列进行幂等处理。

在项目中，使用 Redis 消息去重表，不依赖事务，针对消息表本身做了状态的区分：消费中、消费完成。

如果消息已经在消费中，抛出异常，消息会触发延迟消费，在 RocketMQ 的场景下即发送到 RETRY TOPIC。

![img](https://cdn.nlark.com/yuque/0/2024/png/25782110/1709951361433-1c81f719-f2b0-48ad-8fe3-2ad9f1804b65.png?x-oss-process=image%2Fformat%2Cwebp%2Fresize%2Cw_1125%2Climit_0)

### 3.4.8 短链接流量控制

1. 短链接后管

根据登录用户做出控制，比如 x 秒请求后管系统的频率最多 x 次。可以通过 Redis `increment` 命令对一个数据进行递增，如果超过 x 次就会返回失败。这里需要注意周期是 x 秒，需要对 Redis 的 Key 设置 x 秒有效期。

但是 Redis 中对于 `increment` 命令是没有提供过期命令的，这就需要两步操作，进而出现原子性问题。为此，需要通过 LUA 脚本来保证原子性。

```lua
-- 设置用户访问频率限制的参数
local username = KEYS[1]
local timeWindow = tonumber(ARGV[1]) -- 时间窗口，单位：秒

-- 构造 Redis 中存储用户访问次数的键名
local accessKey = "short-link:user-flow-risk-control:" .. username

-- 原子递增访问次数，并获取递增后的值
local currentAccessCount = redis.call("INCR", accessKey)

-- 设置键的过期时间
redis.call("EXPIRE", accessKey, timeWindow)

-- 返回当前访问次数
return currentAccessCount
```

2. 短链接中台

根据接口进行流控，比如同一接口最大接受 20 QPS。具体使用Sentinel进行限流

## 3.6 项目代码介绍

### 3.6.1. 短链接生成

在短链接生成过程中，原始长链接经过哈希函数进行计算，生成一个哈希值。当我们发现冲突后，将原始长链接与一个随机生成的 UUID 字符串拼接，通过拼接后的内容继续查询布隆过滤器，直到不存在为止。如果超过指定次数，就抛出异常。

```java
private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
    int customGenerateCount = 0;
    String shorUri;
    while (true) {
        if (customGenerateCount > 10) {
            throw new ServiceException("短链接频繁生成，请稍后再试");
        }
        String originUrl = requestParam.getOriginUrl();
        originUrl += UUID.randomUUID().toString();
        shorUri = HashUtil.hashToBase62(originUrl);
        if (!shortUriCreateCachePenetrationBloomFilter.contains(createShortLinkDefaultDomain + "/" + shorUri)) {
            break;
        }
        customGenerateCount++;
    }
    return shorUri;
}
```

### 3.6.2. 缓存预热

创建完短链接后就将短链接记录新增到缓存中，并设置过期时间。

```java
@Transactional(rollbackFor = Exception.class)
@Override
public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
    verificationWhitelist(requestParam.getOriginUrl());
    String shortLinkSuffix = generateSuffix(requestParam);
    String fullShortUrl = StrBuilder.create(createShortLinkDefaultDomain)
            .append("/")
            .append(shortLinkSuffix)
            .toString();
    ShortLinkDO shortLinkDO = xxx;
    ShortLinkGotoDO linkGotoDO = ShortLinkGotoDO.builder()
            .fullShortUrl(fullShortUrl)
            .gid(requestParam.getGid())
            .build();
    try {
        baseMapper.insert(shortLinkDO);
        shortLinkGotoMapper.insert(linkGotoDO);
    } catch (DuplicateKeyException ex) {
        if (!shortUriCreateCachePenetrationBloomFilter.contains(fullShortUrl)) {
            shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        }
        throw new ServiceException(String.format("短链接：%s 生成重复", fullShortUrl));
    }
    // 将短链接新增到缓存中进行预热
    stringRedisTemplate.opsForValue().set(
            String.format(GOTO_SHORT_LINK_KEY, fullShortUrl),
            requestParam.getOriginUrl(),
            LinkUtil.getLinkCacheValidTime(requestParam.getValidDate()), TimeUnit.MILLISECONDS
    );
    shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
    return ShortLinkCreateRespDTO.builder()
            .fullShortUrl("http://" + shortLinkDO.getFullShortUrl())
            .originUrl(requestParam.getOriginUrl())
            .gid(requestParam.getGid())
            .build();
}
```

### 3.6.3 双重判定锁防止缓存击穿

双重判断：获取锁后，在查询数据库之前，再次检查一下缓存中是否存在数据。这是一个双重判断，如果缓存中存在数据，就直接返回；如果不存在，才继续执行查询数据库的操作。

```java
public String selectTrain(String id) {
	String cacheData = cache.get(id);
	if (StrUtil.isBlank(cacheData)) {
        Lock lock = getLock(id);
        lock.lock();
        try {
            cacheData = cache.get(id);
            if (StrUtil.isBlank(cacheData)) {
                String dbData = trainMapper.selectId(id);
                if (StrUtil.isNotBlank(dbData)) {
            		cahce.set(id, dbData);
                    cacheData = dbData;
                }
            }
        } finally {
            lock.unlock();
        }
    }
	return cacheData;
}
```

### 3.6.4 短链接后管限流

定义需要风控接口的规则：

```java
/**
 * 初始化限流配置
 */
@Component
public class SentinelRuleConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule createOrderRule = new FlowRule();
        createOrderRule.setResource("create_short-link");
        createOrderRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        createOrderRule.setCount(1);
        rules.add(createOrderRule);
        FlowRuleManager.loadRules(rules);
    }
}
```

如果触发风控，设置降级策略。

```java
public class CustomBlockHandler {

    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
```

在代码中引入 Sentinel 注解控制流控规则。

```java
/**
 * 创建短链接
 */
@PostMapping("/api/short-link/v1/create")
@SentinelResource(
        value = "create_short-link",
        blockHandler = "createShortLinkBlockHandlerMethod",
        blockHandlerClass = CustomBlockHandler.class
)
public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
    return Results.success(shortLinkService.createShortLink(requestParam));
}
```

### 3.6.5 TODO

# 四、测试结果

## 4.1 短链接新增接口并发量测试

使用Jmeter对分布式锁和布隆过滤器的性能进行对比测试。测试通过 Jmeter 配置了 40 个线程循环 100 次压测。

### 分布式锁

第一次压测：

![](.\imgs\image_20240317175313.jpg)

第二次压测：

![](.\imgs\2.jpg)

第三次压测：

![](.\imgs\3.jpg)

### 布隆过滤器

第一次压测：

![](.\imgs\4.jpg)

第二次压测：

![](C:\Users\Administrator\Desktop\短链接项目文档\imgs\5.jpg)

第三次压测：

![](.\imgs\6.jpg)

## 4.2短链接访问并发量测试

Jmeter 配置同上，40 个线程循环 100 次压测，**最终吞吐量大概在 3300 左右**。

![](.\imgs\7.jpg)


# 五、项目总结与反思

## 5.1 已识别出的优化项

1. 短链接修改

在最初的代码逻辑，如果存在短链接记录修改分组，那么就会变成短链接记录不存在问题。因为短链接记录是按照`gid`进行分表，可能不存在原始表中。

```java
LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
        .eq(ShortLinkDO::getGid, requestParam.getGid())
        .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
        .eq(ShortLinkDO::getDelFlag, 0)
        .eq(ShortLinkDO::getEnableStatus, 0);
ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
if (hasShortLinkDO == null) {
    throw new ClientException("短链接记录不存在");
}
```

原因是由于数据分表，索引按照创建的 `full_short_url` 字段可能存在冲突。

因此需要创建一个新的字段存储删除时间戳 `del_time`，防止唯一索引冲突。

> 如果短链接正在修改分组，这时有用户正在访问短链接，统计监控相关的分组还是之前的数据，是否就涉及到无法正确统计监控数据问题？

引入Redisson读写锁，对短链接的访问的同步机制，允许多个线程同时读取短链接，但在写入时保证独占访问，以确保数据的一致性和完整性。读写锁刚好适用于短链接修改这种读操作频繁，写操作较少的情况。

> 如果短链接修改获取到写锁，那用户获取不到访问的读锁，修改期间就不允许用户访问或者短链接访问统计吗？

通过消息队列发送统计短链接统计信息，使统计信息异步写入MySQL。

2. 消息队列不支持多活

当RocketMQ出现异常，或者宕机后，就会导致监控统计数据的丢失，因此需要系统支持消息队列多活机制，当RocketMQ发生宕机之后，可以通过修改配置的MQ快速切换到其他消息队列，保障系统的稳定性和可靠性。

3. 缓存更新策略

先写DB再删缓存的方式在高并发场景下要考虑缓存是否能够删除，如果程序删除了缓存，可能会导致缓存击穿问题，而更新频繁时则可能引发缓存雪崩。因此，在考虑缓存一致性模型时，务必充分考虑业务场景是否属于高并发模型。如果是高并发场景，删除缓存可能并不合适，此时应采用最终一致性策略。


## 5.2 架构演进的可能性

1. 数据分库存储

如果随着业务的增长，当出现以下两种情况时，我们需要考虑通过分库来将数据分散到多个数据库实例上，以提升整体系统的性能：

- 当单个数据库支持的连接数已经不足以满足客户端需求。
- 数据量已经超过单个数据库实例的处理能力。

不过，如果仅仅是单个数据库的连接不够客户端使用场景，可以优先考虑使用读写分离，减少分库带来的复杂性。

2. Redis 读写分离

目前 Redis 并没有做读写分离，可能会因为请求过大而造成一定的压力，因此日后可以考虑 Redis 读写分离。

3. 使用消息总线

目前的各个服务中消息队列的消息格式各不相同，导致生产者和消费者之间耦合较大，且不易扩展和维护。因此日后可以考虑使用消息总线的通信模式，定义统一的消息格式，这样每个服务只需要与消息总线进行通信，便于维护和扩展。

# 六、其他补充资料

## 6.1 开发踩坑盘点

分库分表连接查询出现笛卡尔积的情况，在连接查询今日访问信息的时候，通过SQL的查询是两条数据，但是通过Shardingsphere查询出10条记录，就是没有设置绑定表。因为一般分表之后的数据我们是不做连接查询的。

解决方法：通过在Shardingsphere添加绑定表的配置解决问题。
