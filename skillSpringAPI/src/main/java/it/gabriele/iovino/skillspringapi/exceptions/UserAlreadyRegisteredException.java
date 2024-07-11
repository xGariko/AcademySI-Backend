package it.gabriele.iovino.skillspringapi.exceptions;

public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException(String e){
        super(e);
    }
}
