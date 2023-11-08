package com.ada.software.design.principles.dip;

/**
 * <b><code></code></b>
 * <p/>
 * 学习测试类
 * <p/>
 *
 * @Date: 2022/8/31 23:01
 * @Author xwn
 * @Version 1.0.0.1
 */
public class StudyTest {
    public static void main(String[] args) {
        People people = new People();
        people.study(new JavaCourse());
        people.study(new PythonCourse());
    }
}
