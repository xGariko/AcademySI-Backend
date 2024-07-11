package it.gabriele.iovino.skillspringapi.dto;

public class EnrollmentDTO {
    private int userId;
    private int courseId;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
