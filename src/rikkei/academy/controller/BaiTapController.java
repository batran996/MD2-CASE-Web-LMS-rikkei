package rikkei.academy.controller;

import rikkei.academy.model.BaiTap;
import rikkei.academy.service.baitap.BaiTapServiceIMPL;
import rikkei.academy.service.baitap.IBaiTapSerVice;

import java.util.List;

public class BaiTapController {
    IBaiTapSerVice baiTapSerVice = new BaiTapServiceIMPL();

    public List<BaiTap> getListBaiTap() {
       return baiTapSerVice.findAll();
    }

    public void addBaiTap(BaiTap baiTap) {
        baiTapSerVice.save(baiTap);

    }

    public void deleteBaitap(int id,BaiTap baiTapDelete) {
        baiTapSerVice.delete(id, baiTapDelete);
    }

    public BaiTap getBaiTap(int iDelete) {
      return  baiTapSerVice.findByID(iDelete);
    }
}
