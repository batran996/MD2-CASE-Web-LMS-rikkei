package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.ClassroomController;
import rikkei.academy.model.ClassRoom;
import rikkei.academy.model.KhoaHoc;

import java.util.List;

public class ClassroomManage {
    ClassroomController classroomController = new ClassroomController();
    List<ClassRoom>classRoomListController = classroomController.getListClassroom();
    public void classroomMenu(){
        System.out.println("***Menu ClassRoom***");
        System.out.println("1:Show list Classroom");
        System.out.println("2:Create Classroom");
        System.out.println("3:Delete Classroom");
        System.out.println("4:Detail Classroom");
        System.out.println("5:Update Classroom");
        System.out.println("6:Back ");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            classroomMenu();
        }
        switch (choice){
            case 1:
                formShowListClassRoom();
                break;
            case 2:
                fromCreateClassRoom();
                break;
              case 3:
                fromDeleteClassRoom();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice!");
        }
        classroomMenu();
    }

    private void fromDeleteClassRoom() {
        formShowListClassRoom();
        System.out.println("Nhập ID Lớp học bạn muốn xóa");
        int iDelete = -1;
        try {
            iDelete = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromDeleteClassRoom();
        }
        boolean check = false;
        for (int i = 0; i < classRoomListController.size(); i++) {
            if (classRoomListController.get(i).getId() == iDelete) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        ClassRoom classRoomDelete = classroomController.getClassroom(iDelete);
        classroomController.deleteClassRoom(iDelete, classRoomDelete);
        System.out.println("Delete success!");
        formShowListClassRoom();

    }

    private void fromCreateClassRoom() {
        System.out.println("nhập tên lớp học mới");
        String name = "";
//        name = checkName(name);
        name = Config.scanner().nextLine();
        if (!name.matches("[a-zA-Z \\d]{1,100}")) {
            System.out.println("Tên sai định dạng");
            return;
        }
        for (ClassRoom classRoom : classRoomListController) {
            if (name.equalsIgnoreCase(classRoom.getName())) {
                System.out.println("lớp học đã tồn tại ");
                return;
            }
        }

        int lastId;
        if (classRoomListController.isEmpty()) {
            lastId = 1;
        } else {
            lastId = classRoomListController.get(classRoomListController.size() - 1).getId() + 1;
        }
        ClassRoom classRoom = new ClassRoom(lastId, name);
        classroomController.saveClassRoom(classRoom);
        formShowListClassRoom();
    }

    private void formShowListClassRoom() {
        if (classroomController.getListClassroom() == null){
            System.out.println("danh sách trống");
            return;
        }
        System.out.println("Danh sách lớp học");

        for(ClassRoom classRoom : classRoomListController) {
            System.out.println("ID:"+classRoom.getId()+" " + "Lớp:"+ classRoom.getName());
        }
    }
}
