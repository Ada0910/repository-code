package com.ada.software.design.factory.simple;

/**
 * <b><code></code></b>
 * <p/>
 * <p> JAVA课程
 * <p/>
 *
 * @Date: 2022/9/4 14:56
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaCourse implements ICourse {
    @Override
    public void study() {
        System.out.printf("学习JAVA课程！");
    }
}
