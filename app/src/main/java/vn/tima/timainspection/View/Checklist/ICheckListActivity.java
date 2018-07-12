package vn.tima.timainspection.View.Checklist;

import java.util.List;

import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by anhnh-dev on 28/11/2017.
 */

public interface ICheckListActivity {
    void getCheckList(List<CheckList> extraData);
    void getCheckListListFail(String msg);
    void insertCoordinatorCheckList(String message);
    void insertCoordinatorCheckListFail(String message);
}
