package rikkei.academy.controller;

import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    IRoleService roleService = new RoleServiceIMPL();


    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUserName(signUpDTO.getUserName())) {
            return new ResponseMessenger("user_existed");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : strRole) {
            switch (role) {
                case "admin":
                    roles.add(roleService.findByRoleName(RoleName.ADMIN));
                    break;
                case "user":
                    roles.add(roleService.findByRoleName(RoleName.USER));
                    break;
                default:
                    return new ResponseMessenger("Invalid Role");
            }
        }

        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUserName(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                roles);
        userService.save(user);
        return new ResponseMessenger("success");
    }


    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUserName(), signInDTO.getPassword())) {
            return new ResponseMessenger("login_failure");
        }
        User login = userService.findByUserName(signInDTO.getUserName());
        userService.saveCurrentUser(login);
        return new ResponseMessenger("login_success");
    }

    public User getCurrentuser() {
        return userService.getCurrentUser();
    }
    public void logout(){
        userService.saveCurrentUser(null);
    }


    public User getUser(int idDelete) {
       return userService.findByID(idDelete);

    }

    public void deleteUser(int idDelete, User userDelete) {
//        if (userDelete.getRoles(RoleName) == RoleName.ADMIN){
//            System.err.println("Không thể xóa tài khoản ADMIN");
//            return;
//        }
        userService.delete(idDelete,userDelete);
    }
}
