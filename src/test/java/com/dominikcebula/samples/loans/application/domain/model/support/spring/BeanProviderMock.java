package com.dominikcebula.samples.loans.application.domain.model.support.spring;

import org.springframework.beans.factory.BeanFactory;

import static org.mockito.Mockito.*;

public class BeanProviderMock {
    private static BeanFactory oldBeanFactory;

    public static void setUp() {
        if (BeanProvider.beanFactory == null || notMockedYet()) {
            oldBeanFactory = BeanProvider.beanFactory;
            BeanProvider.beanFactory = mock(BeanFactory.class);
        }
    }

    public static <T> void registerBean(Class<T> type, T bean) {
        when(BeanProvider.beanFactory.getBean(type)).thenReturn(bean);
    }

    public static void tearDown() {
        BeanProvider.beanFactory = oldBeanFactory;
    }

    private static boolean notMockedYet() {
        return !mockingDetails(BeanProvider.beanFactory).isMock();
    }
}