package com.ada.software.design.factory.simple;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 简单工厂模式（通过这个来建造出相关的JAVA课程类）
 * <p/>
 *
 * @Date: 2022/9/4 14:58
 * @Author xwn
 * @Version 1.0.0.1
 */
public class CourseFactory {
    public ICourse createCourse(Class clazz) {
        try {
            if (null != clazz) {
                ICourse course = (ICourse) clazz.newInstance();
                return course;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
