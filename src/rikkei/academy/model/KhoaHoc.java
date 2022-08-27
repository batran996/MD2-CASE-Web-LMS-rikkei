package rikkei.academy.model;

import java.io.Serializable;

public class KhoaHoc implements Serializable {
    private int id;
    private String nameKhoaHoc;
    private LoTrinh loTrinh;



    public KhoaHoc(int id, String nameKhoaHoc, LoTrinh loTrinh) {
        this.id = id;
        this.nameKhoaHoc = nameKhoaHoc;
        this.loTrinh = loTrinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameKhoaHoc() {
        return nameKhoaHoc;
    }

    public void setNameKhoaHoc(String nameKhoaHoc) {
        this.nameKhoaHoc = nameKhoaHoc;
    }

    public LoTrinh getLoTrinh() {
        return loTrinh;
    }

    public void setLoTrinh(LoTrinh loTrinh) {
        this.loTrinh = loTrinh;
    }

    @Override
    public String toString() {
        return "KhoaHoc{" +
                "id=" + id +
                ", nameKhoaHoc='" + nameKhoaHoc + '\'' +
                ", loTrinh=" + loTrinh +
                '}';
    }
}
