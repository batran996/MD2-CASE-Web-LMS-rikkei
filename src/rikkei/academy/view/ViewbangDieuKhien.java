package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;

import java.util.ArrayList;

public class ViewbangDieuKhien {
    UserController userController = new UserController();

    public void fromBangDieuKhien() {
        System.out.println("0: back ");
        System.out.println("1: thông tin cá nhân");
        System.out.println("2: danh sách bài tập");
        System.out.println("3: lộ trình của tôi");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        }catch (Exception e){
            fromBangDieuKhien();
        }
        switch (choice) {
            case 0:
                return;
            case 1:
                fromThongTinCaNhan();
                break;
            case 2:
                fromListBaiTap();
                break;
            case 3:
                fromMyLoTrinh();
                break;
            default:
                System.err.println("Invalid choice");
        }
    }

    private void fromMyLoTrinh() {
        System.out.println("lộ trình");
    }

    private void fromListBaiTap() {
        System.out.println("list bài tập");
    }

    private void fromThongTinCaNhan() {
        User userLogin = userController.getCurrentuser();
        System.out.println("thông tin tài khoản");
        System.out.printf("%-15s %-15s %s %n", "ID", "Username", "Role");
        System.out.printf("%-15s %-15s %s %n", userLogin.getId(), userLogin.getUserName(),
                new ArrayList<>(userLogin.getRoles()).get(0).getRoleName());
    }


}
