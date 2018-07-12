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

public class ListGroupAnswerAdapter extends RecyclerView.Adapter<ListGroupAnswerAdapter.MyHolder> {
    ArrayList<CheckList.LstGroupAnswer> lstGroupAnswers;
    Context context;
    ListAnswerAdapter listAnswerAdapter;

    public void setLstGroupAnswers(ArrayList<CheckList.LstGroupAnswer> lstGroupAnswers) {
        this.lstGroupAnswers = lstGroupAnswers;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checklist_lstgroup_answer, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CheckList.LstGroupAnswer data = lstGroupAnswers.get(position);
        holder.tv_titleAnswer.setText(data.getTitleAnswer());
        if(data.getTitleAnswer()!= null && !data.getTitleAnswer().equals("")){
            holder.tv_titleAnswer.setVisibility(View.VISIBLE);
        }else{
            holder.tv_titleAnswer.setVisibility(View.GONE);
        }


        initGroupChildrenAdapter(holder,data, position);
    }

    @Override
    public int getItemCount() {
        return lstGroupAnswers.size();
    }


    private void initGroupChildrenAdapter(MyHolder holder, final CheckList.LstGroupAnswer data, final int position){
        Common.setGridLayoutRecyclerView(context,holder.rv_lstAnswer,3);
        listAnswerAdapter = new ListAnswerAdapter();
        listAnswerAdapter.setLstAnswers((ArrayList<CheckList.LstAnswer>) data.getLstAnswer());
        holder.rv_lstAnswer.setAdapter(listAnswerAdapter);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_titleAnswer)
        TextView tv_titleAnswer;
        @BindView(R.id.rv_lstAnswer)
        RecyclerView rv_lstAnswer;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
