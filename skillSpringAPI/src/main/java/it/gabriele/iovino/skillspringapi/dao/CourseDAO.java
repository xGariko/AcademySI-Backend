package it.gabriele.iovino.skillspringapi.dao;

import it.gabriele.iovino.skillspringapi.models.Category;
import it.gabriele.iovino.skillspringapi.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseDAO extends JpaRepository<Course, Integer> {
    Optional<List<Course>> getAllCourseByCategory(Category category);
}