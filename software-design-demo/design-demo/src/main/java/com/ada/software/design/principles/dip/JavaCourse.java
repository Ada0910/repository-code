package com.ada.software.design.principles.dip;

/**
 * <b><code></code></b>
 * <p/>
 * <p> Java课程类
 * <p/>
 *
 * @Date: 2022/8/31 22:57
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("ada 在学习 Java 课程");
    }
}
