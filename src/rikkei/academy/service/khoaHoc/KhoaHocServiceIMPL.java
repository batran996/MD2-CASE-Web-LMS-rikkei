package rikkei.academy.service.khoaHoc;

import rikkei.academy.config.Config;
import rikkei.academy.model.KhoaHoc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KhoaHocServiceIMPL implements IKhoaHocService {
    static Config<List<KhoaHoc>> config = new Config<>();
    static String PATH_KHOAHOCYEUTHICH = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\khoaHocYeuThich.txt";
    static String PATH_KHOAHOC = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\khoahoc.txt";

    static List<KhoaHoc> listYeuThich = config.read(PATH_KHOAHOCYEUTHICH);

    static {
        if (listYeuThich == null) {
            listYeuThich = new ArrayList<>();
        }
    }

    private static List<KhoaHoc> khoaHocList = config.read(PATH_KHOAHOC);

    static {
        if (khoaHocList == null) khoaHocList = new ArrayList<>();
    }


    @Override

    public List<KhoaHoc> findAll() {
        config.write(PATH_KHOAHOC, khoaHocList);
        return khoaHocList;
    }

    @Override
    public void save(KhoaHoc khoaHoc) {
        khoaHocList.add(khoaHoc);
        config.write(PATH_KHOAHOC, khoaHocList);
    }

    @Override
    public KhoaHoc findByID(int id) {
        for (KhoaHoc khoaHoc : khoaHocList) {
            if (khoaHoc.getId() == id) {
                return khoaHoc;
            }
        }
        return null;
    }

    @Override
    public void eDit(int id, KhoaHoc khoaHoc) {
        KhoaHoc khoaHocEdit = findByID(khoaHoc.getId());
        khoaHocEdit.setNameKhoaHoc(khoaHoc.getNameKhoaHoc());
        khoaHocEdit.setLoTrinh(khoaHoc.getLoTrinh());
        config.write(PATH_KHOAHOC, khoaHocList);
    }

    @Override
    public void delete(int id, KhoaHoc khoaHoc) {
        KhoaHoc khoaHocDelete = findByID(id);
        khoaHocList.remove(khoaHocDelete);
        config.write(PATH_KHOAHOC, khoaHocList);
    }


    @Override
    public void addYeuThich(KhoaHoc khoaYeuThich) {
        listYeuThich.add(khoaYeuThich);
        config.write(PATH_KHOAHOCYEUTHICH, listYeuThich);
    }

    @Override
    public List<KhoaHoc> listYeuThich() {
        config.write(PATH_KHOAHOCYEUTHICH, listYeuThich);
        return listYeuThich;
    }

    @Override
    public void deleteKhoaHocYeuthich(int iDelete, KhoaHoc khoaHocDelete) {
        KhoaHoc khoaHoc = getkhoaHocyeuThich(iDelete);
        listYeuThich.remove(khoaHoc);
        config.write(PATH_KHOAHOCYEUTHICH, listYeuThich);
    }

    @Override
    public KhoaHoc getkhoaHocyeuThich(int id) {
        for (KhoaHoc khoaHoc : listYeuThich) {
            if (khoaHoc.getId() == id) {
                return khoaHoc;
            }
        }
        return null;

    }
}
