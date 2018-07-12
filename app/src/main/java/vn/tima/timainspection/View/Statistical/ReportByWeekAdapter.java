package vn.tima.timainspection.View.Statistical;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ReportByWeekEntity;
import vn.tima.timainspection.R;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class ReportByWeekAdapter extends RecyclerView.Adapter<ReportByWeekAdapter.ViewHolder> {

    private List<ReportByWeekEntity> contractData;
    private Context context;
    private int sumHouse = 0;
    private int sumCompany = 0;
    private int sumMotorbike = 0;
    private int sumMotorbikeHub = 0;

    public void setContractData(List<ReportByWeekEntity> contractData) {
        this.contractData = contractData;
    }

    public ReportByWeekAdapter(Context context, List<ReportByWeekEntity> contractData) {
        this.contractData = contractData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_by_week, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            if (position < contractData.size()) {
                final ReportByWeekEntity reportByWeekEntity = contractData.get(position);
                if (position == 0) {
                    holder.tvDate.setText(reportByWeekEntity.getCreateDateInfo());
                } else {
                    if (contractData.get(position - 1).getCreateDateInfo().equals(reportByWeekEntity.getCreateDateInfo())) {
                        holder.tvDate.setText("");
                    } else {
                        holder.tvDate.setText(reportByWeekEntity.getCreateDateInfo());
                    }
                }
                holder.tvType.setText(reportByWeekEntity.getProductName());
                holder.tvTDNha.setText(String.valueOf(reportByWeekEntity.getNumberHomeEvaluation()));
                holder.tvTDCongTy.setText(String.valueOf(reportByWeekEntity.getNumberCompanyEvaluation()));
                holder.tvTDXe.setText(String.valueOf(reportByWeekEntity.getNumberMotobikeEvaluationTima()));
                holder.tvTDXeHub.setText(String.valueOf(reportByWeekEntity.getNumberMotobikeEvaluationHub()));
                sumHouse += reportByWeekEntity.getNumberHomeEvaluation();
                sumCompany += reportByWeekEntity.getNumberCompanyEvaluation();
                sumMotorbike += reportByWeekEntity.getNumberMotobikeEvaluationTima();
                sumMotorbikeHub += reportByWeekEntity.getNumberMotobikeEvaluationHub();
            } else {
                holder.tvType.setText("Tổng số");
                holder.tvTDNha.setText(String.valueOf(sumHouse));
                holder.tvTDCongTy.setText(String.valueOf(sumCompany));
                holder.tvTDXe.setText(String.valueOf(sumMotorbike));
                holder.tvTDXeHub.setText(String.valueOf(sumMotorbikeHub));
                holder.tvTDNha.setTypeface(null, Typeface.BOLD);
                holder.tvTDCongTy.setTypeface(null, Typeface.BOLD);
                holder.tvTDXe.setTypeface(null, Typeface.BOLD);
                holder.tvTDXeHub.setTypeface(null, Typeface.BOLD);
            }
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#EBEBEB"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return contractData.size() == 0 ? 0 : contractData.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvTDNha)
        TextView tvTDNha;
        @BindView(R.id.tvTDCongTy)
        TextView tvTDCongTy;
        @BindView(R.id.tvTDXe)
        TextView tvTDXe;
        @BindView(R.id.tvTDXeHub)
        TextView tvTDXeHub;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
