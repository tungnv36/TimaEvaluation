package vn.tima.timainspection.View.Contract;

import java.util.List;

import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;

/**
 * Created by tima on 11/27/17.
 */

public interface IContractActivity {
    void getContractList(List<ContractEntity> extraData);
    void getContractListChoTD(List<WaitTDHSEntity> extraData);
    void getContractListFail(String msg);
    void getContractListChoTDFail(String msg);
    void insertProductAppraiserSuccess(String msg);
    void insertProductAppraiserFail(String msg);
}
