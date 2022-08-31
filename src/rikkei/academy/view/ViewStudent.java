package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.ClassroomController;
import rikkei.academy.controller.StudenController;
import rikkei.academy.model.ClassRoom;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.Student;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewStudent {
    ClassroomController classroomController = new ClassroomController();
    StudenController studenController = new StudenController();
    List<Student> studentListController = studenController.getListStudent();

    public void menuStudent() {
        System.out.println("1:Show list student");
        System.out.println("2:Create student");
        System.out.println("3:Update student");
        System.out.println("4:Delete student");
        System.out.println("5:Detail student");
        System.out.println("6:Back");
        int choice = -1;
        try {
            choice = Integer.parseInt(Config.scanner().nextLine());
        } catch (Exception e) {
            menuStudent();
        }
        switch (choice) {
            case 1:
                fromListStudent();
                break;
            case 2:
                fromCeateStudent();
                break;
            case 3:
                fromUpdateStudent();
                break;
            case 4:
                fromDeleteStudent();
                break;
            case 5:
                fromDetailStudent();
                break;
            case 6:
                return;
            default:
                System.err.println("Invlaid choice!");
        }
    }

    private void fromDetailStudent() {
        fromListStudent();
        System.out.println("Chon ID để xem chi tiết học sinh");
        int id = Integer.parseInt(Config.scanner().nextLine());
       Student student = studenController.getStudent(id);
        System.out.println(student);
    }

    private void fromDeleteStudent() {
    }

    private void fromUpdateStudent() {
        fromListStudent();
        System.out.println("Nhập ID để update student");
        int idUpdate = -1;
        try {
            idUpdate = Integer.parseInt(Config.scanner().nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Invalid choice!");
            fromUpdateStudent();
        }
        boolean check = false;
        for (int i = 0; i < studentListController.size(); i++) {
            if (studentListController.get(i).getId() == idUpdate) {
                check = true;
            }
        }
        if (!check) {
            System.out.println("không tìm thấy id");
            return;
        }
        Student studentUpdate = studenController.getStudent(idUpdate);
        System.out.println("Nhập tên mới cho khóa học,Ấn Enter để bỏ qua");
        String newName = Config.scanner().nextLine();
        if (newName.trim().equals("")) {
            newName = studentUpdate.getName();
        }
        System.out.println("Nhập số điện thoại mới,enter để bỏ qua");
        String newPhonnumber = Config.scanner().nextLine();
        if (newPhonnumber.trim().equals("")) {
            newPhonnumber = String.valueOf(studentUpdate.getPhone());
        }
        System.out.println("Nhập email mới,chọn Enter để bỏ qua");
        String newEmail = Config.scanner().nextLine();
        if (newEmail.trim().equals("")) {
            newEmail = studentUpdate.getEmail();
        }
        System.out.println("Nhập địa chỉ,chọn Enter để bỏ qua");
        String newAddress = Config.scanner().nextLine();
        if (newAddress.trim().equals("")) {
            newAddress = studentUpdate.getAddress();
        }
        System.out.println("Nhập ngày sinh ");
        String newDateStr = Config.scanner().nextLine();
        LocalDate newDate;
        if (newDateStr.trim().equals("")) {
            newDate = studentUpdate.getBirt();
        } else {
            newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        }
        System.out.println("nhập giới tính,chọn enter để bỏ qua");
        String newGender = Config.scanner().nextLine();
        if (newGender.trim().equals("")) {
            newGender = studentUpdate.getGender();
        }
        System.out.println(classroomController.getListClassroom());
        System.out.println("nhập class mới");
        String idClassroom = Config.scanner().nextLine();
        ClassRoom newClassRoom;
        if (idClassroom.trim().equals("")) {
            newClassRoom = studentUpdate.getClassRoom();
        } else {
            int newIdClass = Integer.parseInt(idClassroom);
            newClassRoom = classroomController.getClassroom(newIdClass);
        }
        Student newStuden = new Student(studentUpdate.getId(), newName
                , newPhonnumber, newEmail, newAddress, newDate,
                newGender, newClassRoom);
        studenController.upDateStudent(idUpdate, newStuden);
        fromListStudent();
    }

    private void fromCeateStudent() {

        System.out.println("Nhập tên học sinh");
        String name = Config.scanner().nextLine();
        System.out.println("nhập số điện thoại");
        String phoneNumber = Config.scanner().nextLine();
        System.out.println("Nhập email");
        String email = Config.scanner().nextLine();
        System.out.println("Nhập địa chỉ");
        String address = Config.scanner().nextLine();
        System.out.println("Nhập ngày sinh ");
        String date = "";
        while (!date.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|" +
                "Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|" +
                "Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)" +
                "(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468]" +
                "[048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May" +
                "|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
            date = Config.scanner().nextLine();
            if (!date.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul" +
                    "|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr" +
                    "|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29" +
                    "(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579]" +
                    "[26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:" +
                    "(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:" +
                    "(?:1[6-9]|[2-9]\\d)?\\d{2})$")) {
                System.out.println("Invalid name");
            }
        }
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("nhập giới tính");
        String gender = Config.scanner().nextLine();
        System.out.println(classroomController.getListClassroom());
        System.out.println("nhập class");
        int idClass = Integer.parseInt(Config.scanner().nextLine());
        ClassRoom classRoom = classroomController.getClassroom(idClass);


        int lastId;
        if (studentListController.isEmpty()) {
            lastId = 1;
        } else {
            lastId = studentListController.get(studentListController.size() - 1).getId() + 1;
        }
        Student student = new Student(lastId, name, phoneNumber, email, address, localDate, gender, classRoom);
        studenController.save(student);
        fromListStudent();
    }

    private void fromListStudent() {
        if (studentListController == null) {
            System.err.println("Danh sách trống");
            return;
        }
        for (Student student : studentListController) {
            System.out.println("ID" + student.getId() + " " + student.getName() + " lớp "
                    + student.getClassRoom().getName());
        }
    }

}