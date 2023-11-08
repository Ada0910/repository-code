package com.ada.software.design.principles.dip;

/**
 * <b><code></code></b>
 * <p/>
 * PythonCourse的课程实现类
 * <p/>
 *
 * @Date: 2022/8/31 22:58
 * @Author xwn
 * @Version 1.0.0.1
 */
public class PythonCourse implements ICourse {
    @Override
    public void study() {
        System.out.println("ada 在学习Python");
    }
}
