package vn.tima.timainspection.View.Checklist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.Presenter.CheckList.CheckListPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.Dialog.LoadingDialog;

/**
 * Created by anhnh-dev on 28/11/2017.
 */

public class CheckListActivity extends AppCompatActivity implements View.OnClickListener, ICheckListActivity {
    public static final String TAG = CheckListActivity.class.getSimpleName();
    @BindView(R.id.rv_checklist)
    RecyclerView rv_checklist;
    @BindView(R.id.btn_click)
    Button btn_click;

    ArrayList<CheckList> checkLists = new ArrayList<>();
    CheckListAdapter adapter;
    CheckListPresenter checkListPresenter;
    int LoanID = 0;
    LoadingDialog loadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Checklist");
        initListCheckList();
        checkListPresenter = new CheckListPresenter(this, this);
        LoanID = getIntent().getIntExtra("LoanID",0);
        checkListPresenter.getCheckList(LoanID);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        loadingDialog = new LoadingDialog(CheckListActivity.this,"Đang lưu checklist");
        loadingDialog.showDialog();
        Log.d(TAG, "onClick: "+ checkLists);
        Gson gson = new Gson();
        String checkListJson = gson.toJson(checkLists);
        checkListPresenter.insertCoordinatorCheckList(LoanID,checkListJson);

        return super.onOptionsItemSelected(item);
    }


    private void initListCheckList(){
        Common.setVerticalRecyclerView(this,rv_checklist);
        rv_checklist.setNestedScrollingEnabled(false);
        adapter = new CheckListAdapter();
        adapter.setCheckLists(checkLists);
        rv_checklist.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getCheckList(List<CheckList> extraData) {
        checkLists = (ArrayList<CheckList>) extraData;
        notifyDataSetChange();
    }

    @Override
    public void getCheckListListFail(String msg) {

    }

    @Override
    public void insertCoordinatorCheckList(String message) {
        loadingDialog.showDialog();
        DialogUtils.showAlertDialog(CheckListActivity.this, "Thông báo", message, new DialogUtils.OnClickListener() {
            @Override
            public void onClickSuccess() {
                CheckListActivity.this.finish();
            }
        });
    }

    @Override
    public void insertCoordinatorCheckListFail(String message) {
        loadingDialog.showDialog();
        DialogUtils.showAlertDialog(CheckListActivity.this,"Thông báo", message);
    }

    private void notifyDataSetChange(){
        adapter.setCheckLists(checkLists);
        adapter.notifyDataSetChanged();
    }
}
