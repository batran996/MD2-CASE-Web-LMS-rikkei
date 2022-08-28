package rikkei.academy.controller;

import rikkei.academy.model.KhoaHoc;
import rikkei.academy.model.KhoaHocGanDay;
import rikkei.academy.service.khoahocganday.IKhoaHocGanDay;
import rikkei.academy.service.khoahocganday.KhoaHocGanDayIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KhoaHocGanDayController {
    IKhoaHocGanDay khoahocganDaySerVice = new KhoaHocGanDayIMPL();
    IUserService userService = new UserServiceIMPL();
    List<KhoaHocGanDay> khoaHocGanDayList = khoahocganDaySerVice.findAll();

    public void createKhoaHocGanDay(KhoaHoc khoaHoc) {
        KhoaHocGanDay khoaHocGanDay = null;
        for (KhoaHocGanDay khoaHocGanDay1 : khoahocganDaySerVice.findAll()) {
            if (khoaHocGanDay1.getIdUser() == userService.getCurrentUser().getId()) {
                khoaHocGanDay = khoaHocGanDay1;
            }
        }
        if (khoaHocGanDay == null) {
            int id;

            if (khoaHocGanDayList.isEmpty()) {
                id = 1;
            } else {
                id = khoaHocGanDayList.get(khoaHocGanDayList.size() - 1).getId() + 1;
            }
            Queue<KhoaHoc> listKHGanday = new LinkedList<>();
            listKHGanday.add(khoaHoc);
            KhoaHocGanDay khoaHocGanDay2 = new KhoaHocGanDay(id, userService.getCurrentUser().getId(), listKHGanday);
            khoahocganDaySerVice.save(khoaHocGanDay2);
        } else {
            Queue<KhoaHoc> listKHGanday = khoaHocGanDay.getKhoaHocQueue();
            if (listKHGanday.size() == 5) {
                listKHGanday.remove();
                listKHGanday.add(khoaHoc);
            } else {
                listKHGanday.add(khoaHoc);
            }
            khoahocganDaySerVice.findAll();
        }
    }

    public KhoaHocGanDay getKhoaHocGanDay() {
        for (KhoaHocGanDay khoaHocGanDay : khoahocganDaySerVice.findAll()) {
            if (khoaHocGanDay.getIdUser() == userService.getCurrentUser().getId()) {
                return khoaHocGanDay;
            }
        }
        return null;
    }
}
