package rikkei.academy.service.studen;

import rikkei.academy.config.Config;
import rikkei.academy.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService {
    static Config<List<Student>> config = new Config<>();
    static String PATH_STUDENT = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\studen.txt";
    static List<Student> studentList = config.read(PATH_STUDENT);

    static {
        if (studentList == null) {
            studentList = new ArrayList<>();
        }
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void save(Student studen) {
        studentList.add(studen);
        config.write(PATH_STUDENT, studentList);
    }

    @Override
    public Student findByID(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void eDit(int id, Student studen) {
        Student studentEdit = findByID(id);
        studentEdit.setName(studen.getName());
        studentEdit.setPhone(studen.getPhone());
        studentEdit.setEmail(studen.getEmail());
        studentEdit.setAddress(studen.getAddress());
        studentEdit.setBirt(studen.getBirt());
        studentEdit.setGender(studen.getGender());
        studentEdit.setClassRoom(studen.getClassRoom());
        config.write(PATH_STUDENT, studentList);
    }

    @Override
    public void delete(int id, Student studen) {
        Student studentDelete = findByID(id);
        studentList.remove(studentDelete);
        config.write(PATH_STUDENT, studentList);
    }
}
