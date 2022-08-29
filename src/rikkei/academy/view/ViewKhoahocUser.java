package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.KhoaHocController;
import rikkei.academy.controller.KhoaHocGanDayController;
import rikkei.academy.model.KhoaHoc;

import java.util.List;
import java.util.Locale;

public class ViewKhoahocUser {
    KhoaHocController khoaHocController = new KhoaHocController();
    List<KhoaHoc> khoaHocControllerList = khoaHocController.getListKhoaHoc();
    List<KhoaHoc> khoaHocYeuThichController = khoaHocController.getListYeuThich();
    KhoaHocGanDayController khoaHocGanDayController = new KhoaHocGanDayController();


    public void menuKhoaHoc() {
        System.out.println("Menu khóa học");
        System.out.println("0: back ");
        System.out.println("1: Danh sách khóa học");
        System.out.println("2: Tìm kiếm khóa học");
        System.out.println("3: Khóa học yêu thích");
        System.out.println("4: thêm Khóa học yêu thích");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        }catch (Exception e){
            menuKhoaHoc();
        }
        switch (choice) {
            case 0:
                return;
            case 1:
                fromDanhSachKhoaHoc();
                break;
            case 2:
                fromTimKhoaHoc();
                break;
            case 3:
                fromkhoaHocyeuthich();
                break;
            case 4:
                fromThemdanhSachYeuThich();
                break;
            default:
                System.err.println("Invalid choice");
        }
    }

    private void fromThemdanhSachYeuThich() {
        fromDanhSachKhoaHoc();
        System.out.println("Nhập ID để thêm vào danh sách yêu thích");
        int idyeuThich = -1;
        try {
            idyeuThich = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromkhoaHocyeuthich();
        }
        boolean check = false;
        for (int i = 0; i < khoaHocControllerList.size(); i++) {

            if (khoaHocControllerList.get(i).getId() == idyeuThich) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        KhoaHoc khoaYeuThich = khoaHocController.getKhoaHoc(idyeuThich);
        khoaHocController.addYeuThich(khoaYeuThich);

    }

    private void fromkhoaHocyeuthich() {
        System.out.println("Danh sách khóa yêu thích");
        if (khoaHocYeuThichController.isEmpty()) {
            System.out.println("danh sách trống");
            return;
        } else {
            for (KhoaHoc khoaHoc : khoaHocYeuThichController) {
                System.out.println(khoaHoc);
            }
        }
        System.out.println("nhập ID để xóa khỏi danh sách yêu thích");
        System.out.println("nhập 0 để back");
        int iDelete = -1;
        try {
            iDelete = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromkhoaHocyeuthich();
        }
        if (iDelete == 0) {
            return;
        }
        boolean check = false;
        for (int i = 0; i < khoaHocYeuThichController.size(); i++) {

            if (khoaHocYeuThichController.get(i).getId() == iDelete) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        KhoaHoc khoaHocDelete = khoaHocController.getKhoaHocYeuThich(iDelete);
        System.out.println(khoaHocDelete);
        khoaHocController.deleteKhoaHocYeuThich(iDelete, khoaHocDelete);
        System.out.println("delete success!");
    }

    private void fromTimKhoaHoc() {
        System.out.println("Nhập tên khóa học để tìm kiếm ");
        String nameSearch = Config.scanner().nextLine();
        for (KhoaHoc khoaHoc : khoaHocControllerList) {
            if (khoaHoc.getNameKhoaHoc().toLowerCase(Locale.ROOT).contains(nameSearch.toLowerCase())) {
                System.out.println(khoaHoc);
            }
        }
    }
    private void fromDanhSachKhoaHoc() {
        System.out.println("Danh sách khóa học");
        if (khoaHocControllerList == null) {
            System.out.println(" NULL");
            return;
        }
        for (KhoaHoc khoahoc : khoaHocControllerList) {
            System.out.println( "ID" + khoahoc.getId()+" " + khoahoc.getNameKhoaHoc());
        }
        System.out.println("Chọn ID  để xem chi tiết khóa học");
        int id = -1;
        try {
            id = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
        }
        boolean check = false;
        for (int i = 0; i < khoaHocControllerList.size(); i++) {

            if (khoaHocControllerList.get(i).getId() == id) {
                check = true;
                System.out.println(khoaHocControllerList.get(i));
                khoaHocGanDayController.createKhoaHocGanDay(khoaHocControllerList.get(i));
            }
        }
        if (!check) {
            System.err.println("không tìm thấy id");
            return;
        }
    }

}
