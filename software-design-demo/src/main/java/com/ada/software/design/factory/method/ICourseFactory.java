package com.ada.software.design.factory.method;

import com.ada.software.design.factory.simple.ICourse;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 定义一个课程的接口（规范）
 * <p/>
 *
 * @Date: 2022/9/4 16:40
 * @Author xwn
 * @Version 1.0.0.1
 */
public interface ICourseFactory {
    ICourse create();
}
