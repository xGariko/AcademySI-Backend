package it.gabriele.iovino.skillspringapi.services;

import it.gabriele.iovino.skillspringapi.dto.CourseDTO;
import it.gabriele.iovino.skillspringapi.models.Category;
import it.gabriele.iovino.skillspringapi.models.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    // GET
    List<Course> getAllCourse();
    Course getCourseById(int id);
    List<Course> getAllCourseByCategory(int categoryId);

    // POST
    Course createCourse(CourseDTO courseDTO);

    // PUT
    Course editCourse(CourseDTO courseDTO, int courseId);
    Course editCourseCategory(int courseId, int categoryId);


    // DELETE
    void deleteCourse(int id);
}
