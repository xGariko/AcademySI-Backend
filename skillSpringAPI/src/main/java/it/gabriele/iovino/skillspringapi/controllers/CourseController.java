package it.gabriele.iovino.skillspringapi.controllers;

import it.gabriele.iovino.skillspringapi.dto.CourseDTO;
import it.gabriele.iovino.skillspringapi.models.Course;
import it.gabriele.iovino.skillspringapi.security.JWTTokenNeeded;
import it.gabriele.iovino.skillspringapi.security.Secured;
import it.gabriele.iovino.skillspringapi.services.CourseService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Path("/course")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseController {

    @Autowired
    private CourseService courseService;

    /* ------------   GET   ------------ */

    @GET
    public List<Course> getAllCourses(){
        return courseService.getAllCourse();
    }

    @GET
    @Path("/{id}")
    public Course getCourseById(@PathParam("id") int id){
        return courseService.getCourseById(id);
    }

    @GET
    @Path("/byCategory/{categoryId}")
    public List<Course> getCourseByCategory(@PathParam("categoryId") int categoryId){
        return courseService.getAllCourseByCategory(categoryId);
    }


    /* ------------   POST   ------------ */

    @POST
    @Secured(role=1)
    @JWTTokenNeeded
    public Course createCourse(@RequestBody CourseDTO courseDTO){
        return courseService.createCourse(courseDTO);
    }

    /* ------------   PUT   ------------ */

    @PUT
    @Path("/{id}")
    public Course editCourse(@PathParam("id") int courseId, @RequestBody CourseDTO courseDTO){
        return courseService.editCourse(courseDTO, courseId);
    }

    @PUT
    @Path("/{courseId}/setCategory/{categoryId}")
    public Course editCourseCategory(@PathParam("courseId") int courseId, @PathParam("categoryId") int categoryId){
        return courseService.editCourseCategory(courseId, categoryId);
    }

    /* ------------   DELETE   ------------ */

    @DELETE
    @Path("/{id}")
    public void deleteCourseById(@PathParam("id") int id){
        courseService.deleteCourse(id);
    }



}
