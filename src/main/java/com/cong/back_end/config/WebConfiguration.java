package com.cong.back_end.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Author Cong
 * @Create_time 2022-10-16 下午 08:18
 * @Project_name back_end
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    
    private TokenInterceptor tokenInterceptor;
    
    @Autowired
    public void setTokenInterceptor(TokenInterceptor tokenInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
    }
    
    /**
     * 解决跨域请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }
    
    /**
     * 异步请求配置
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }
    
    /**
     * 配置拦截器、拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        // User
        excludePath.add("/user/login");  // 登录
        excludePath.add("/user/register");  // 注册
        excludePath.add("/user/forgetPwdByEmail");
        excludePath.add("/user/forgetPwdByPassword");
        // Mail
        excludePath.add("/mail/getCheckCode");  // 获取邮箱验证码
        excludePath.add("/mail/getCheckCode2");  // 重置密码的验证码
        // Token
        excludePath.add("/token/getNewToken");  // 通过refreshToken生成新的token
        // Article
        excludePath.add("/article/getArticles");  // 获取所有文章基础信息
        excludePath.add("/article/getArticle");  // 获取指定文章的全部信息
        excludePath.add("/article/addViews");  // 增加指定文章访问量
        excludePath.add("/article/top5_archive");  // 文章top5（根据浏览量排序获得）和归档展示
        excludePath.add("/article/getArticlesByArchive");  // 归档文章展示
        // Access
        excludePath.add("/access/getThreeAccess");  // 展示三天的访问量
        excludePath.add("/access/updateAccess");  // 更新访问量
        // Error
        excludePath.add("/error");  // token失效重新获取会先重定向到error，需要将其放行（此时暂无token）
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
