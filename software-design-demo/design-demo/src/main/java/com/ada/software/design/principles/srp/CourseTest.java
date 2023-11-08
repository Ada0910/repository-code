package com.ada.software.design.principles.srp;

/**
 * <b><code></code></b>
 * <p/>
 * <p>
 * <p/>
 *
 * @Date: 2022/8/31 23:30
 * @Author xwn
 * @Version 1.0.0.1
 */
public class CourseTest {
    public static void main(String[] args) {
        LiveCourse liveCourse = new LiveCourse();
        liveCourse.study("JAVA");

        ReplayCourse replayCourse = new ReplayCourse();
        replayCourse.study("JAVA");



    }
}
