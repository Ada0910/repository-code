package com.ada.software.design.factory.abstrac;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 抽象工厂测试类
 * <p/>
 *
 * @Date: 2022/9/4 17:16
 * @Author xwn
 * @Version 1.0.0.1
 */
public class AbstarctFactoryTest {
    public static void main(String[] args) {
        ICourseFactory courseFactory = new JavaCourseFactory();
        courseFactory.createCourse();
        courseFactory.createVidio();
        courseFactory.createNote();

    }
}
