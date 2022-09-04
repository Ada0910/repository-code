package com.ada.software.design.factory.simple;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 测试类
 * <p/>
 *
 * @Date: 2022/9/4 14:57
 * @Author xwn
 * @Version 1.0.0.1
 */
public class CourseTest {
    public static void main(String[] args) {
        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.createCourse(JavaCourse.class);
        course.study();

    }
}
