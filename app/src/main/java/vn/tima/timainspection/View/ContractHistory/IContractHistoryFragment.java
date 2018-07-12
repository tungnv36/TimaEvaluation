package vn.tima.timainspection.View.ContractHistory;

import java.util.List;

import vn.tima.timainspection.Model.Entity.CommentEntity;

/**
 * Created by tima on 11/29/17.
 */

public interface IContractHistoryFragment {
    void getCommentSuccess(List<CommentEntity> lst);
    void getCommentFail(String msg);
}
