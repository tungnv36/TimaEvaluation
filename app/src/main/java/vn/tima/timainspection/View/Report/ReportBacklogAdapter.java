package vn.tima.timainspection.View.Report;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ReportBacklogEntity;
import vn.tima.timainspection.Presenter.Contract.ContractPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Dialog.BacklogDialog;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class ReportBacklogAdapter extends RecyclerView.Adapter<ReportBacklogAdapter.ReportViewHolder> {

    private List<ReportBacklogEntity> contractData;

    private Context context;

    public void setContractData(List<ReportBacklogEntity> contractData) {
        this.contractData = contractData;
    }

    public ReportBacklogAdapter(Context context, List<ReportBacklogEntity> contractData) {
        this.contractData = contractData;
        this.context = context;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_backlog, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReportViewHolder holder, int position) {
        try {
            final ReportBacklogEntity reportBacklogEntity = contractData.get(position);
            holder.tvSTT.setText(String.valueOf(position + 1));
            holder.tvHoTen.setText("Mã HĐ: " + reportBacklogEntity.getLoancreditid());
            holder.tvQuanHuyen.setText("Tên KH: " + reportBacklogEntity.getFullname());
            holder.tvNgayThang.setText("Quận/Huyện: " + reportBacklogEntity.getDistrictname());
            holder.tvTongTien.setText(Common.formatMoney(reportBacklogEntity.getTotalmoney()));
//            holder.tvTrangThai.setText(reportBacklogEntity.getOpenningnote());

            if(reportBacklogEntity.getType() == 1) {
                holder.btNha.setText("Xử lý tồn (Nhà) ▾");
            } else {
                holder.btNha.setText("Xử lý tồn (Cty) ▾");
            }

//            if(reportBacklogEntity.getIscongty()) {
//                holder.btCty.setVisibility(View.VISIBLE);
//            } else {
//                holder.btCty.setVisibility(View.GONE);
//            }
//            holder.btCty.setVisibility(View.VISIBLE);

            holder.btNha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BacklogDialog backlogDialog = new BacklogDialog(context);
                    backlogDialog.setLoanId(reportBacklogEntity.getLoancreditid());
                    backlogDialog.setType(reportBacklogEntity.getType());
                    backlogDialog.setProductID(reportBacklogEntity.getProductid());
                    backlogDialog.showDialog();
                }
            });

//            holder.btCty.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    BacklogDialog backlogDialog = new BacklogDialog(context);
//                    backlogDialog.setLoanId(reportBacklogEntity.getLoancreditid());
//                    backlogDialog.setType(2);
//                    backlogDialog.setProductID(reportBacklogEntity.getProductid());
//                    backlogDialog.showDialog();
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return contractData.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvSTT)
        TextView tvSTT;
        @BindView(R.id.tvHoTen)
        TextView tvHoTen;
        @BindView(R.id.tvQuanHuyen)
        TextView tvQuanHuyen;
        @BindView(R.id.tvNgayThang)
        TextView tvNgayThang;
        @BindView(R.id.btNha)
        Button btNha;
//        @BindView(R.id.btCty)
//        Button btCty;
        @BindView(R.id.tvTongTien)
        TextView tvTongTien;
        @BindView(R.id.tvTrangThai)
        TextView tvTrangThai;

        public ReportViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}
