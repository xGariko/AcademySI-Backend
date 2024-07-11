package it.gabriele.iovino.skillspringapi.services.impl;

import it.gabriele.iovino.skillspringapi.dao.CategoryDAO;
import it.gabriele.iovino.skillspringapi.dao.CourseDAO;
import it.gabriele.iovino.skillspringapi.dto.CourseDTO;
import it.gabriele.iovino.skillspringapi.models.Category;
import it.gabriele.iovino.skillspringapi.models.Course;
import it.gabriele.iovino.skillspringapi.services.CategoryService;
import it.gabriele.iovino.skillspringapi.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    CategoryService categoryService;

    @Override
    public List<Course> getAllCourse() {
        return courseDAO.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseDAO.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourseByCategory(int categoryId) {
        Category selectedCategory = categoryService.getCategoryById(categoryId);
        if(selectedCategory == null){
            return null;
        }

        return courseDAO.getAllCourseByCategory(selectedCategory).orElse(null);
    }

    @Override
    public Course createCourse(CourseDTO courseDTO) {
        Course newCourse = new Course();
        newCourse.setDescription(courseDTO.getDescription());
        newCourse.setTitle(courseDTO.getTitle());
        newCourse.setImgUrl(courseDTO.getImgUrl());
        newCourse.setPrice(courseDTO.getPrice());
        newCourse.setCategory(null);
        newCourse.setRatingsCount(0);
        newCourse.setRatingsTotal(0);

        courseDAO.save(newCourse);

        return newCourse;
    }

    @Override
    public Course editCourse(CourseDTO courseDTO, int courseId) {
        Course newCourse = new Course();

        newCourse.setId(courseId);
        newCourse.setDescription(courseDTO.getDescription());
        newCourse.setTitle(courseDTO.getTitle());
        newCourse.setImgUrl(courseDTO.getImgUrl());
        newCourse.setPrice(courseDTO.getPrice());
        courseDAO.save(newCourse);

        return newCourse;
    }

    @Override
    public Course editCourseCategory(int courseId, int categoryId) {
        Course selectedCorse = this.getCourseById(courseId);
        Category selectedCategory = categoryService.getCategoryById(categoryId);

        if(selectedCategory == null || selectedCorse == null){
            return null;
        }

        selectedCorse.setCategory(selectedCategory);
        courseDAO.save(selectedCorse);
        return selectedCorse;
    }

    @Override
    public void deleteCourse(int id) {
        courseDAO.deleteById(id);
    }


    public static List<CourseDTO> convertListToDto(List<Course> courses) {
        return courses.stream().map(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setTitle(course.getTitle());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setPrice(course.getPrice());
            return courseDTO;
        }).collect(Collectors.toList());
    }

}
