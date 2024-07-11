package it.gabriele.iovino.skillspringapi.services;


import it.gabriele.iovino.skillspringapi.dto.*;
import it.gabriele.iovino.skillspringapi.exceptions.UserAlreadyRegisteredException;
import it.gabriele.iovino.skillspringapi.models.User;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    // GET
    List<User> getAllUser();
    User getUserById(int id);
    User getUserByEmail(String email);

    // POST
    User register(UserRegisterDTO userRegisterDTO) throws UserAlreadyRegisteredException;
    Response login(UserLoginDTO userLoginDTO);

    //PUT
    UserDTO editUser(UserDTO userDTO, int id);
    Response setRole(int id, int newRole);

    // DELETE
    void deleteUser(int id);
}
