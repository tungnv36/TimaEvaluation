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
import vn.tima.timainspection.Model.Entity.ReportTDHS2AndDaiLyEntity;
import vn.tima.timainspection.R;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class ReportTDTDChuyenTDHS2Adapter extends RecyclerView.Adapter<ReportTDTDChuyenTDHS2Adapter.ViewHolder> {

    private List<ReportTDHS2AndDaiLyEntity> contractData;
    private Context context;
    private int sumTDTDChuyenTDHS2 = 0;
    private int sumDLDuyet = 0;

    public void setContractData(List<ReportTDHS2AndDaiLyEntity> contractData) {
        this.contractData = contractData;
    }

    public ReportTDTDChuyenTDHS2Adapter(Context context, List<ReportTDHS2AndDaiLyEntity> contractData) {
        this.contractData = contractData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_tdtd_chuyen_tdhs2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            if(position < contractData.size()) {
                final ReportTDHS2AndDaiLyEntity reportTDHS2AndDaiLyEntity = contractData.get(position);
                holder.tvType.setText(reportTDHS2AndDaiLyEntity.getProductName());
                holder.tvTDTDChuyenTDHS2.setText(String.valueOf(reportTDHS2AndDaiLyEntity.getNumberHomeEvaluation()));
                holder.tvDaiLyDuyet.setText(String.valueOf(reportTDHS2AndDaiLyEntity.getNumberCompanyEvaluation()));
                sumTDTDChuyenTDHS2 += reportTDHS2AndDaiLyEntity.getNumberHomeEvaluation();
                sumDLDuyet += reportTDHS2AndDaiLyEntity.getNumberCompanyEvaluation();
            } else {
                holder.tvType.setText("Tổng số");
                holder.tvTDTDChuyenTDHS2.setText(String.valueOf(sumTDTDChuyenTDHS2));
                holder.tvDaiLyDuyet.setText(String.valueOf(sumDLDuyet));
                holder.tvType.setTypeface(null, Typeface.BOLD);
                holder.tvTDTDChuyenTDHS2.setTypeface(null, Typeface.BOLD);
                holder.tvDaiLyDuyet.setTypeface(null, Typeface.BOLD);
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
        return contractData.size()==0?0:contractData.size() + 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvType)
        TextView tvType;
        @BindView(R.id.tvTDTDChuyenTDHS2)
        TextView tvTDTDChuyenTDHS2;
        @BindView(R.id.tvDaiLyDuyet)
        TextView tvDaiLyDuyet;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
