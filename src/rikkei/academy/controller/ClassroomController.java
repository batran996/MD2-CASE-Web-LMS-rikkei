package rikkei.academy.controller;

import rikkei.academy.model.ClassRoom;
import rikkei.academy.service.classroom.ClassroomServiceIMPL;
import rikkei.academy.service.classroom.IClassroomSerVice;

import java.util.List;

public class ClassroomController {
IClassroomSerVice classroomSerVice = new ClassroomServiceIMPL();

    public List<ClassRoom> getListClassroom() {
      return classroomSerVice.findAll();

    }

    public void saveClassRoom(ClassRoom classRoom) {
        classroomSerVice.save(classRoom);
    }

    public ClassRoom getClassroom(int iDelete) {
        return classroomSerVice.findByID(iDelete);
    }

    public void deleteClassRoom(int iDelete, ClassRoom classRoomDelete) {
        classroomSerVice.delete(iDelete,classRoomDelete);
    }
}
