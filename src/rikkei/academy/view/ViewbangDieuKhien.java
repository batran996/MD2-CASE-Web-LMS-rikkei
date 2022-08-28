package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.BaiTapController;
import rikkei.academy.controller.KhoaHocController;
import rikkei.academy.controller.KhoaHocGanDayController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.*;

import java.util.ArrayList;
import java.util.List;

public class ViewbangDieuKhien {
    UserController userController = new UserController();
    BaiTapController baiTapController = new BaiTapController();
    List<BaiTap> listBaiTapController = baiTapController.getListBaiTap();
    KhoaHocController khoaHocController = new KhoaHocController();
    List<KhoaHoc> listKhoaHocController = khoaHocController.getListKhoaHoc();
    KhoaHocGanDayController khoaHocGanDayController = new KhoaHocGanDayController();

    public void fromBangDieuKhien() {
        System.out.println("0: back ");
        System.out.println("1: thông tin cá nhân");
        System.out.println("2: danh sách bài tập");
        System.out.println("3: lộ trình của tôi");
        System.out.println("4: Khóa học gần đây");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
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
            case 4:
                fromKhoaHocGanDay();
                break;
            default:
                System.err.println("Invalid choice");
        }
    }

    private void fromKhoaHocGanDay() {
        System.out.println("Khóa học gần đây");
        khoaHocGanDayController.getKhoaHocGanDay();
//        System.out.println(khoaHocGanDayController.getKhoaHocGanDay().getKhoaHocQueue());
        for (KhoaHoc khoaHoc:khoaHocGanDayController.getKhoaHocGanDay().getKhoaHocQueue()) {
            System.out.println(khoaHoc);
        }
    }

    private void fromMyLoTrinh() {
        System.out.println("lộ trình java:");
        for (int i = 0; i < listKhoaHocController.size(); i++) {
            if (listKhoaHocController.get(i).getLoTrinh() == LoTrinh.JAVA){
                System.out.println(listKhoaHocController.get(i));
            }
        }
        System.out.println("lộ trình java script");
        for (int i = 0; i < listKhoaHocController.size(); i++) {
            if (listKhoaHocController.get(i).getLoTrinh() == LoTrinh.JAVASCRIPT){
                System.out.println(listKhoaHocController.get(i));
            }
        }
    }

    private void fromListBaiTap() {
        System.out.println("list bài tập");
        baiTapController.getListBaiTap();
        for (BaiTap baiTap : listBaiTapController) {
            System.out.println(baiTap);
        }
    }

    private void fromThongTinCaNhan() {
        User userLogin = userController.getCurrentuser();
        System.out.println("thông tin tài khoản");
        System.out.printf("%-15s %-15s %s %n", "ID", "Username", "Role");
        System.out.printf("%-15s %-15s %s %n", userLogin.getId(), userLogin.getUserName(),
                new ArrayList<>(userLogin.getRoles()).get(0).getRoleName());
    }


}
