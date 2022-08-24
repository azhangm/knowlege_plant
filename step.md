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
