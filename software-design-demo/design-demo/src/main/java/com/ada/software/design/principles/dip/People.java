package com.ada.software.design.principles.dip;

/**
 * <b><code></code></b>
 * <p/>
 * 依赖倒置原则：高层模块不应该依赖底层模块，二者都应该依赖其抽象。抽象不应该依赖细节；细节应该依赖抽象
 * <p/>
 *
 * @Date: 2022/8/31 22:59
 * @Author xwn
 * @Version 1.0.0.1
 */
public class People {

    /**
     * 依赖注入方式一：普通注入
     */
    public void study(ICourse course) {
        course.study();
    }

    /**
     * 依赖注入方式二：构造器注入
     */

//    private ICourse course;
//
//    public People(ICourse course) {
//        this.course = course;
//    }
//
//    public void study() {
//        course.study();
//    }


    /**
     * 依赖注入方式三：setter方法注入
     */
//    private ICourse course;
//
//    public People(ICourse course) {
//        this.course = course;
//    }
//
//    public void study(){
//        course.study();;
//    }


}
