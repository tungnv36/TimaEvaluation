package vn.tima.timainspection.View.ContractDetail;

import vn.tima.timainspection.Model.Entity.ContractDetailEntity;

/**
 * Created by tima on 11/29/17.
 */

public interface IContractDetailFragment {
    void getContractDetail(ContractDetailEntity contractDetailEntity);
    void getContractDetailFail(String msg);
}
