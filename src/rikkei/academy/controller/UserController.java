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

import java.util.ArrayList;
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
        if (login.isStatus()) {
            return new ResponseMessenger("blocked");
        }
        userService.saveCurrentUser(login);
        return new ResponseMessenger("login_success");
    }

    public User getCurrentuser() {
        return userService.getCurrentUser();
    }

    public void logout() {
        userService.saveCurrentUser(null);
    }


    public User getUser(int id) {
        return userService.findByID(id);

    }

    public void deleteUser(int idDelete, User userDelete) {
        userService.delete(idDelete, userDelete);
    }

    public ResponseMessenger changeRole(int idChange, String roleName) {
        if (userService.findByID(idChange) == null || idChange == 0) {
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equalsIgnoreCase("user") && !roleName.equalsIgnoreCase("coach")) {
            return new ResponseMessenger("Invalid_role");
        }
        Role role = roleName.equalsIgnoreCase("user") ? roleService.findByRoleName(RoleName.USER) : roleService.findByRoleName(RoleName.COACH);
        userService.changeRole(idChange, role);
        return new ResponseMessenger("success!");
    }

    public void changePass(int id, String password) {
        userService.changPassword(id, password);
    }

    public ResponseMessenger blockUser(int id) {
        User blockUser = userService.findByID(id);
        if (blockUser == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        Role role = new ArrayList<>(getCurrentuser().getRoles()).get(0);
        Role roleBlock = new ArrayList<>(blockUser.getRoles()).get(0);
        if (role.getRoleName() == RoleName.COACH && (roleBlock.getRoleName() == RoleName.COACH || roleBlock.getRoleName() == RoleName.ADMIN)) {
            return new ResponseMessenger("jurisdiction");
        }
        userService.changeStatus(id);
        boolean check = blockUser.isStatus();
        if (check) {
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }
    }
}
