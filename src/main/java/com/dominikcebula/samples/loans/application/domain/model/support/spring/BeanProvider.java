package com.dominikcebula.samples.loans.application.domain.model.support.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanProvider implements ApplicationContextAware {
    static BeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        BeanProvider.beanFactory = context;
    }

    public static <T> T getBean(Class<T> requiredType) {
        if (beanFactory == null) {
            throw new IllegalStateException("Bean Factory is not initialized yet.");
        }
        return beanFactory.getBean(requiredType);
    }
}
