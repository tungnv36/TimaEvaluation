package vn.tima.timainspection.View.Statistical;

import java.util.ArrayList;

import vn.tima.timainspection.Model.Entity.RBacklogEntity;
import vn.tima.timainspection.Model.Entity.ReportByWeekEntity;
import vn.tima.timainspection.Model.Entity.ReportEvaluationEntity;
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;

/**
 * Created by tima on 12/20/17.
 */

public interface IStatisticalFragment {
    void getReportEvaluationSuccess(ArrayList<ReportEvaluationEntity> list_report);
    void getReportEvaluationFail(String msg);
    void getReportTDTDChuyenTDHS2Success(ArrayList<ReportTDHS2AndDaiLyEntity> list_report);
    void getReportTDTDChuyenTDHS2Fail(String msg);
    void getReportLastWeekSuccess(ArrayList<ReportByWeekEntity> list_report);
    void getReportLastWeekFail(String msg);
    void getReportBacklogSuccess(RBacklogEntity list_report);
    void getReportBacklogFail(String msg);
}
