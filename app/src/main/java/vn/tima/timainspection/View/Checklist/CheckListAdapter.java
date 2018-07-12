package vn.tima.timainspection.View.Checklist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;

/**
 * Created by tima on 11/25/17.
 */

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.CheckListHolder> {
    ArrayList<CheckList> checkLists = new ArrayList<>();
    Context context;
    GroupchildrenAdapter groupchildrenAdapter;
    public void setCheckLists(ArrayList<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @Override
    public CheckListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checklist, parent, false);
        return new CheckListHolder(view);
    }

    @Override
    public void onBindViewHolder(CheckListHolder holder, int position) {
        CheckList data = checkLists.get(position);
        initGroupChildrenAdapter(holder,data);
        holder.tv_title.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return checkLists.size();
    }

    private void initGroupChildrenAdapter(CheckListHolder holder, CheckList checkList){
        Common.setVerticalRecyclerView(context,holder.rv_lstGroupChildren);
        groupchildrenAdapter = new GroupchildrenAdapter();
        groupchildrenAdapter.setLstGroupChildren((ArrayList<CheckList.Lstgroupchildren>) checkList.getLstgroupchildren());
        holder.rv_lstGroupChildren.setAdapter(groupchildrenAdapter);
    }

    public class CheckListHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.rv_lstGroupChildren)
        RecyclerView rv_lstGroupChildren;
        public CheckListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
