package vn.tima.timainspection.View.CoordinatorFortransfer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;
import vn.tima.timainspection.Presenter.CoordinatorFortransfer.CoordinatorFortransferPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;

/**
 * Created by tima on 12/1/17.
 */

public class CoordinatorFortransferActivity extends AppCompatActivity implements ICoordinatorFortransferActivity {

    @BindView(R.id.rvThamDinhVien)
    RecyclerView rvThamDinhVien;

    private ProgressDialog progressDoalog;
    private CoordinatorFortransferPresenter coordinatorFortransferPresenter;
    private CoordinatorFortransferAdapter coordinatorFortransferAdapter;
    private List<CoordinatorFortransferEntity> lst;
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_fortransfer);
        ButterKnife.bind(this);
        activity = this;
        progressDoalog = new ProgressDialog(this);
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

        coordinatorFortransferPresenter = new CoordinatorFortransferPresenter(this, this);
        coordinatorFortransferPresenter.getListThamDinh(getIntent().getIntExtra("LoanID", 0));
    }

    @Override
    public void getListCoordinatorFortransfer(List<CoordinatorFortransferEntity> lst) {
        this.lst = lst;
        coordinatorFortransferAdapter = new CoordinatorFortransferAdapter(activity,this.lst, coordinatorFortransferPresenter);
        Common.setVerticalRecyclerView(this, rvThamDinhVien);
        rvThamDinhVien.setAdapter(coordinatorFortransferAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getListCoordinatorFortransferFail(String msg) {
        progressDoalog.dismiss();
    }

    @Override
    public void dieuChuyenSuccess(String msg) {
        Common.showAlert(this, msg);
    }

    @Override
    public void dieuChuyenFail(String msg) {
        Common.showAlert(this, msg);
    }

}
