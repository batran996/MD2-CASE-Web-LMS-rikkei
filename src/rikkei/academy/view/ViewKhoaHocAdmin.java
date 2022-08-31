package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.KhoaHocController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.LoTrinh;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewKhoaHocAdmin {
    KhoaHocController khoaHocController = new KhoaHocController();
    List<KhoaHoc> khoaHocControllerList = khoaHocController.getListKhoaHoc();
    UserController userController = new UserController();
    User currentUser = userController.getCurrentuser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();
public void khoaHocAdmin(){
    System.out.println("0:back");
    System.out.println("1:Danh Sách khóa học");
    System.out.println("2:Thêm khóa học");
    System.out.println("3:Sửa khóa học");
    System.out.println("4:Tìm kiếm khóa học");
    System.out.println("5:Xóa khóa học");
    int choice = -1;
    try {
        choice = Integer.parseInt(Config.scanner().nextLine());
    }catch (Exception e){
        khoaHocAdmin();
    }
    switch (choice) {
        case 0:
            return;
        case 1:
            fromDanhSachKhoaHoc();
          break;
        case 2:
            fromCreateKhoaHoc();
            break;
        case 3:
            fromEditKhoaHoc();
            break;
        case 4:
            fromSheachKhoaHoc();
            break;
        case 5:
            fromDeleteKhoaHoc();
            break;
        default:
            System.err.println("Invlaid choice!");
    }
    khoaHocAdmin();
}
    private void fromDeleteKhoaHoc() {
        fromDanhSachKhoaHoc();
        System.out.println("Nhập ID khóa học bạn muốn xóa");
        int iDelete = -1;
        try {
            iDelete = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromDeleteKhoaHoc();
        }
        boolean check = false;
        for (int i = 0; i < khoaHocControllerList.size(); i++) {
            if (khoaHocControllerList.get(i).getId() == iDelete) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        KhoaHoc khoaHocDelete = khoaHocController.getKhoaHoc(iDelete);
        khoaHocController.deleteKhoaHoc(iDelete, khoaHocDelete);
        fromDanhSachKhoaHoc();
    }

    private void fromSheachKhoaHoc() {
        System.out.println("Nhập tên khóa học để tìm kiếm ");
        String nameSearch = Config.scanner().nextLine();
        for (KhoaHoc khoaHoc : khoaHocControllerList) {
            if (khoaHoc.getNameKhoaHoc().toLowerCase(Locale.ROOT).contains(nameSearch.toLowerCase())) {
                System.out.println(khoaHoc);
            }
        }
    }

    private void fromEditKhoaHoc() {
        fromDanhSachKhoaHoc();
        System.out.println("Nhập ID khóa học muốn sửa");
        int idEdit = -1;
        try {
            idEdit = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromEditKhoaHoc();
        }
        boolean check = false;
        for (int i = 0; i < khoaHocControllerList.size(); i++) {
            if (khoaHocControllerList.get(i).getId() == idEdit) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }

        KhoaHoc khoahocEDit = khoaHocController.getKhoaHoc(idEdit);
        ///check sửa tên mới cho khóa học
        System.out.println("Nhập tên mới cho khóa học" +
                " Enter để bỏ qua");

        String newName = Config.scanner().nextLine();
        if (newName.trim().equals("")) {
            newName = khoahocEDit.getNameKhoaHoc();
        } else {
            while (checkName(newName).equals("wrongness")) {
                System.err.println("Sai dinh dang name vui long nhap lai");
                newName = Config.scanner().nextLine();
            }
            for (KhoaHoc khoaHoc : khoaHocControllerList) {
                if (newName.equalsIgnoreCase(khoaHoc.getNameKhoaHoc())) {
                    System.err.println("khóa học đã tồn tại ");
                    return;
                }
            }
        }
        ////check lộ trình
        checkLoTrinh(idEdit, newName);
    }

    private void checkLoTrinh(int idEdit, String newName) {
        System.out.println("Nhập lộ trình mới cho khóa học");
        String newLoTrinh = Config.scanner().nextLine();
        if (newLoTrinh.equals("java")) {

            KhoaHoc newKhoaHoc = new KhoaHoc(idEdit, newName, LoTrinh.JAVA);
            khoaHocController.eDitKhoahoc(idEdit, newKhoaHoc);
        } else if (newLoTrinh.equals("java script")) {
            KhoaHoc newKhoaHoc = new KhoaHoc(idEdit, newName, LoTrinh.JAVASCRIPT);
            khoaHocController.eDitKhoahoc(idEdit, newKhoaHoc);
        } else {
            System.err.println("Invalid lộ trình");
        }
        fromDanhSachKhoaHoc();
    }

    private void fromCreateKhoaHoc() {
        //// check ky tu dac biet cua name;
        System.out.println("nhập tên khóa học mới");
        String name = "";
//        name = checkName(name);
        name = Config.scanner().nextLine();
        if (!name.matches("[a-zA-Z \\d]{1,100}")) {
            System.out.println("Tên sai định dạng");
            return;
        }
        for (KhoaHoc khoaHoc : khoaHocControllerList) {
            if (name.equalsIgnoreCase(khoaHoc.getNameKhoaHoc())) {
                System.out.println("khóa học đã tồn tại ");
                return;
            }
        }

        int lastId;
        if (khoaHocControllerList.isEmpty()) {
            lastId = 1;
        } else {
            lastId = khoaHocControllerList.get(khoaHocControllerList.size() - 1).getId()+1;
        }
        ///tạo khóa học mới cần truyền vào với id tăng 1 và tên+ lộ trình;
        System.out.println("nhập lộ trình khóa học");
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

        KhoaHoc khoaHoc1 = new KhoaHoc(lastId , name, loTrinh);
        khoaHocController.saveKhoaHoc(khoaHoc1);
        fromDanhSachKhoaHoc();

    }

    private void fromDanhSachKhoaHoc() {

        for (KhoaHoc khoaHoc : khoaHocControllerList) {
            System.out.println(khoaHoc);
        }
    }

    private String checkName(String name) {
        if (name.matches("[a-zA-Z \\d]{1,100}")) {
            return name;
        } else {
            return "wrongness";
        }
    }
}
