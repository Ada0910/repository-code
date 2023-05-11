package com.ada.framework.aop.aspect;


import com.ada.framework.aop.intercept.MyMethodInterceptor;
import com.ada.framework.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;


public class MyAfterReturningAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {

    private MyJoinPoint joinPoint;

    public MyAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }
}
