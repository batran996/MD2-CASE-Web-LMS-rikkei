package rikkei.academy.service.classroom;

import rikkei.academy.config.Config;
import rikkei.academy.model.ClassRoom;

import java.util.ArrayList;
import java.util.List;

public class ClassroomServiceIMPL implements IClassroomSerVice {
    static Config<List<ClassRoom>> config = new Config<>();
    static String PATH_CLASSROOM = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\classroom.txt";
    static List<ClassRoom> classRoomList = config.read(PATH_CLASSROOM);

    static {
        if (classRoomList == null) {
            classRoomList = new ArrayList<>();
        }
    }

    @Override
    public List<ClassRoom> findAll() {
        return classRoomList;
    }

    @Override
    public void save(ClassRoom classRoom) {
        classRoomList.add(classRoom);
        config.write(PATH_CLASSROOM, classRoomList);
    }

    @Override
    public ClassRoom findByID(int id) {
        for (ClassRoom classroom : classRoomList) {
            if (classroom.getId() == id) {
                return classroom;
            }
        }
        return null;
    }

    @Override
    public void eDit(int id, ClassRoom classRoom) {
        ClassRoom classRoomEdit = findByID(id);
        classRoomEdit.setName(classRoom.getName());
        config.write(PATH_CLASSROOM, classRoomList);
    }

    @Override
    public void delete(int id, ClassRoom classRoom) {
        ClassRoom classRoomDelete = findByID(id);
        classRoomList.remove(classRoomDelete);
        config.write(PATH_CLASSROOM, classRoomList);
    }
}
