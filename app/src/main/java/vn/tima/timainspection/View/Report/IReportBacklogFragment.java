package vn.tima.timainspection.View.Report;

import java.util.List;

import vn.tima.timainspection.Model.Entity.ReportBacklogEntity;

/**
 * Created by tima on 3/14/18.
 */

public interface IReportBacklogFragment {
    void getReportBacklogSuccess(List<ReportBacklogEntity> extraData);
    void getReportBacklogFailed(String msg);
}
