package vn.tima.timainspection.View.ContractHistory;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.tima.timainspection.Model.Entity.CommentEntity;
import vn.tima.timainspection.Presenter.Comment.CommentPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;

/**
 * Created by tima on 11/29/17.
 */

@SuppressLint("ValidFragment")
public class ContractHistoryFragment extends Fragment implements IContractHistoryFragment {

    @BindView(R.id.rvComment)
    RecyclerView rvComment;
    Unbinder unbinder;

    private ProgressDialog progressDoalog;
    private CommentPresenter commentPresenter;
    private int loanID;
    private ContractHistoryAdapter contractHistoryAdapter;
    private List<CommentEntity> lst;

    private static final String TAG = "ContractDetailFragment";

    public ContractHistoryFragment(int loanID) {
        this.loanID = loanID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contract_history, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDoalog = new ProgressDialog(this.getActivity());
        progressDoalog.setMessage("loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.setCancelable(false);
        progressDoalog.show();

        commentPresenter = new CommentPresenter(this.getActivity(), this);
        commentPresenter.getComment(loanID);
    }

    @Override
    public void getCommentSuccess(List<CommentEntity> lst) {
        this.lst = lst;
        contractHistoryAdapter = new ContractHistoryAdapter(this.lst);
        Common.setVerticalRecyclerView(this.getActivity(), rvComment);
        rvComment.setAdapter(contractHistoryAdapter);
        progressDoalog.dismiss();
    }

    @Override
    public void getCommentFail(String msg) {
        Common.showAlert(this.getActivity(), msg);
        progressDoalog.dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
