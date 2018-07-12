package vn.tima.timainspection.View.Statistical;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.RBacklogEntity;
import vn.tima.timainspection.R;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class ReportBacklogAdapter extends RecyclerView.Adapter<ReportBacklogAdapter.ViewHolder> {

    private ArrayList<RBacklogEntity> contractData;
    private Context context;

    public void setContractData(ArrayList<RBacklogEntity> contractData) {
        this.contractData = contractData;
    }

    public ReportBacklogAdapter(Context context, ArrayList<RBacklogEntity> contractData) {
        this.contractData = contractData;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_report_backlog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        try {
            final RBacklogEntity rBacklogEntity = contractData.get(position);
            holder.tvHSNhan.setText(String.valueOf(rBacklogEntity.getTonghosonhan()));
            holder.tvHSTon.setText(String.valueOf(rBacklogEntity.getTonghosoton()));
            holder.tvHSXuLy.setText(String.valueOf(rBacklogEntity.getTonghosoxuly()));
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
        return contractData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvHSNhan)
        TextView tvHSNhan;
        @BindView(R.id.tvHSXuLy)
        TextView tvHSXuLy;
        @BindView(R.id.tvHSTon)
        TextView tvHSTon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
