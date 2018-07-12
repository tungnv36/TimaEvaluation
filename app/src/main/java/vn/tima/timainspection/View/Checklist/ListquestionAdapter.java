package vn.tima.timainspection.View.Checklist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.View.Dialog.EditAnswerDialog;

/**
 * Created by tima on 11/25/17.
 */

public class ListquestionAdapter extends RecyclerView.Adapter<ListquestionAdapter.MyHolder> {
    ArrayList<CheckList.Listquestion> listquestions;
    Context context;
    ListGroupAnswerAdapter listGroupAnswerAdapter;
    public void setListquestion(ArrayList<CheckList.Listquestion> listquestions) {
        this.listquestions = listquestions;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checklist_listquestion, parent, false);
        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CheckList.Listquestion data = listquestions.get(position);
        holder.tv_questionName.setText(data.getQuestionName());
        holder.tv_comment.setText(data.getComment());

        if(data.getQuestionName()!= null && !data.getQuestionName().equals("")){
            holder.tv_questionName.setVisibility(View.VISIBLE);
        }else{
            holder.tv_questionName.setVisibility(View.GONE);
        }

        if(data.getComment()!= null && !data.getComment().equals("")){
            holder.tv_comment.setVisibility(View.VISIBLE);
        }else{
            holder.tv_comment.setVisibility(View.GONE);
        }

        initGroupAnswerAdapter(holder,data);
        setupComment(holder,data,position);
        setupEditQuestion(holder,data,position);
    }

    private void setupComment(final MyHolder holder, CheckList.Listquestion data, final int position) {
        if(data.getIsComment()==1){
            holder.tv_comment.setVisibility(View.VISIBLE);
            holder.tv_comment.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    listquestions.get(position).setComment(holder.tv_comment.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }else{
            holder.tv_comment.setVisibility(View.GONE);
        }
    }
    private void setupEditQuestion(final MyHolder holder, final CheckList.Listquestion data , final int position) {
        if(data.getIsEdit() == 1){
            holder.tv_questionName.setTextColor(context.getResources().getColor(R.color.blue));
            holder.tv_questionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditAnswerDialog dialog = new EditAnswerDialog(context, data.getQuestionName());
                    dialog.showDialog();
                    dialog.setOnSubmitClick(new EditAnswerDialog.OnSubmitClick() {
                        @Override
                        public void onSubmitComment(String answer) {
                            holder.tv_questionName.setText(!answer.equals("")? answer :holder.tv_questionName.getText().toString());
                            listquestions.get(position).setQuestionName(!answer.equals("")? answer :holder.tv_questionName.getText().toString());
                            dialog.dismissDialog();
                        }
                    });
                }
            });
        }else {
            holder.tv_questionName.setTextColor(context.getResources().getColor(R.color.gray4));
        }
    }
    @Override
    public int getItemCount() {
        return listquestions.size();
    }


    private void initGroupAnswerAdapter(MyHolder holder, CheckList.Listquestion data){
        Common.setVerticalRecyclerView(context,holder.rv_lstGroupAnswer);
        listGroupAnswerAdapter = new ListGroupAnswerAdapter();
        listGroupAnswerAdapter.setLstGroupAnswers((ArrayList<CheckList.LstGroupAnswer>) data.getLstGroupAnswer());
        holder.rv_lstGroupAnswer.setAdapter(listGroupAnswerAdapter);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_comment)
        EditText tv_comment;
        @BindView(R.id.tv_questionName)
        TextView tv_questionName;
        @BindView(R.id.rv_lstGroupAnswer)
        RecyclerView rv_lstGroupAnswer;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
