package it.gabriele.iovino.skillspringapi.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.gabriele.iovino.skillspringapi.dao.UserDAO;
import it.gabriele.iovino.skillspringapi.dto.*;
import it.gabriele.iovino.skillspringapi.exceptions.UserAlreadyRegisteredException;
import it.gabriele.iovino.skillspringapi.models.User;
import it.gabriele.iovino.skillspringapi.services.UserService;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Response setRole(int id, int newRole) {
        User selectedUser = getUserById(id);
        if(selectedUser == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        selectedUser.setRole(newRole!=0);
        userDAO.save(selectedUser);

        return Response.ok().build();
    }

    @Override
    public UserDTO editUser(UserDTO userDTO, int id) {
        User selectedUser = this.getUserById(id);

        selectedUser.setEmail(userDTO.getEmail());
        selectedUser.setName(userDTO.getName());
        selectedUser.setLastName(userDTO.getLastName());

        userDAO.save(selectedUser);

        return convertToDTO(selectedUser);
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userDAO.findById(id);
        return user.orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userDAO.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) throws UserAlreadyRegisteredException {
        User newUser = new User();
        newUser.setName(userRegisterDTO.getName());
        newUser.setLastName(userRegisterDTO.getLastName());
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setPassword(hashPassword(userRegisterDTO.getPassword()));
        newUser.setRole(Boolean.FALSE);

        userDAO.save(newUser);

        return newUser;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response login(UserLoginDTO userLoginDTO) {
        User user = getUserByEmail(userLoginDTO.getEmail());
        if (user != null && user.getPassword().equals(hashPassword(userLoginDTO.getPassword()))){

            UserLoginResponseDTO response = issueToken(user);
            return Response.ok().entity(response).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }



    @Override
    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }




    public static List<UserDTO> convertListToDto(List<User> users) {
        return users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            return userDTO;
        }).collect(Collectors.toList());
    }

    public static UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }



    private UserLoginResponseDTO issueToken(User userInfo) {
        byte[] secret = "fgvajyukkhjgbvfsdkfgvbshjfgvsbsdaetgvbvsertdgerffvh".getBytes();
        Key key = Keys.hmacShaKeyFor(secret);


        Date creation = new Date();
        Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
        String.valueOf(userInfo.getRole());
        String tokenJwts = Jwts.builder()
                .claim("name", userInfo.getName())
                .claim("lastName", userInfo.getLastName())
                .claim("role", String.valueOf(userInfo.getRole()))
                .claim("email", userInfo.getEmail())
                .setIssuer("http://localhost:8080")
                .setIssuedAt(creation)
                .setExpiration(end)
                .signWith(key)
                .compact();

        UserLoginResponseDTO token = new UserLoginResponseDTO();

        token.setToken(tokenJwts);
        token.setTokenCreationTime(creation);
        token.setTtl(end);

        return token;
    }


}
