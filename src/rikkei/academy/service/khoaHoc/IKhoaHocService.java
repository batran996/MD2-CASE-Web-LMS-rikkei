package rikkei.academy.service.khoaHoc;

import rikkei.academy.model.KhoaHoc;
import rikkei.academy.service.IGernericService;

import java.util.List;

public interface IKhoaHocService extends IGernericService<KhoaHoc> {

    void addYeuThich(KhoaHoc khoaYeuThich);

    List<KhoaHoc> listYeuThich();

    void deleteKhoaHocYeuthich(int iDelete, KhoaHoc khoaHocDelete);

    KhoaHoc getkhoaHocyeuThich(int id);

}
