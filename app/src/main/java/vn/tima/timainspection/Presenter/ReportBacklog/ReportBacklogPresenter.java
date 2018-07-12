package vn.tima.timainspection.Presenter.ReportBacklog;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.Entity.ReportBacklogEntity;
import vn.tima.timainspection.Model.ReportBacklog.ReportBacklogRequest;
import vn.tima.timainspection.View.Report.IReportBacklogFragment;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 3/14/18.
 */

public class ReportBacklogPresenter implements IReportBacklogPresenter {

    private ReportBacklogRequest reportBacklogRequest;
    private IReportBacklogFragment iReportBacklogFragment;

    public ReportBacklogPresenter(Context context, IReportBacklogFragment iReportBacklogFragment) {
        this.iReportBacklogFragment = iReportBacklogFragment;
        reportBacklogRequest = ReportBacklogRequest.getInstance(context);
    }

    @Override
    public void getReportBacklog(String fromDate, String toDate) {
        reportBacklogRequest.getReportBacklogList(new OnResponse<String, List<ReportBacklogEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<ReportBacklogEntity> extraData) {
                if(extraData != null) {
                    iReportBacklogFragment.getReportBacklogSuccess(extraData);
                } else {
                    iReportBacklogFragment.getReportBacklogFailed(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iReportBacklogFragment.getReportBacklogFailed(message);
            }
        }, fromDate, toDate);
    }

}
