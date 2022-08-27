package rikkei.academy.dto.request;

import rikkei.academy.model.Role;

import java.util.Set;

public class SignUpDTO {
    private int id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private Set<String> roles ;

    public SignUpDTO() {
    }

    public SignUpDTO(int id, String name, String userName, String email, String password, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
