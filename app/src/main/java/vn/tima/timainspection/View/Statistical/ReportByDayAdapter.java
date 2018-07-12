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
import vn.tima.timainspection.Model.Entity.ReportEvaluationEntity;
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;
import vn.tima.timainspection.R;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class ReportByDayAdapter extends RecyclerView.Adapter<ReportByDayAdapter.ViewHolder> {

    private List<ReportEvaluationEntity> contractData;
    private List<ReportTDHS2AndDaiLyEntity> contractDataTDTDChuyenTDHS2;
    private Context context;
    private int sumHouse = 0;
    private int sumCompany = 0;
    private int sumMotorbike = 0;
    private int sumHub= 0;

    public void setContractData(List<ReportEvaluationEntity> contractData) {
        this.contractData = contractData;
    }

    public ReportByDayAdapter(Context context, List<ReportEvaluationEntity> contractData) {
        this.contractData = contractData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_by_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            if (position < contractData.size()) {
                final ReportEvaluationEntity reportEvaluationEntity = contractData.get(position);
                holder.tvType.setText(reportEvaluationEntity.getProductName());
                holder.tvTDNha.setText(String.valueOf(reportEvaluationEntity.getNumberHomeEvaluation()));
                holder.tvTDCongTy.setText(String.valueOf(reportEvaluationEntity.getNumberCompanyEvaluation()));
                holder.tvTDXe.setText(String.valueOf(reportEvaluationEntity.getNumberMotobikeEvaluationTima()));
                holder.tvTDXeHub.setText(String.valueOf(reportEvaluationEntity.getNumberMotobikeEvaluationHub()));
                sumHouse += reportEvaluationEntity.getNumberHomeEvaluation();
                sumCompany += reportEvaluationEntity.getNumberCompanyEvaluation();
                sumMotorbike += reportEvaluationEntity.getNumberMotobikeEvaluationTima();
                sumHub += reportEvaluationEntity.getNumberMotobikeEvaluationHub();
            } else {
                holder.tvType.setText("Tổng số");
                holder.tvTDNha.setText(String.valueOf(sumHouse));
                holder.tvTDCongTy.setText(String.valueOf(sumCompany));
                holder.tvTDXe.setText(String.valueOf(sumMotorbike));
                holder.tvTDXeHub.setText(String.valueOf(sumHub));
                holder.tvType.setTypeface(null, Typeface.BOLD);
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
