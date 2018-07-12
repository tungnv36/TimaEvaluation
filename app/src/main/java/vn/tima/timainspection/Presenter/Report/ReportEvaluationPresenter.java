package vn.tima.timainspection.Presenter.Report;

import android.content.Context;

import java.util.ArrayList;

import vn.tima.timainspection.Model.Entity.RBacklogEntity;
import vn.tima.timainspection.Model.Entity.ReportByWeekEntity;
import vn.tima.timainspection.Model.Entity.ReportEvaluationEntity;
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;
import vn.tima.timainspection.Model.Report.ReportEvaluationRequest;
import vn.tima.timainspection.View.Statistical.IStatisticalFragment;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 12/20/17.
 */

public class ReportEvaluationPresenter implements IReportEvaluationPresenter {

    private ReportEvaluationRequest reportEvaluationRequest;
    private IStatisticalFragment iStatisticalFragment;

    public ReportEvaluationPresenter(Context context, IStatisticalFragment iStatisticalFragment){
        this.iStatisticalFragment = iStatisticalFragment;
        reportEvaluationRequest = ReportEvaluationRequest.getInstance(context);
    }

    private int getPosEvaluation(ArrayList<ReportEvaluationEntity> extraData, int productID) {
        for(int i = 0; i < extraData.size(); i++) {
            ReportEvaluationEntity reportEvaluationEntity = extraData.get(i);
            if(reportEvaluationEntity.getProductId() == productID) {
                return i;
            }
        }
        return -1;
    }

    private int getPosByWeek(ArrayList<ReportByWeekEntity> extraData, int productID) {
        for(int i = 0; i < extraData.size(); i++) {
            ReportByWeekEntity reportByWeekEntity = extraData.get(i);
            if(reportByWeekEntity.getProductId() == productID) {
                return i;
            }
        }
        return -1;
    }

    private int getPosTDTDAndTDHS2(ArrayList<ReportTDHS2AndDaiLyEntity> extraData, int productID) {
        for(int i = 0; i < extraData.size(); i++) {
            ReportTDHS2AndDaiLyEntity reportTDHS2AndDaiLyEntity = extraData.get(i);
            if(reportTDHS2AndDaiLyEntity.getProductId() == productID) {
                return i;
            }
        }
        return -1;
    }

//    private int getPosBacklog(ArrayList<RBacklogEntity> extraData, int productID) {
//        for(int i = 0; i < extraData.size(); i++) {
//            RBacklogEntity rBacklogEntity = extraData.get(i);
//            if(rBacklogEntity.getProductid() == productID) {
//                return i;
//            }
//        }
//        return -1;
//    }

    @Override
    public void getReportEvaluation(String fromDate, String toDate) {
        reportEvaluationRequest.getReportEvaluationHomeAndCompany(new OnResponse<String, ArrayList<ReportEvaluationEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ArrayList<ReportEvaluationEntity> extraData) {
                if(extraData != null) {
//                    int pos = getPosEvaluation(extraData, 2);
//                    if(pos != -1) {
//                        ReportEvaluationEntity old = extraData.get(pos);
//                        ReportEvaluationEntity ree = new ReportEvaluationEntity();
//                        ree.setAgencyConfirm(old.getAgencyConfirm());
//                        ree.setCreateDate(old.getCreateDate());
//                        ree.setCreateDateInfo(old.getCreateDateInfo());
//                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
//                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
//                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationTima(old.getNumberMotobikeEvaluationTima());
//                        ree.setProductName(old.getProductName() + " (HUB)");
//                        ree.setProductId(old.getProductId());
//                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
//                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
//                        extraData.add(pos + 1, ree);
//                    }
//                    pos = getPosEvaluation(extraData, 5);
//                    if(pos != -1) {
//                        ReportEvaluationEntity old = extraData.get(pos);
//                        ReportEvaluationEntity ree = new ReportEvaluationEntity();
//                        ree.setAgencyConfirm(old.getAgencyConfirm());
//                        ree.setCreateDate(old.getCreateDate());
//                        ree.setCreateDateInfo(old.getCreateDateInfo());
//                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
//                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
//                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationTima(old.getNumberMotobikeEvaluationTima());
//                        ree.setProductName(old.getProductName() + " (HUB)");
//                        ree.setProductId(old.getProductId());
//                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
//                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
//                        extraData.add(pos + 1, ree);
//                    }
                    iStatisticalFragment.getReportEvaluationSuccess(extraData);
                } else {
                    iStatisticalFragment.getReportEvaluationFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iStatisticalFragment.getReportEvaluationFail(message);
            }
        }, fromDate, toDate);
    }

    @Override
    public void getReportByWeek(String fromDate, String toDate) {
        reportEvaluationRequest.getReportEvaluationByWeekend(new OnResponse<String, ArrayList<ReportByWeekEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ArrayList<ReportByWeekEntity> extraData) {
                if(extraData != null) {
//                    int pos = getPosByWeek(extraData, 2);
//                    if(pos != -1) {
//                        ReportByWeekEntity old = extraData.get(pos);
//                        ReportByWeekEntity ree = new ReportByWeekEntity();
//                        ree.setAgencyConfirm(old.getAgencyConfirm());
//                        ree.setCreateDate(old.getCreateDate());
//                        ree.setCreateDateInfo(old.getCreateDateInfo());
//                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
//                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
//                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationTima(old.getNumberMotobikeEvaluationTima());
//                        ree.setProductName(old.getProductName() + " (HUB)");
//                        ree.setProductId(old.getProductId());
//                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
//                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
//                        extraData.add(pos + 1, ree);
//                    }
//                    pos = getPosByWeek(extraData, 5);
//                    if(pos != -1) {
//                        ReportByWeekEntity old = extraData.get(pos);
//                        ReportByWeekEntity ree = new ReportByWeekEntity();
//                        ree.setAgencyConfirm(old.getAgencyConfirm());
//                        ree.setCreateDate(old.getCreateDate());
//                        ree.setCreateDateInfo(old.getCreateDateInfo());
//                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
//                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
//                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationTima(old.getNumberMotobikeEvaluationTima());
//                        ree.setProductName(old.getProductName() + " (HUB)");
//                        ree.setProductId(old.getProductId());
//                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
//                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
//                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
//                        extraData.add(pos + 1, ree);
//                    }
                    iStatisticalFragment.getReportLastWeekSuccess(extraData);
                } else {
                    iStatisticalFragment.getReportLastWeekFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iStatisticalFragment.getReportLastWeekFail(message);
            }
        }, fromDate, toDate);
    }

    @Override
    public void getReportTDHS2AndDaiLy(String fromDate, String toDate) {
        reportEvaluationRequest.getReportEvaluationByLoanCredit(new OnResponse<String, ArrayList<ReportTDHS2AndDaiLyEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ArrayList<ReportTDHS2AndDaiLyEntity> extraData) {
                if(extraData != null) {
                    int pos = getPosTDTDAndTDHS2(extraData, 2);
                    if(pos != -1) {
                        ReportTDHS2AndDaiLyEntity old = extraData.get(pos);
                        ReportTDHS2AndDaiLyEntity ree = new ReportTDHS2AndDaiLyEntity();
                        ree.setAgencyConfirm(old.getAgencyConfirm());
                        ree.setCreateDate(old.getCreateDate());
                        ree.setCreateDateInfo(old.getCreateDateInfo());
                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
                        ree.setNumberMotobikeEvaluation(old.getNumberMotobikeEvaluationHub());
                        ree.setProductName(old.getProductName() + " (HUB)");
                        ree.setProductId(old.getProductId());
                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
                        extraData.add(pos + 1, ree);
                    }
                    pos = getPosTDTDAndTDHS2(extraData, 5);
                    if(pos != -1) {
                        ReportTDHS2AndDaiLyEntity old = extraData.get(pos);
                        ReportTDHS2AndDaiLyEntity ree = new ReportTDHS2AndDaiLyEntity();
                        ree.setAgencyConfirm(old.getAgencyConfirm());
                        ree.setCreateDate(old.getCreateDate());
                        ree.setCreateDateInfo(old.getCreateDateInfo());
                        ree.setFieldEvaluationConfirm(old.getFieldEvaluationConfirm());
                        ree.setNumberCompanyEvaluation(old.getNumberCompanyEvaluationHub());
                        ree.setNumberHomeEvaluation(old.getNumberHomeEvaluationHub());
                        ree.setNumberMotobikeEvaluation(old.getNumberMotobikeEvaluationHub());
                        ree.setProductName(old.getProductName() + " (HUB)");
                        ree.setProductId(old.getProductId());
                        ree.setNumberHomeEvaluationHub(old.getNumberHomeEvaluationHub());
                        ree.setNumberMotobikeEvaluationHub(old.getNumberMotobikeEvaluationHub());
                        ree.setNumberCompanyEvaluationHub(old.getNumberCompanyEvaluationHub());
                        extraData.add(pos + 1, ree);
                    }
                    iStatisticalFragment.getReportTDTDChuyenTDHS2Success(extraData);
                } else {
                    iStatisticalFragment.getReportTDTDChuyenTDHS2Fail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iStatisticalFragment.getReportTDTDChuyenTDHS2Fail(message);
            }
        }, fromDate, toDate);
    }

    @Override
    public void getReportBacklog(String fromDate, String toDate) {
        reportEvaluationRequest.getReportBacklog(new OnResponse<String, RBacklogEntity>() {
            @Override
            public void onResponseSuccess(String tag, String rs, RBacklogEntity extraData) {
                if(extraData != null) {
//                    int pos = getPosBacklog(extraData, 2);
//                    if(pos != -1) {
//                        RBacklogEntity old = extraData.get(pos);
//                        RBacklogEntity ree = new RBacklogEntity();
//                        ree.setCityid(old.getCityid());
//                        ree.setCityname(old.getCityname());
//                        ree.setCustomercreditname(old.getCustomercreditname());
//                        ree.setDistrictid(old.getDistrictid());
//                        ree.setDistrictname(old.getDistrictname());
//                        ree.setFullname(old.getFullname());
//                        ree.setIscongty(old.getIscongty());
//                        ree.setIsnha(old.getIsnha());
//                        ree.setLoancreditid(old.getLoancreditid());
//                        ree.setLoancreditopenningid(old.getLoancreditopenningid());
//                        ree.setLoantime(old.getLoantime());
//                        ree.setOpenningday(old.getOpenningday());
//                        ree.setProductid(old.getProductid());
//                        ree.setTotalmoney(old.getTotalmoney());
//                        ree.setUserid(old.getUserid());
//                        extraData.add(pos + 1, ree);
//                    }
//                    pos = getPosBacklog(extraData, 5);
//                    if(pos != -1) {
//                        RBacklogEntity old = extraData.get(pos);
//                        RBacklogEntity ree = new RBacklogEntity();
//                        ree.setCityid(old.getCityid());
//                        ree.setCityname(old.getCityname());
//                        ree.setCustomercreditname(old.getCustomercreditname());
//                        ree.setDistrictid(old.getDistrictid());
//                        ree.setDistrictname(old.getDistrictname());
//                        ree.setFullname(old.getFullname());
//                        ree.setIscongty(old.getIscongty());
//                        ree.setIsnha(old.getIsnha());
//                        ree.setLoancreditid(old.getLoancreditid());
//                        ree.setLoancreditopenningid(old.getLoancreditopenningid());
//                        ree.setLoantime(old.getLoantime());
//                        ree.setOpenningday(old.getOpenningday());
//                        ree.setProductid(old.getProductid());
//                        ree.setTotalmoney(old.getTotalmoney());
//                        ree.setUserid(old.getUserid());
//                        extraData.add(pos + 1, ree);
//                    }
                    iStatisticalFragment.getReportBacklogSuccess(extraData);
                } else {
                    iStatisticalFragment.getReportBacklogFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iStatisticalFragment.getReportBacklogFail(message);
            }
        }, fromDate, toDate);
    }

}
