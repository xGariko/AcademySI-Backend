package it.gabriele.iovino.skillspringapi.dto;

public class CourseCategoryDTO {
    private int courseId;
    private int categoryId;


    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}