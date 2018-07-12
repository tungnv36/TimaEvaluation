package vn.tima.timainspection.View.Statistical;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import info.hoang8f.android.segmented.SegmentedGroup;
import vn.tima.timainspection.Model.Entity.RBacklogEntity;
import vn.tima.timainspection.Model.Entity.ReportByWeekEntity;
import vn.tima.timainspection.Model.Entity.ReportEvaluationEntity;
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;
import vn.tima.timainspection.Presenter.Report.ReportEvaluationPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Main.MainActivity;

/**
 * Created by tima on 11/25/17.
 */

public class StatisticalFragment extends Fragment implements IStatisticalFragment, CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    Unbinder unbinder;
    @BindView(R.id.rbBaoCaoNgay)
    RadioButton rbBaoCaoNgay;
    @BindView(R.id.rbBaoCaoTuan)
    RadioButton rbBaoCaoTuan;
    @BindView(R.id.rbBaoCaoThang)
    RadioButton rbBaoCaoThang;
    @BindView(R.id.segment)
    SegmentedGroup segment;
    @BindView(R.id.ivFromDate)
    ImageView ivFromDate;
    @BindView(R.id.tvFromDate)
    TextView tvFromDate;
    @BindView(R.id.ivToDate)
    ImageView ivToDate;
    @BindView(R.id.tvToDate)
    TextView tvToDate;
    @BindView(R.id.llDate)
    LinearLayout llDate;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvTDNha)
    TextView tvTDNha;
    @BindView(R.id.tvTDCongTy)
    TextView tvTDCongTy;
    @BindView(R.id.tvTDXe)
    TextView tvTDXe;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.rvReport)
    RecyclerView rvReport;
    @BindView(R.id.tvType2)
    TextView tvType2;
    @BindView(R.id.tvTDTDChuyenTDHS2)
    TextView tvTDTDChuyenTDHS2;
    @BindView(R.id.tvDaiLyDuyet)
    TextView tvDaiLyDuyet;
    @BindView(R.id.llTitle2)
    LinearLayout llTitle2;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvType3)
    TextView tvType3;
    @BindView(R.id.tvNha)
    TextView tvNha;
    @BindView(R.id.tvCongTy)
    TextView tvCongTy;
    @BindView(R.id.tvXe)
    TextView tvXe;
    @BindView(R.id.llTitle3)
    LinearLayout llTitle3;
    @BindView(R.id.rbBaoCaoTon)
    RadioButton rbBaoCaoTon;
    @BindView(R.id.tvHSNhan)
    TextView tvHSNhan;
    @BindView(R.id.tvHSXuLy)
    TextView tvHSXuLy;
    @BindView(R.id.tvHSTon)
    TextView tvHSTon;
    @BindView(R.id.llTitle4)
    LinearLayout llTitle4;
    private ReportEvaluationPresenter reportEvaluationPresenter;
    private int year, month, day;
    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener fromDate;
    private DatePickerDialog.OnDateSetListener toDate;
    private ArrayList<ReportEvaluationEntity> list_report = new ArrayList<>();
    private ArrayList<ReportTDHS2AndDaiLyEntity> list_report_tdtd_chuyen_tdhs2 = new ArrayList<>();
    private ArrayList<ReportByWeekEntity> list_report_by_week = new ArrayList<>();
    private ArrayList<RBacklogEntity> list_report_backlog = new ArrayList<>();
    private ReportByDayAdapter reportByDayAdapter;
    private ReportByWeekAdapter reportByWeekAdapter;
    private ReportTDTDChuyenTDHS2Adapter reportTDTDChuyenTDHS2Adapter;
    private ReportBacklogAdapter reportBacklogAdapter;
    private ProgressDialog progressDoalog;

    public static StatisticalFragment newInstance() {
        StatisticalFragment fragment = new StatisticalFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Thống kê");
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        reportEvaluationPresenter = new ReportEvaluationPresenter(this.getActivity(), this);
        unbinder = ButterKnife.bind(this, view);
//        rbBaoCaoNgay.setOnCheckedChangeListener(this);
//        rbBaoCaoTuan.setOnCheckedChangeListener(this);
//        rbBaoCaoThang.setOnCheckedChangeListener(this);
        rbBaoCaoNgay.setOnClickListener(this);
        rbBaoCaoTuan.setOnClickListener(this);
        rbBaoCaoThang.setOnClickListener(this);
        rbBaoCaoTon.setOnClickListener(this);

        tvFromDate.setOnClickListener(this);
        tvToDate.setOnClickListener(this);

        progressDoalog = new ProgressDialog(this.getActivity());
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);

//        tvFromDate.setText("20-01-2017");
//        tvToDate.setText("20-01-2019");

        tvFromDate.setText(Common.getFirstDayOfWeek());
        tvToDate.setText(Common.getToday());

        fromDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(tvFromDate);
            }

        };

        toDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(tvToDate);
            }

        };

        initListReport();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE, ActionBar.DISPLAY_SHOW_TITLE);
        return view;
    }

    private void changeTab(int id) {
        progressDoalog.show();
        switch (id) {
            case R.id.rbBaoCaoNgay:
                llTitle.setVisibility(View.VISIBLE);
                llTitle2.setVisibility(View.GONE);
                llTitle3.setVisibility(View.GONE);
                llTitle4.setVisibility(View.GONE);
                list_report_by_week.clear();
                list_report_tdtd_chuyen_tdhs2.clear();
                list_report_backlog.clear();
                reportEvaluationPresenter.getReportEvaluation(tvFromDate.getText().toString(), tvToDate.getText().toString());
                break;
            case R.id.rbBaoCaoTuan:
                llTitle.setVisibility(View.GONE);
                llTitle2.setVisibility(View.GONE);
                llTitle3.setVisibility(View.VISIBLE);
                llTitle4.setVisibility(View.GONE);
                list_report.clear();
                list_report_tdtd_chuyen_tdhs2.clear();
                list_report_backlog.clear();
                reportEvaluationPresenter.getReportByWeek(tvFromDate.getText().toString(), tvToDate.getText().toString());
                break;
            case R.id.rbBaoCaoThang:
                llTitle.setVisibility(View.GONE);
                llTitle2.setVisibility(View.VISIBLE);
                llTitle3.setVisibility(View.GONE);
                llTitle4.setVisibility(View.GONE);
                list_report.clear();
                list_report_by_week.clear();
                list_report_backlog.clear();
                reportEvaluationPresenter.getReportTDHS2AndDaiLy(tvFromDate.getText().toString(), tvToDate.getText().toString());
                break;
            case R.id.rbBaoCaoTon:
                llTitle.setVisibility(View.GONE);
                llTitle2.setVisibility(View.GONE);
                llTitle3.setVisibility(View.GONE);
                llTitle4.setVisibility(View.VISIBLE);
                list_report.clear();
                list_report_by_week.clear();
                list_report_tdtd_chuyen_tdhs2.clear();
                reportEvaluationPresenter.getReportBacklog(tvFromDate.getText().toString(), tvToDate.getText().toString());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDoalog.show();
        reportEvaluationPresenter.getReportEvaluation(tvFromDate.getText().toString(), tvToDate.getText().toString());
    }

    private void initListReport() {
        reportByDayAdapter = new ReportByDayAdapter(this.getActivity(), this.list_report);
        Common.setVerticalRecyclerView(this.getActivity(), rvReport);
        rvReport.setAdapter(reportByDayAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        changeTab(compoundButton.getId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvFromDate:
                new DatePickerDialog(StatisticalFragment.this.getActivity(), fromDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.tvToDate:
                new DatePickerDialog(StatisticalFragment.this.getActivity(), toDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.rbBaoCaoNgay:
                changeTab(view.getId());
                break;
            case R.id.rbBaoCaoTuan:
                changeTab(view.getId());
                break;
            case R.id.rbBaoCaoThang:
                changeTab(view.getId());
                break;
            case R.id.rbBaoCaoTon:
                changeTab(view.getId());
                break;
        }
    }

    private void updateLabel(TextView tv) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv.setText(sdf.format(myCalendar.getTime()));
        progressDoalog.show();
        if (rbBaoCaoNgay.isChecked()) {
            reportEvaluationPresenter.getReportEvaluation(tvFromDate.getText().toString(), tvToDate.getText().toString());
        } else if (rbBaoCaoTuan.isChecked()) {
            reportEvaluationPresenter.getReportByWeek(tvFromDate.getText().toString(), tvToDate.getText().toString());
        } else  if (rbBaoCaoThang.isChecked()) {
            reportEvaluationPresenter.getReportTDHS2AndDaiLy(tvFromDate.getText().toString(), tvToDate.getText().toString());
        } else {
            reportEvaluationPresenter.getReportBacklog(tvFromDate.getText().toString(), tvToDate.getText().toString());
        }
    }

    @Override
    public void getReportEvaluationSuccess(ArrayList<ReportEvaluationEntity> list_report) {
        this.list_report = list_report;
        reportByDayAdapter = new ReportByDayAdapter(this.getActivity(), list_report);
        rvReport.setAdapter(reportByDayAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportEvaluationFail(String msg) {
        list_report.clear();
        reportByDayAdapter = new ReportByDayAdapter(this.getActivity(), list_report);
        rvReport.setAdapter(reportByDayAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportTDTDChuyenTDHS2Success(ArrayList<ReportTDHS2AndDaiLyEntity> list_report) {
        list_report_tdtd_chuyen_tdhs2 = list_report;
        reportTDTDChuyenTDHS2Adapter = new ReportTDTDChuyenTDHS2Adapter(this.getActivity(), list_report);
        rvReport.setAdapter(reportTDTDChuyenTDHS2Adapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportTDTDChuyenTDHS2Fail(String msg) {
        list_report_tdtd_chuyen_tdhs2.clear();
        reportTDTDChuyenTDHS2Adapter = new ReportTDTDChuyenTDHS2Adapter(this.getActivity(), list_report_tdtd_chuyen_tdhs2);
        rvReport.setAdapter(reportTDTDChuyenTDHS2Adapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportLastWeekSuccess(ArrayList<ReportByWeekEntity> list_report) {
        list_report_by_week = list_report;
        reportByWeekAdapter = new ReportByWeekAdapter(this.getActivity(), list_report);
        rvReport.setAdapter(reportByWeekAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportLastWeekFail(String msg) {
        list_report_by_week.clear();
        reportByWeekAdapter = new ReportByWeekAdapter(this.getActivity(), list_report_by_week);
        rvReport.setAdapter(reportByWeekAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportBacklogSuccess(RBacklogEntity list_report) {
        list_report_backlog.clear();
        list_report_backlog.add(list_report);
        reportBacklogAdapter = new ReportBacklogAdapter(this.getActivity(), list_report_backlog);
        rvReport.setAdapter(reportBacklogAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getReportBacklogFail(String msg) {
        list_report_backlog.clear();
        reportBacklogAdapter = new ReportBacklogAdapter(this.getActivity(), list_report_backlog);
        rvReport.setAdapter(reportBacklogAdapter);
        progressDoalog.dismiss();
    }

}
