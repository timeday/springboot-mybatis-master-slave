package com.msql.dynamicdatasource.configuration;

import com.msql.dynamicdatasource.common.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不同的异常跳转到不同的错误业务 resolveException方法
 */
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    private final Logger logger = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView=new ModelAndView();
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (ex instanceof ServiceException) {
                modelAndView.setViewName(" ");
                modelAndView.addObject("","");
            } else {
                if (ex instanceof DataAccessException) {
                    modelAndView.setViewName(" ");
                    modelAndView.addObject("","");
                } else {
                    modelAndView.setViewName(" ");
                    modelAndView.addObject("","");
                }

                String message = String.format("interface [%s] has exception,method is %s.%s, exception message is %s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        ex.getMessage());

                logger.error(message, ex);
            }
        } else {
            if (ex instanceof NoHandlerFoundException) {
            } else {
                modelAndView.setViewName(" ");
                modelAndView.addObject("","");
            }
        }
        return modelAndView;
    }
}
