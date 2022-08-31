package rikkei.academy.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student  implements Serializable {
    int id;
    String name;
    String phone;
    String email;
    String address;
    LocalDate birt;
    String gender;
    ClassRoom classRoom;

    public Student() {
    }

    public Student(int id, String name, String phone, String email, String address, LocalDate birt, String gender, ClassRoom classRoom) {
        this.id = id;
        this.name = name;
        this.phone = String.valueOf(phone);
        this.email = email;
        this.address = address;
        this.birt = birt;
        this.gender = gender;
        this.classRoom = classRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = String.valueOf(phone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirt() {
        return birt;
    }

    public void setBirt(LocalDate birt) {
        this.birt = birt;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "Studen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birt=" + birt +
                ", gender='" + gender + '\'' +
                ", classRoom=" + classRoom +
                '}';
    }

}
