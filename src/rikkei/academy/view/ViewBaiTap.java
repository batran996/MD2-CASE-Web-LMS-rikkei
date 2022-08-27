package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.BaiTapController;
import rikkei.academy.model.BaiTap;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.LoTrinh;

import java.util.List;
import java.util.Locale;

public class ViewBaiTap {
    BaiTapController baiTapController = new BaiTapController();
    List<BaiTap> controllerListBaiTap = baiTapController.getListBaiTap();

    public void baiTapADmin(){
        System.out.println("0: back");
        System.out.println("1:Thêm bài tập mới");
        System.out.println("2:Xóa bài tập ");
        System.out.println("3:Danh sách bài tập hiện có");
        System.out.println("4:Tìm kiếm bài tập");

        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        }catch (Exception e){
            baiTapADmin();
        }
        switch (choice){
            case 0:
                return;
            case 1:
                fromAddBaitap();
                break;
            case 2:
                fromDeletebaiTap();
                break;
            case 3:
                fromListBaiTap();
                break;
            case 4:
                fromSearchBaiTap();
                break;
            default:
                System.out.println("Invalid choice! please try again");
        }
        baiTapADmin();
    }

    private void fromSearchBaiTap() {
        System.out.println("Nhập tên bai tập cần tìm kiếm");
        String nameSearch = Config.scanner().nextLine();
        for (BaiTap baiTap : controllerListBaiTap) {
            if (baiTap.getName().toLowerCase(Locale.ROOT).contains(nameSearch.toLowerCase())) {
                System.out.println(baiTap);
            }
        }
    }

    private void fromDeletebaiTap() {
        fromListBaiTap();
        System.out.println("nhập id bài tập bạn muốn xóa");
        int iDelete = -1;
        try {
            iDelete = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromDeletebaiTap();
        }
        boolean check = false;
        for (int i = 0; i < controllerListBaiTap.size(); i++) {
            if (controllerListBaiTap.get(i).getId() == iDelete) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        BaiTap baiTapDelete = baiTapController.getBaiTap(iDelete);
        baiTapController.deleteBaitap(iDelete, baiTapDelete);
        fromListBaiTap();
    }

    private void fromAddBaitap() {
        System.out.println("Nhập tên bài tập mới");
        //// check ky tu dac biet cua name;
        String name = "";
//        name = checkName(name);
        name = Config.scanner().nextLine();
        if (!name.matches("[a-zA-Z \\d]{1,100}")) {
            System.out.println("Tên sai định dạng");
            return;
        }
        for (BaiTap baiTap : controllerListBaiTap) {
            if (name.equalsIgnoreCase(baiTap.getName())) {
                System.out.println("Bài tập đã tồn tại ");
                return;
            }
        }

        int lastId;
        if (controllerListBaiTap.isEmpty()) {
            lastId = 1;
        } else {
            lastId = controllerListBaiTap.get(controllerListBaiTap.size() - 1).getId();
        }
        ///tạo khóa học mới cần truyền vào với id tăng 1 và tên+ lộ trình;
        System.out.println("nhập lộ trình của bài tập");
        String inputLotrinh = Config.scanner().nextLine();
        LoTrinh loTrinh;
        switch (inputLotrinh) {
            case "java":
                loTrinh = LoTrinh.JAVA;
                break;
            case "java script":
                loTrinh = LoTrinh.JAVASCRIPT;
                break;
            default:
                System.out.println("sai lộ trình!");
                return;
    }
//        private String checkName(String name) {
//            if (name.matches("[a-zA-Z \\d]{1,100}")) {
//                return name;
//            } else {
//                return "wrongness";
//            }
//        }
        BaiTap baiTap = new BaiTap(lastId + 1, name, loTrinh);
        baiTapController.addBaiTap(baiTap);
        fromListBaiTap();

}

    private void fromListBaiTap() {
        baiTapController.getListBaiTap();
        for (BaiTap baiTap : controllerListBaiTap) {
            System.out.println(baiTap);
        }
    }
    }
