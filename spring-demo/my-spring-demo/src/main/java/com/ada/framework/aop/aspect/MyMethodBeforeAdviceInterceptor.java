package com.ada.framework.aop.aspect;


import com.ada.framework.aop.intercept.MyMethodInterceptor;
import com.ada.framework.aop.intercept.MyMethodInvocation;

import java.lang.reflect.Method;

public class MyMethodBeforeAdviceInterceptor extends MyAbstractAspectAdvice implements MyAdvice, MyMethodInterceptor {


    private MyJoinPoint joinPoint;
    public MyMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);

    }
    @Override
    public Object invoke(MyMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
