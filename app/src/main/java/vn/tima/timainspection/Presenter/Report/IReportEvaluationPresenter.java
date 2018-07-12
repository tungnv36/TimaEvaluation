package vn.tima.timainspection.Presenter.Report;

/**
 * Created by tima on 12/20/17.
 */

public interface IReportEvaluationPresenter {
    void getReportEvaluation(String fromDate, String toDate);
    void getReportByWeek(String fromDate, String toDate);
    void getReportTDHS2AndDaiLy(String fromDate, String toDate);
    void getReportBacklog(String fromDate, String toDate);
}
