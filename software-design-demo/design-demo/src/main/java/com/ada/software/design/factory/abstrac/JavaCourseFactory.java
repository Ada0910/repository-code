package com.ada.software.design.factory.abstrac;

import com.ada.software.design.factory.simple.ICourse;
import com.ada.software.design.factory.simple.JavaCourse;

/**
 * <b><code></code></b>
 * <p/>
 * <p> JAVA的课程类，也就是JAVA的这个品牌
 * <p/>
 *
 * @Date: 2022/9/4 17:17
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaCourseFactory  implements  ICourseFactory{
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVidio() {
        return new JavaVideo();
    }
}
