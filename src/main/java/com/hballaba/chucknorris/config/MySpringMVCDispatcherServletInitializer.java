package com.hballaba.chucknorris.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Setting tomcat
public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
//        указываем класс конфигурации
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
//        указываем корень dispatcherServlet
        return new String[] {""};
    }
}
