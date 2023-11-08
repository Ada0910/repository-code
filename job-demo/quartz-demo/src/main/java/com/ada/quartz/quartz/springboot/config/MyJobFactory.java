package com.ada.quartz.quartz.springboot.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

/**
 *
 * 将Spring的对象注入到Quartz Job
 * 2、定义一个 MyJobFactory，继承 AdaptableJobFactory。使用 Spring 的 AutowireCapableBeanFactory，把 Job 实例注入到容器中
 */
@Component
public class MyJobFactory extends AdaptableJobFactory {
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);

        return jobInstance;
    }
}