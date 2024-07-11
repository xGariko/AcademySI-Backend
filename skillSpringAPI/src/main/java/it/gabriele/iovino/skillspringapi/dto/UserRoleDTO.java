package it.gabriele.iovino.skillspringapi.dto;

public class UserRoleDTO {
    private int userId;
    private Boolean role;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
}
