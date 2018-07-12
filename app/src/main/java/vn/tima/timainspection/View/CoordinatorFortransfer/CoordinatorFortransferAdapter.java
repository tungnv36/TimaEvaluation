package vn.tima.timainspection.View.CoordinatorFortransfer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CoordinatorFortransferEntity;
import vn.tima.timainspection.Presenter.CoordinatorFortransfer.CoordinatorFortransferPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;

/**
 * Created by tima on 11/25/17.
 */

public class CoordinatorFortransferAdapter extends RecyclerView.Adapter<CoordinatorFortransferAdapter.ContractViewHolder> {

    private List<CoordinatorFortransferEntity> contractData;
    private CoordinatorFortransferPresenter coordinatorFortransferPresenter;
    Activity context;
    public CoordinatorFortransferAdapter(Activity context,List<CoordinatorFortransferEntity> contractData, CoordinatorFortransferPresenter coordinatorFortransferPresenter) {
        this.contractData = contractData;
        this.coordinatorFortransferPresenter = coordinatorFortransferPresenter;
        this.context = context;
    }

    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_coordinator_fortransfer, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContractViewHolder holder, int position) {
        try {
            final CoordinatorFortransferEntity coordinatorFortransferEntity = contractData.get(position);
            holder.tvThamDinh.setText(coordinatorFortransferEntity.getFullName());

            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

            holder.ibChuyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    DialogUtils.showExitDialog2(context, "Hồ sơ này đã hoàn thành chưa?", new DialogUtils.OnClickListener2() {
//                        @Override
//                        public void onClickSuccess() {
//                            coordinatorFortransferPresenter.chuyenThamDinh(coordinatorFortransferEntity.LoanCreditId,
//                                    coordinatorFortransferEntity.getCoordinatorID(), coordinatorFortransferEntity.getFullName(),1);// Hồ sơ đã xong
//                        }
//
//                        @Override
//                        public void onClickCancel() {
//                            coordinatorFortransferPresenter.chuyenThamDinh(coordinatorFortransferEntity.LoanCreditId,
//                                    coordinatorFortransferEntity.getCoordinatorID(), coordinatorFortransferEntity.getFullName(),0);// Hồ sơ chưa xong
//                        }
//                    });
                    coordinatorFortransferPresenter.chuyenThamDinh(coordinatorFortransferEntity.LoanCreditId,
                                    coordinatorFortransferEntity.getCoordinatorID(), coordinatorFortransferEntity.getFullName(),1);// Hồ sơ đã xong
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return contractData.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvThamDinh)
        TextView tvThamDinh;
        @BindView(R.id.ibChuyen)
        ImageButton ibChuyen;

        public ContractViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
