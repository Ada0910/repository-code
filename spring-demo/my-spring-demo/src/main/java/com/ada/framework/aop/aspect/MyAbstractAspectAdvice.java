package com.ada.framework.aop.aspect;

import java.lang.reflect.Method;

public abstract class MyAbstractAspectAdvice implements MyAdvice {
    private Method aspectMethod;
    private Object aspectTarget;
    public MyAbstractAspectAdvice(Method aspectMethod, Object aspectTarget) {
       this.aspectMethod = aspectMethod;
       this.aspectTarget = aspectTarget;
    }

    public Object invokeAdviceMethod(MyJoinPoint joinPoint, Object returnValue, Throwable tx) throws Throwable{
        Class<?> [] paramTypes = this.aspectMethod.getParameterTypes();
        if(null == paramTypes || paramTypes.length == 0){
            return this.aspectMethod.invoke(aspectTarget);
        }else{
            Object [] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i ++) {
                if(paramTypes[i] == MyJoinPoint.class){
                    args[i] = joinPoint;
                }else if(paramTypes[i] == Throwable.class){
                    args[i] = tx;
                }else if(paramTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspectTarget,args);
        }
    }


}
