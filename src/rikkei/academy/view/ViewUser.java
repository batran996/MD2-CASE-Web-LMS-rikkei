package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ViewUser {
    UserController userController = new UserController();

    public void fromUserManager() {
        System.out.println("Menu User");
        System.out.println("0:Exit");
        System.out.println("1:list user");
        System.out.println("2:delete user");
        System.out.println("3:search user");
        System.out.println("4:Chỉ định quyền truy cập");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            fromUserManager();
        }
        switch (choice) {
            case 0:
                return;
            case 1:
                fromShowUser();
                break;
            case 2:
                fromDeleteUser();
                break;
            case 3:
                fromSearchUser();
                break;
            case 4:
                fromQuyenTruyCap();
                break;
            default:
                System.err.println("Invalid choice!");
        }
        fromUserManager();
    }

    private void fromQuyenTruyCap() {
        fromShowUser();
        System.out.println("Nhập ID muốn thay đổi quyền truy cập");
        int idChange = -1;
        try {
            idChange = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromQuyenTruyCap();
        }
        boolean check = false;
        for (int i = 0; i < userController.getUserList().size(); i++) {
            if (userController.getUserList().get(i).getId() == idChange) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        User userChange = new UserController().getUser(idChange);
        if (userChange.getUserName().equalsIgnoreCase("ad")) {
            System.err.println("Không thể thay đổi tài khoản ADMIN");
        } else {
            System.out.println("Nhập quyền mới");
            String newAccessRights = Config.scanner().nextLine();
            userController.changeRole(idChange,newAccessRights);
            fromShowUser();
        }
    }

    private void fromSearchUser() {
        System.out.println("Nhập tên user để tìm kiếm ");
        String nameSearch = Config.scanner().nextLine();
        for (User user : userController.getUserList()) {
            if (user.getUserName().toLowerCase(Locale.ROOT).contains(nameSearch.toLowerCase())) {
                System.out.println(user);
            }
        }
    }

    private void fromShowUser() {
        System.out.printf("%-15s %-15s %s %n", "ID", "Username", "Role");
        for (User user : userController.getUserList()) {
            System.out.printf("%-15s %-15s %s %n", user.getId(), user.getUserName(),
                    new ArrayList<>(user.getRoles()).get(0).getRoleName());
        }
    }

    private void fromDeleteUser() {
        fromShowUser();
        System.out.println("Nhập Id user cần xóa");
        int idDelete = -1;
        try {
            idDelete = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromDeleteUser();
        }
        boolean check = false;
        for (int i = 0; i < userController.getUserList().size(); i++) {
            if (userController.getUserList().get(i).getId() == idDelete) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        User userDelete = new UserController().getUser(idDelete);
        if (userDelete.getUserName().equalsIgnoreCase("ad")) {
            System.err.println("Không thể xóa tài khoản ADMIN");
        } else {
            userController.deleteUser(idDelete, userDelete);

        }
        fromShowUser();
    }
}
