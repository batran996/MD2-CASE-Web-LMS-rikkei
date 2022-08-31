package rikkei.academy.controller;

import rikkei.academy.model.Student;
import rikkei.academy.service.studen.IStudentService;
import rikkei.academy.service.studen.StudentServiceIMPL;

import java.awt.*;
import java.util.List;

public class StudenController {
IStudentService studentService = new StudentServiceIMPL();

    public List<Student> getListStudent() {

      return studentService.findAll();
    }

    public void save(Student student){
        studentService.save(student);
    }
    public Student getStudent(int id){
       return studentService.findByID(id);
    }

    public void upDateStudent(int idUpdate, Student newStuden) {
        studentService.eDit( idUpdate,newStuden);
    }
}
