package com.ada.software.design.factory.abstrac;

import com.ada.software.design.factory.simple.ICourse;

/**
 * <b><code></code></b>
 * <p/>
 * <p> 一个品牌的工厂（比如，格力工厂可以生产洗衣机，空调、电冰箱）
 * <p/>
 *
 * @Date: 2022/9/4 17:13
 * @Author xwn
 * @Version 1.0.0.1
 */
public interface ICourseFactory {
    ICourse createCourse();

    INote createNote();

    IVideo createVidio();
}
