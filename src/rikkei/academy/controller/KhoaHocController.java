package rikkei.academy.controller;

import rikkei.academy.model.KhoaHoc;
import rikkei.academy.service.khoaHoc.IKhoaHocService;
import rikkei.academy.service.khoaHoc.KhoaHocServiceIMPL;

import java.util.List;

public class KhoaHocController {
    IKhoaHocService khoaHocService = new KhoaHocServiceIMPL();
//    Config<List<KhoaHoc>> config = new Config<>();

    public List<KhoaHoc> getListKhoaHoc() {
        return khoaHocService.findAll();
    }

    public void saveKhoaHoc(KhoaHoc khoaHoc) {

        khoaHocService.save(khoaHoc);
    }

    public KhoaHoc getKhoaHoc(int id) {
        return khoaHocService.findByID(id);
    }

    public void eDitKhoahoc(int idEdit, KhoaHoc newKhoaHoc) {

        khoaHocService.eDit(idEdit, newKhoaHoc);
    }

    public void deleteKhoaHoc(int iDelete, KhoaHoc khoaHocDelete) {

        khoaHocService.delete(iDelete,khoaHocDelete);
    }

    public void addYeuThich(KhoaHoc khoaYeuThich) {
        khoaHocService.addYeuThich(khoaYeuThich);
    }

    public List<KhoaHoc> getListYeuThich() {
        return khoaHocService.listYeuThich();
    }

    public KhoaHoc getKhoaHocYeuThich(int id) {
       return khoaHocService.getkhoaHocyeuThich(id);
    }

    public void deleteKhoaHocYeuThich(int iDelete, KhoaHoc khoaHocDelete) {
        khoaHocService.deleteKhoaHocYeuthich(iDelete,khoaHocDelete);
    }
}
