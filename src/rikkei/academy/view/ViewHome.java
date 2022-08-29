package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.KhoaHocController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewHome extends ViewMainMenu {
    KhoaHocController khoaHocController = new KhoaHocController();
    List<KhoaHoc> khoaHocControllerList = khoaHocController.getListKhoaHoc();
    UserController userController = new UserController();
    User currentUser = userController.getCurrentuser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();

    public ViewHome() {

        switch (roleName) {
            case ADMIN:
                menuAdmin();
                break;
            case USER:
                menuUser();
                return;
            case COACH:
                menuCoach();
                break;
        }
    }

    private void menuCoach() {
        System.out.println(" hello COACH: " + currentUser.getUserName());
        System.out.println("1:Logout");
        System.out.println("2:Quản lý khóa học");
        System.out.println("3:Quản lý bài tập");
        System.out.println("4: Cá Nhân");
        System.out.println("5:Quản lý user");
        System.out.println("6:Quản lý lớp học");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            menuAdmin();
        }
        switch (choice) {
            case 1:
                userController.logout();
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewKhoaHocAdmin().khoaHocAdmin();
                break;
            case 3:
                new ViewBaiTap().baiTapADmin();
                break;
            case 4:
                fromThongTinCaNhan();
                break;
            case 5:
                new ViewUserToCoach().fromUserManager();
                break;
            case 6:
                new ClassroomManage().classroomMenu();
                break;
            default:
                System.err.println("Invlaid choice!");
        }
        menuCoach();
    }

    public void menuUser() {
        System.out.println(" hello USER: " + currentUser.getUserName());
        System.out.println("1: Logout");
        System.out.println("2: Khóa Học ");
        System.out.println("3: Bảng Điều khiển ");
        System.out.println("4: Cá Nhân");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            menuUser();
        }
        switch (choice) {
            case 1:
                userController.logout();
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewKhoahocUser().menuKhoaHoc();
                break;
            case 3:
                new ViewbangDieuKhien().fromBangDieuKhien();
                break;
            case 4:
                fromThongTinCaNhan();
                break;
            default:
                System.out.println("Invalid choice");

        }
        menuUser();
    }

    private void fromThongTinCaNhan() {
        System.out.println("thông tin cá nhân");
        User userCaNhan = userController.getCurrentuser();
        System.out.println(userCaNhan);
        System.out.println("Enter 1 to change password");
        System.out.println("Enter 0 to back menu");

        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            fromThongTinCaNhan();
        }
        switch (choice) {
            case 0:
                return;
            case 1:
                String password;
                while (true) {
                    System.out.println("Enter new password");
                    password = Config.scanner().nextLine();
                    if (password.matches("[a-zA-Z\\d]{1,10}")) {
                        break;
                    } else {
                        System.out.println("Invalid password try again");
                    }
                }
                userController.changePass(userCaNhan.getId(), password);
                System.out.println("change success");
                break;
            default:
                System.err.println("Invalid choice!");
        }
    }

    public void menuAdmin() {
        System.out.println(" hello ADMIN: " + currentUser.getUserName());
        System.out.println("1:Logout");
        System.out.println("2:Quản lý user");
        System.out.println("3:Quản lý khóa học");
        System.out.println("4:Quản lý bài tập");
        System.out.println("5:Thông tin cá nhân");
        System.out.println("6:Quản lý lớp học");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            menuAdmin();
        }
        switch (choice) {
            case 1:
                userController.logout();
                new ViewMainMenu().menu();
                return;
            case 2:
                new ViewUser().fromUserManager();
                break;
            case 3:
                new ViewKhoaHocAdmin().khoaHocAdmin();
                break;
            case 4:
                new ViewBaiTap().baiTapADmin();
                break;
            case 5:
                fromThongTinCaNhan();
                break;
            case 6:
               new ClassroomManage().classroomMenu();
                break;
            default:
                System.err.println("Invlaid choice!");
        }
        menuAdmin();
    }


}
