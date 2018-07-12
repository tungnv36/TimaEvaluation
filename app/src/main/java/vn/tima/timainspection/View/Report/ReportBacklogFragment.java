package vn.tima.timainspection.View.Report;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.tima.timainspection.Model.Entity.ReportBacklogEntity;
import vn.tima.timainspection.Presenter.ReportBacklog.ReportBacklogPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Main.MainActivity;

/**
 * Created by tima on 3/13/18.
 */

public class ReportBacklogFragment extends Fragment implements View.OnClickListener, IReportBacklogFragment {

    @BindView(R.id.ivFromDate)
    ImageView ivFromDate;
    @BindView(R.id.tvFromDate)
    TextView tvFromDate;
    @BindView(R.id.ivToDate)
    ImageView ivToDate;
    @BindView(R.id.tvToDate)
    TextView tvToDate;
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.rvHopDong)
    RecyclerView rvHopDong;
    Unbinder unbinder;

    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener fromDate;
    private DatePickerDialog.OnDateSetListener toDate;
    private ProgressDialog progressDialog;
    private ReportBacklogPresenter reportBacklogPresenter;
    private List<ReportBacklogEntity> extraData = new ArrayList<>();
    private ReportBacklogAdapter reportBacklogAdapter;

    public static ReportBacklogFragment newInstance() {
        ReportBacklogFragment fragment = new ReportBacklogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Báo cáo tồn");
        View view = inflater.inflate(R.layout.fragment_report_backlog, container, false);
        unbinder = ButterKnife.bind(this, view);

        reportBacklogPresenter = new ReportBacklogPresenter(this.getActivity(), this);
        progressDialog = new ProgressDialog(this.getActivity());
        progressDialog.setMessage("loading....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);

        tvFromDate.setOnClickListener(this);
        tvToDate.setOnClickListener(this);

        tvFromDate.setText(Common.getToday());
        tvToDate.setText(Common.getToday());

        reportBacklogPresenter.getReportBacklog(tvFromDate.getText().toString(), tvToDate.getText().toString());

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

        return view;
    }

    private void updateLabel(TextView tv) {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv.setText(sdf.format(myCalendar.getTime()));
        progressDialog.show();
        reportBacklogPresenter.getReportBacklog(tvFromDate.getText().toString(), tvToDate.getText().toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvFromDate:
                new DatePickerDialog(ReportBacklogFragment.this.getActivity(), fromDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.tvToDate:
                new DatePickerDialog(ReportBacklogFragment.this.getActivity(), toDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    private void initListReport(){
        reportBacklogAdapter = new ReportBacklogAdapter(this.getActivity(), extraData);
        Common.setVerticalRecyclerView(this.getActivity(), rvHopDong);
        rvHopDong.setAdapter(reportBacklogAdapter);
    }

    @Override
    public void getReportBacklogSuccess(List<ReportBacklogEntity> extraData) {
        this.extraData = extraData;
        reportBacklogAdapter.setContractData(extraData);
        reportBacklogAdapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    @Override
    public void getReportBacklogFailed(String msg) {
        extraData.clear();
        reportBacklogAdapter.setContractData(extraData);
        reportBacklogAdapter.notifyDataSetChanged();
//        Common.showAlert(ReportBacklogFragment.this.getActivity(), msg);
        progressDialog.dismiss();
    }

}
