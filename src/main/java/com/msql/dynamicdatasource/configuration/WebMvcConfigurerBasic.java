package com.msql.dynamicdatasource.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;


@Configuration
public class WebMvcConfigurerBasic implements WebMvcConfigurer {

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new CustomHandlerExceptionResolver());
    }

    /**
     *  addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
     *  registry.addViewController("请求路径").setViewName("请求页面文件路径")
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.
    }


}
