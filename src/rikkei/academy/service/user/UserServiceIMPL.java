package rikkei.academy.service.user;

import rikkei.academy.config.Config;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceIMPL implements IUserService {
    static String PATH_USER = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\user.txt";
    static String PATH_USER_LOGIN = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\user_login.txt";

    static Config<List<User>> config = new Config<>();
    private static List<User> userList = config.read(PATH_USER);

    static {
        if (userList == null) {
            userList = new ArrayList<>();
        }
    }


    @Override
    public List<User> findAll() {
        config.write(PATH_USER, userList);
        return userList;
    }

    @Override
    public void save(User user) {
        userList.add(user);
        config.write(PATH_USER, userList);

    }

    @Override
    public User findByID(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void eDit(int id, User user) {


    }

    @Override
    public void delete(int id, User user) {
        User userDelete = findByID(id);
        userList.remove(userDelete);
        config.write(PATH_USER, userList);
    }

    @Override
    public boolean existsByUserName(String userName) {
        for (User user : userList) {
            if (user.getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        User user = new Config<User>().read(PATH_USER_LOGIN);
        if (user == null) {
            return null;
        }
        return findByUserName(user.getUserName());
    }

    @Override

    public void saveCurrentUser(User user) {
        new Config<User>().write(PATH_USER_LOGIN, user);

    }

    @Override
    public User findByUserName(String username) {
        for (User user : userList) {
            if (user.getUserName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void changeUser(int id, User userChange, RoleName user) {
        User userChanger = findByID(id);

    }

    @Override
    public void changeRole(int idChange, Role role) {
        User userChange = findByID(idChange);
        Set<Role>setRole = new HashSet<>();
        setRole.add(role);
        userChange.setRoles(setRole);
        config.write(PATH_USER,userList);
    }

    @Override
    public void changPassword(int id, String password) {
        User userChangePass = findByID(id);
        userChangePass.setPassword(password);
        config.write(PATH_USER,userList);

    }

    @Override
    public void changeStatus(int id) {
        User user = findByID(id);
        user.setStatus(!user.isStatus());
        config.write(PATH_USER,userList);
    }
}
