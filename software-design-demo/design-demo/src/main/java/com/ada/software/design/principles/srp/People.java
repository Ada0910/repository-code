package com.ada.software.design.principles.srp;

/**
 * <b><code></code></b>
 * <p/>
 * <p>
 * <p/>
 *
 * @Date: 2022/8/31 23:40
 * @Author xwn
 * @Version 1.0.0.1
 */
public class People {

    private String courseName;

    private byte[] courseVideo;

    private String studyCourse;

    private String refundCourse;

    public void getCourseInfo(ICourseInfo courseInfo) {
        this.courseName = courseInfo.getCourseName();
        this.courseVideo = courseInfo.getCourseVideo();
    }

    public void getCourse(ICourseManager iCourseManager) {
        this.studyCourse = iCourseManager.studyCourse();
        this.refundCourse = iCourseManager.refundCourse();
    }
}
