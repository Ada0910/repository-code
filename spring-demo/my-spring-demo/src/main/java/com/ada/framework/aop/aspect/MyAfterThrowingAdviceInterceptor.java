package com.ada.framework.aop.aspect;

import com.ada.framework.aop.intercept.MyMethodInterceptor;
import com.ada.framework.aop.intercept.MyMethodInvocation;
import java.lang.reflect.Method;

public class MyAfterThrowingAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {


    private String throwingName;

    public MyAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName){
        this.throwingName = throwName;
    }
}
