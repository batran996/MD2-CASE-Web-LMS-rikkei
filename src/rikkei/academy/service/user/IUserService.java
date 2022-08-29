package rikkei.academy.service.user;

import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.IGernericService;

public interface IUserService extends IGernericService<User> {
boolean existsByUserName(String userName);
boolean existsByEmail(String email);


    boolean checkLogin(String userName, String password);
    User getCurrentUser();
    void saveCurrentUser(User user);
    User findByUserName(String username);

    void changeUser(int idChange, User userChange, RoleName user);

    void changeRole(int idChange, Role role);

    void changPassword(int id, String password);

    void changeStatus(int id);

}
