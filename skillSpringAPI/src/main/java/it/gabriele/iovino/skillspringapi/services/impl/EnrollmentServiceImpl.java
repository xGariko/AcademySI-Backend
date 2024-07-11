package it.gabriele.iovino.skillspringapi.services.impl;

import it.gabriele.iovino.skillspringapi.dao.EnrollmentDAO;
import it.gabriele.iovino.skillspringapi.models.Course;
import it.gabriele.iovino.skillspringapi.models.Enrollment;
import it.gabriele.iovino.skillspringapi.models.User;
import it.gabriele.iovino.skillspringapi.services.CourseService;
import it.gabriele.iovino.skillspringapi.services.EnrollmentService;
import it.gabriele.iovino.skillspringapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.convert.UriListHttpMessageConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentDAO enrollmentDAO;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;
    @Autowired
    private UriListHttpMessageConverter uriListHttpMessageConverter;

    @Override
    public Enrollment newEnrollment(int userId, int courseId) {
        User selectedUser = userService.getUserById(userId);
        Course selectedCourse = courseService.getCourseById(courseId);

        if(selectedCourse == null || selectedUser == null){
            return null;
        }

        List<Enrollment> actualUserEnrollments = this.getEnrollmentByUser(userId);
        for (Enrollment enrollment : actualUserEnrollments){
            if(enrollment.getCourse().getId() == courseId){
                return null;
            }
        }

        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setCourse(selectedCourse);
        newEnrollment.setUser(selectedUser);

        enrollmentDAO.save(newEnrollment);
        return newEnrollment;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentDAO.findAll();
    }

    @Override
    public List<Enrollment> getEnrollmentByUser(int userId) {
        return enrollmentDAO.findAllByUserId(userId).orElse(null);
    }

    @Override
    public List<Enrollment> getEnrollmentByCourse(int courseId) {
        return enrollmentDAO.findAllByCourseId(courseId).orElse(null);
    }

    @Override
    public void deleteCourseEnrollments(int courseId) {
        List<Enrollment> selectedEnrollments = this.getEnrollmentByCourse(courseId);
        for (Enrollment enrollment : selectedEnrollments){
            this.deleteEnrollmentById(enrollment.getId());
        }
    }

    @Override
    public void deleteUserEnrollments(int userId) {
        List<Enrollment> selectedEnrollments = this.getEnrollmentByUser(userId);
        for (Enrollment enrollment : selectedEnrollments){
            this.deleteEnrollmentById(enrollment.getId());
        }
    }

    @Override
    public void deleteEnrollmentById(int id) {
        enrollmentDAO.deleteById(id);
    }
}
