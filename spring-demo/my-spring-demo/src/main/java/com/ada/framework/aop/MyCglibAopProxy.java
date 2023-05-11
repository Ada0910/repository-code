package com.ada.framework.aop;

import com.ada.framework.aop.support.MyAdvisedSupport;

public class MyCglibAopProxy implements MyAopProxy {
    public MyCglibAopProxy(MyAdvisedSupport config) {
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
