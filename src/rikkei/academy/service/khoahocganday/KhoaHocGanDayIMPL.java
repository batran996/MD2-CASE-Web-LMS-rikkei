package rikkei.academy.service.khoahocganday;

import rikkei.academy.config.Config;
import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.KhoaHocGanDay;

import java.util.ArrayList;
import java.util.List;

public class KhoaHocGanDayIMPL implements IKhoaHocGanDay {
    static Config<List<KhoaHocGanDay>> config = new Config<>();
    static String PATH_KHOAHOCGANDAY = "C:\\Users\\WINDOWS\\IdeaProjects\\MD2-CASE-Web LMS rikkei\\src\\rikkei\\academy\\database\\khoahocganday.txt";
    static List<KhoaHocGanDay> listKhoaHocGanDay = config.read(PATH_KHOAHOCGANDAY);

    static {
        if (listKhoaHocGanDay == null) {
            listKhoaHocGanDay = new ArrayList<>();
        }
    }


    @Override
    public List<KhoaHocGanDay> findAll() {
        config.write(PATH_KHOAHOCGANDAY,listKhoaHocGanDay);
        return listKhoaHocGanDay;
    }

    @Override
    public void save(KhoaHocGanDay khoaHocGanDay) {
        listKhoaHocGanDay.add(khoaHocGanDay);
        config.write(PATH_KHOAHOCGANDAY,listKhoaHocGanDay);

    }

    @Override
    public KhoaHocGanDay findByID(int id) {
        return null;
    }

    @Override
    public void eDit(int id, KhoaHocGanDay khoaHocGanDay) {

    }

    @Override
    public void delete(int id, KhoaHocGanDay khoaHocGanDay) {

    }
}
