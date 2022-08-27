package rikkei.academy.service.baitap;

import rikkei.academy.config.Config;
import rikkei.academy.model.BaiTap;
import rikkei.academy.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class BaiTapServiceIMPL implements IBaiTapSerVice {
    static Config<List<BaiTap>> config = new Config<>();
    static String PATH_BAITAP = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\baitap.txt";
    static List<BaiTap> listBaiTap = config.read(PATH_BAITAP);

    static {
        if (listBaiTap == null) {
            listBaiTap = new ArrayList<>();
        }
    }


    @Override
    public List<BaiTap> findAll() {
        config.write(PATH_BAITAP, listBaiTap);
        return listBaiTap;
    }

    @Override
    public void save(BaiTap baiTap) {
        listBaiTap.add(baiTap);
        config.write(PATH_BAITAP, listBaiTap);

    }

    @Override
    public BaiTap findByID(int id) {
        for (BaiTap baiTap : listBaiTap) {
            if (baiTap.getId() == id) {
                return baiTap;
            }
        }
        return null;
    }

    @Override
    public void eDit(int id, BaiTap baiTap) {
        BaiTap baitapEdit = findByID(baiTap.getId());
        baitapEdit.setName(baiTap.getName());
        baitapEdit.setLoTrinh(baiTap.getLoTrinh());
        config.write(PATH_BAITAP, listBaiTap);
    }

    @Override
    public void delete(int id, BaiTap baiTap) {
        BaiTap baiTapdelete = findByID(id);
        listBaiTap.remove(baiTapdelete);
        config.write(PATH_BAITAP, listBaiTap);
    }
}
