package com.ada.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * 需要被代理的对象
 */
public interface MyJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
