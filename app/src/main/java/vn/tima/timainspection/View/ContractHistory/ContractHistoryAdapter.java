package vn.tima.timainspection.View.ContractHistory;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CommentEntity;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;

/**
 * Created by tima on 11/25/17.
 */

public class ContractHistoryAdapter extends RecyclerView.Adapter<ContractHistoryAdapter.ContractViewHolder> {

    private List<CommentEntity> contractData;

    public ContractHistoryAdapter(List<CommentEntity> contractData) {
        this.contractData = contractData;
    }

    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_comment_history, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContractViewHolder holder, int position) {
        try {
            final CommentEntity commentEntity = contractData.get(position);
            holder.tvNgay.setText(Common.formatCreateDate(commentEntity.getCreateDate()));
            holder.tvNguoiThaoTac.setText(commentEntity.getFullName());
            holder.tvComment.setText(Html.fromHtml(commentEntity.getComment()));

            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return contractData.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNgay)
        TextView tvNgay;
        @BindView(R.id.tvNguoiThaoTac)
        TextView tvNguoiThaoTac;
        @BindView(R.id.tvComment)
        TextView tvComment;

        public ContractViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
