package vn.tima.timainspection.Presenter.Comment;

import android.content.Context;

import java.util.List;

import vn.tima.timainspection.Model.Comment.CommentRequest;
import vn.tima.timainspection.Model.Comment.LocationRequest;
import vn.tima.timainspection.Model.Entity.CommentEntity;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.View.Comment.ICommentFragment;
import vn.tima.timainspection.View.ContractHistory.IContractHistoryFragment;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by tima on 11/30/17.
 */

public class CommentPresenter implements ICommentPresenter {

    private ICommentFragment iCommentFragment;
    private CommentRequest commentRequest;
    private LocationRequest locationRequest;
    private IContractHistoryFragment iContractHistoryFragment;

    public CommentPresenter(Context context, ICommentFragment iCommentFragment) {
        this.iCommentFragment = iCommentFragment;
        commentRequest = CommentRequest.getInstance(context);
        locationRequest = LocationRequest.getInstance(context);
    }

    public CommentPresenter(Context context, IContractHistoryFragment iContractHistoryFragment) {
        this.iContractHistoryFragment = iContractHistoryFragment;
        commentRequest = CommentRequest.getInstance(context);
    }

    @Override
    public void addComment(int loanCreditId, String comment, String sDevice, String sLat, String sLong, String sAddress, String sCity) {
        commentRequest.addComment(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    iCommentFragment.addCommentSuccess(rs);
                } else {
                    iCommentFragment.addCommentFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCommentFragment.addCommentFail(message);
            }
        }, loanCreditId, comment, sDevice, sLat, sLong, sAddress, sCity);
    }

    @Override
    public void putLacation(String sDevice, String sLat, String sLong, String sAddress, String sCity, int LoanCreditId) {
        locationRequest.putLocation(new OnResponse<String, Boolean>() {
            @Override
            public void onResponseSuccess(String tag, String rs, Boolean extraData) {
                if(extraData) {
                    iCommentFragment.addLocationSuccess(rs);
                } else {
                    iCommentFragment.addLocationFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iCommentFragment.addLocationFail(message);
            }
        }, sDevice, sLat, sLong, sAddress, sCity, LoanCreditId);
    }

    @Override
    public void getComment(int loanCreditId) {
        commentRequest.getListComment(new OnResponse<String, List<CommentEntity>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<CommentEntity> extraData) {
                if(extraData != null) {
                    iContractHistoryFragment.getCommentSuccess(extraData);
                } else {
                    iContractHistoryFragment.getCommentFail(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
                iContractHistoryFragment.getCommentFail(message);
            }
        }, loanCreditId);
    }

}
