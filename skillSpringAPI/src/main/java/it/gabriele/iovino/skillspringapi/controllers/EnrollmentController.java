package it.gabriele.iovino.skillspringapi.controllers;


import it.gabriele.iovino.skillspringapi.dto.CourseDTO;
import it.gabriele.iovino.skillspringapi.dto.EnrollmentDTO;
import it.gabriele.iovino.skillspringapi.dto.UserDTO;
import it.gabriele.iovino.skillspringapi.models.Enrollment;
import it.gabriele.iovino.skillspringapi.services.EnrollmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/enrollment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    /* ------------   GET   ------------ */

    @GET
    public List<Enrollment> getAllEnrollments(){
        return enrollmentService.getAllEnrollments();
    }

    @GET
    @Path("/byUser/{userId}")
    public List<Enrollment> getEnrollmentsByUser(@PathParam("userId") int userId){
        return enrollmentService.getEnrollmentByUser(userId);
    }

    @GET
    @Path("/byCourse/{courseId}")
    public List<Enrollment> getEnrollmentsByCourse(@PathParam("courseId") int courseId){
        return enrollmentService.getEnrollmentByCourse(courseId);
    }

    /* ------------   POST   ------------ */

    @POST
    @Path("enroll/user/{userId}/toCourse/{courseId}")
    public Enrollment newEnrollment(@PathParam("userId") int userId, @PathParam("courseId") int courseId){
        return enrollmentService.newEnrollment(userId, courseId);
    }


    /* ------------   DELETE   ------------ */

    @DELETE
    @Path("/{id}")
    public void deleteEnrollmentById(@PathParam("id") int id){
        enrollmentService.deleteEnrollmentById(id);
    }

    @DELETE
    @Path("/byUser/{userId}")
    public void deleteUserEnrollments(@PathParam("userId") int userId){
        enrollmentService.deleteUserEnrollments(userId);
    }

    @DELETE
    @Path("/byCourse/{courseId}")
    public void deleteCourseEnrollments(@PathParam("courseId") int courseId){
        enrollmentService.deleteCourseEnrollments(courseId);
    }
}
