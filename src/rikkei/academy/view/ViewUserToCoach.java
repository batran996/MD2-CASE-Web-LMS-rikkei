package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.Locale;

public class ViewUserToCoach {
    UserController userController = new UserController();

    public void fromUserManager() {
        System.out.println("Menu User");
        System.out.println("0:Exit");
        System.out.println("1:List user");
        System.out.println("2:Block user");
        System.out.println("3:Search user");

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
                fromBlockUser();
                break;
            case 3:
                fromSearchUser();
                break;
            default:
                System.err.println("Invalid choice!");
        }
        fromUserManager();
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
        System.out.printf("%-15s %-15s %s %-15s %n", "ID", "Username", "Role", "status");
        for (User user : userController.getUserList()) {
            System.out.printf("%-15s %-15s %s %-15s %n", user.getId(), user.getUserName(),
                    new ArrayList<>(user.getRoles()).get(0).getRoleName(), user.isStatus());
        }
    }

    private void fromBlockUser() {
        fromShowUser();
        System.out.println("Enter id user to block");
        int id = Integer.parseInt(Config.scanner().nextLine());
        ResponseMessenger messenger = userController.blockUser(id);
        switch (messenger.getMessage()) {
            case "not_found":
                System.out.println("ID not found");
                break;
            case "blocked":
                System.out.println("Bạn đã block user ID:" + id);
                break;
            case "unbocked":
                System.out.println("bạn đã gỡ block user ID: " + id);
                break;
            case "jurisdiction":
                System.out.println("Bạn không đủ quyền hạn để block ADMIN hoặc COACH");
                break;
        }
    }
}
