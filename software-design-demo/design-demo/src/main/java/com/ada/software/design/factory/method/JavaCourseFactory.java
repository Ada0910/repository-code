package com.ada.software.design.factory.method;

import com.ada.software.design.factory.simple.ICourse;
import com.ada.software.design.factory.simple.JavaCourse;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 创建JAVA的工厂方法
 * <p/>
 *
 * @Date: 2022/9/4 16:41
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaCourseFactory implements ICourseFactory {

    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
