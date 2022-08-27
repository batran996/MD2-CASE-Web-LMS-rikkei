package rikkei.academy.service;

import rikkei.academy.model.KhoaHoc;

import java.util.List;

public interface IGernericService <T>{
    List<T> findAll();
    void save(T t);

    T findByID(int id);
    void eDit(int id, T t);
    void delete(int id,T t);
}
