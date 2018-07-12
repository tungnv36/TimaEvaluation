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

public class GroupchildrenAdapter extends RecyclerView.Adapter<GroupchildrenAdapter.MyHolder> {
    ArrayList<CheckList.Lstgroupchildren> lstGroupChildren;
    Context context;
    ListquestionAdapter listquestionAdapter;
    public void setLstGroupChildren(ArrayList<CheckList.Lstgroupchildren> lstGroupChildren) {
        this.lstGroupChildren = lstGroupChildren;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checklist_lstgroupchildren, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CheckList.Lstgroupchildren data = lstGroupChildren.get(position);
        holder.tv_note.setText(data.getNote());
        holder.tv_groupchildrenName.setText(data.getGroupchildrenName());

        if(data.getNote()!= null && !data.getNote().equals("")){
            holder.tv_note.setVisibility(View.VISIBLE);
        }else{
            holder.tv_note.setVisibility(View.GONE);
        }

        if(data.getGroupchildrenName()!= null && !data.getGroupchildrenName().equals("")){
            holder.tv_groupchildrenName.setVisibility(View.VISIBLE);
        }else{
            holder.tv_groupchildrenName.setVisibility(View.GONE);
        }

        setShowHideGroup(data,holder);

        initQuestionAdapter(holder,data);
    }

    private void setShowHideGroup(CheckList.Lstgroupchildren data, final MyHolder holder) {
        if(data.isRequire()){// bắt buộc nhập check
            holder.tv_showhide.setVisibility(View.GONE);
            holder.rv_group_child_checklist.setVisibility(View.VISIBLE);
        }else{// có thể không cần nhập
            holder.tv_showhide.setVisibility(View.VISIBLE);
            holder.rv_group_child_checklist.setVisibility(View.GONE);
            holder.tv_showhide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.rv_group_child_checklist.getVisibility() == View.GONE){
                        holder.tv_showhide.setText("Ẩn");
                        holder.rv_group_child_checklist.setVisibility(View.VISIBLE);
                    }else{
                        holder.tv_showhide.setText("Hiện");
                        holder.rv_group_child_checklist.setVisibility(View.GONE);

                    }
                }
            });
        }
    }

    private void initQuestionAdapter(MyHolder holder, CheckList.Lstgroupchildren data){
        Common.setVerticalRecyclerView(context,holder.rv_group_child_checklist);
        listquestionAdapter = new ListquestionAdapter();
        listquestionAdapter.setListquestion((ArrayList<CheckList.Listquestion>) data.getListquestion());
        holder.rv_group_child_checklist.setAdapter(listquestionAdapter);
    }

    @Override
    public int getItemCount() {
        return lstGroupChildren.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_showhide)
        TextView tv_showhide;
        @BindView(R.id.tv_note)
        TextView tv_note;
        @BindView(R.id.tv_groupchildrenName)
        TextView tv_groupchildrenName;
        @BindView(R.id.rv_group_child_checklist)
        RecyclerView rv_group_child_checklist;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
