package rikkei.academy.model;

import java.io.Serializable;

public class BaiTap implements Serializable {
   private int id;
   private String name;
    private LoTrinh loTrinh;

    public BaiTap() {
    }

    public BaiTap(int id, String name, LoTrinh loTrinh) {
        this.id = id;
        this.name = name;
        this.loTrinh = loTrinh;
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

    public LoTrinh getLoTrinh() {
        return loTrinh;
    }

    public void setLoTrinh(LoTrinh loTrinh) {
        this.loTrinh = loTrinh;
    }

    @Override
    public String toString() {
        return "BaiTap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", loTrinh=" + loTrinh +
                '}';
    }
}
