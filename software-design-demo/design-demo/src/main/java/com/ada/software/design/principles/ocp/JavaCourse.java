package com.ada.software.design.principles.ocp;

/**
 * <b><code></code></b>
 * <p/>
 * 实现课程接口类
 * <p/>
 *
 * @Date: 2022/8/31 22:17
 * @Author xwn
 * @Version 1.0.0.1
 */
public class JavaCourse implements ICourse {

    private Integer Id;
    private String name;
    private Double price;

    public JavaCourse(Integer id, String name, Double price) {
        this.Id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return null;
    }
}
