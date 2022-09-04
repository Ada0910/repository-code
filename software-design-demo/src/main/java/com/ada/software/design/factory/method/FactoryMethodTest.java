package com.ada.software.design.factory.method;

import com.ada.software.design.factory.simple.ICourse;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 工厂方法测试类
 * <p/>
 *
 * @Date: 2022/9/4 16:46
 * @Author xwn
 * @Version 1.0.0.1
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory courseFactory = new JavaCourseFactory();
        ICourse course = courseFactory.create();
        course.study();
    }
}
