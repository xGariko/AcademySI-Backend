package it.gabriele.iovino.skillspringapi.controllers;


import it.gabriele.iovino.skillspringapi.dto.*;
import it.gabriele.iovino.skillspringapi.exceptions.UserAlreadyRegisteredException;
import it.gabriele.iovino.skillspringapi.models.User;
import it.gabriele.iovino.skillspringapi.security.JWTTokenNeeded;
import it.gabriele.iovino.skillspringapi.security.Secured;
import it.gabriele.iovino.skillspringapi.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    /* ------------   GET   ------------ */

    @Autowired
    private UserService userService;

    @GET
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") int id){
        return userService.getUserById(id);
    }

    @GET
    @Path("/email/{email}")
    public User getUserByEmail(@PathParam("email") String email){
        return userService.getUserByEmail(email);
    }

    /* ------------   POST   ------------ */

    @POST
    @Path("/register")
    public Response registerUser(@RequestBody UserRegisterDTO userRegisterDTO) throws UserAlreadyRegisteredException {
        System.out.println("sdkjgvhnb");
        User checkUser = userService.getUserByEmail(userRegisterDTO.getEmail());
        if(checkUser != null){
            return Response.status(Response.Status.CONFLICT).entity("Email already in use").build();
        }
        User justRegsiteredUser = userService.register(userRegisterDTO);
        return Response.ok(justRegsiteredUser).build();
    }


    @POST
    @Path("/login")
    public Response loginUser(@RequestBody UserLoginDTO loginRequest) throws ServletException {
        User user = userService.getUserByEmail(loginRequest.getEmail());
        return userService.login(loginRequest);
    }


    /* ------------   PUT   ------------ */

    @PUT
    @Secured(role = 1)
    @JWTTokenNeeded
    @Path("{id}/setRole/{role}")
    public Response setRole(@PathParam("id") int id, @PathParam("role") int role){
        if(role != 1 && role != 0){
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid role").build();
        }
        return userService.setRole(id,role);
    }

    @PUT
    @Secured(role = 0)
    @JWTTokenNeeded
    @Path("/{id}")
    public UserDTO editUser(@RequestBody UserDTO userDTO, @PathParam("id") int id){
        return userService.editUser(userDTO, id);
    }

    /* ------------   DELETE   ------------ */

    @Secured(role = 1)
    @JWTTokenNeeded
    @DELETE
    @Path("/{id}")
    public void deleteUserById(@PathParam("id") int id){
        userService.deleteUser(id);
    }





}