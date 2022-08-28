package rikkei.academy.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class KhoaHocGanDay implements Serializable {
    int id;
    int idUser;
    Queue<KhoaHoc> khoaHocQueue = new LinkedList<>();

    public KhoaHocGanDay() {
    }

    public KhoaHocGanDay(int id, int idUser, Queue<KhoaHoc> khoaHocQueue) {
        this.id = id;
        this.idUser = idUser;
        this.khoaHocQueue = khoaHocQueue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Queue<KhoaHoc> getKhoaHocQueue() {
        return khoaHocQueue;
    }

    public void setKhoaHocQueue(Queue<KhoaHoc> khoaHocQueue) {
        this.khoaHocQueue = khoaHocQueue;
    }

}
