### 修改banner
[个性文字网址](http://patorjk.com/software/taag/)

###  @RequestMapping 
GET 查
POST 增 
PUT 修改 
DELETE 删除
都有对应Mapping

支持所有请求方式

### 405 返回码 
不支持请求格式的返回码

### spring boot 配置文件
* 自定义配置项
* 查找配置文件 搜到的是properties 可以使用
* [转yaml格式](toyaml.com/index.html)
* toyaml.com/index.html

### 集成热部署


### 集成持久层框架 Mybatis


### spring 拦截器 记录接口耗时
 #### 配置 mvc config
```java
package com.dajuancai.knowledge_plant.config;

import com.dajuancai.knowledge_plant.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        注册拦截器 拦截所有请求 忽略登录接口的拦截
        registry.addInterceptor(logInterceptor).addPathPatterns("/**").excludePathPatterns("/login/");
    }
}
```
#### 耗时 拦截器的实现
```java
package com.dajuancai.knowledge_plant.interceptor;

import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Component;
 import org.springframework.web.servlet.HandlerInterceptor;
 import org.springframework.web.servlet.ModelAndView;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;

 /**
  * 拦截器：Spring框架特有的，常用于登录校验，权限校验，  请求日志打印 /login
  *
  */
 @Component
 public class LogInterceptor implements HandlerInterceptor {

     private static final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

     @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         // 打印请求信息
         LOG.info("------------- LogInterceptor 开始 -------------");
         LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
         LOG.info("远程地址: {}", request.getRemoteAddr());

         long startTime = System.currentTimeMillis();
         request.setAttribute("requestStartTime", startTime);
         return true;
     }

     @Override
     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         long startTime = (Long) request.getAttribute("requestStartTime");
         LOG.info("------------- LogInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
     }
 }
```

### spring AOP 的使用

* 配置 AOP 
* 以后学习


### 集成 Validation 做参数校验
* 计划曾 springboot  start validation

### 雪花算法工具类

```java
package com.dajuancai.knowledge_plant.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Twitter的分布式自增ID雪花算法
 **/
@Component
public class SnowFlake {

    /**
     * 起始的时间戳
     */
    private final static long START_STMP = 1609459200000L; // 2021-01-01 00:00:00

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId = 1;  //数据中心
    private long machineId = 1;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    public SnowFlake() {
    }

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws ParseException {
        // 时间戳
        // System.out.println(System.currentTimeMillis());
        // System.out.println(new Date().getTime());
        //
        // String dateTime = "2021-01-01 08:00:00";
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // System.out.println(sdf.parse(dateTime).getTime());

        SnowFlake snowFlake = new SnowFlake(1, 1);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlake.nextId());
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}

```