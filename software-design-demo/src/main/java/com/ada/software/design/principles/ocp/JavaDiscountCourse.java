package com.ada.software.design.principles.ocp;

/**
 * <b><code></code></b>
 * <p/>
 * Java课程优惠类
 * <p>
 * <p/>
 *
 * @Date: 2022/8/31 22:22
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaDiscountCourse extends JavaCourse {
    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getOriginPrice() {
        return super.getPrice();
    }

    public Double getPrice() {
        return super.getPrice() * 0.61;
    }
}
