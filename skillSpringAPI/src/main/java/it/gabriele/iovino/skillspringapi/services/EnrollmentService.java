package it.gabriele.iovino.skillspringapi.services;

import it.gabriele.iovino.skillspringapi.dto.CourseDTO;
import it.gabriele.iovino.skillspringapi.dto.EnrollmentDTO;
import it.gabriele.iovino.skillspringapi.dto.UserDTO;
import it.gabriele.iovino.skillspringapi.models.Enrollment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {

    // GET
    List<Enrollment> getAllEnrollments();
    List<Enrollment> getEnrollmentByUser(int userId);
    List<Enrollment> getEnrollmentByCourse(int courseId);

    // POST
    Enrollment newEnrollment(int userId, int courseId);


    // DELETE
    void deleteEnrollmentById(int id);
    void deleteUserEnrollments(int userId);
    void deleteCourseEnrollments(int courseId);
}
