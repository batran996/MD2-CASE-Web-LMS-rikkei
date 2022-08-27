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
        }
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
        }catch (Exception e){
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
            default:
                System.out.println("Invalid choice");

        }
        menuUser();
    }

    public void menuAdmin() {
        System.out.println(" hello ADMIN: " + currentUser.getUserName());
        System.out.println("1:Logout");
        System.out.println("2:Quản lý user");
        System.out.println("3:Quản lý khóa học");
        System.out.println("4:Quản lý bài tập");


        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        }catch (Exception e){
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
            default:
                System.err.println("Invlaid choice!");
        }
        menuAdmin();
    }


}
