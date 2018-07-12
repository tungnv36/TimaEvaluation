package vn.tima.timainspection.View.CoordinatorFortransfer;

import java.util.List;

import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;

/**
 * Created by tima on 12/1/17.
 */

public interface ICoordinatorFortransferActivity {
    void getListCoordinatorFortransfer(List<CoordinatorFortransferEntity> lst);
    void getListCoordinatorFortransferFail(String msg);
    void dieuChuyenSuccess(String msg);
    void dieuChuyenFail(String msg);
}
