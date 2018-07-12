package vn.tima.timainspection.View.Checklist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.CheckList;
import vn.tima.timainspection.R;
import vn.tima.timainspection.View.Dialog.EditAnswerDialog;

/**
 * Created by tima on 11/25/17.
 */

public class ListAnswerAdapter extends RecyclerView.Adapter<ListAnswerAdapter.MyHolder> {
    ArrayList<CheckList.LstAnswer> lstAnswers;
    Context context;
    public int mSelectedItem = -1;
    boolean isClickCheckbox = false;
    interface IChecked{
        void onCheck(int position, boolean isCheck);
    }

    IChecked checked ;

    public void setChecked(IChecked checked) {
        this.checked = checked;
    }

    public void setLstAnswers(ArrayList<CheckList.LstAnswer> lstAnswers) {
        this.lstAnswers = lstAnswers;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checklist_lstanswer, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final CheckList.LstAnswer data = lstAnswers.get(position);
        holder.cb_AnswerName.setText(data.getAnswerName());

        if(isClickCheckbox){
            holder.cb_AnswerName.setChecked(position == mSelectedItem);
        }else{
            holder.cb_AnswerName.setChecked(data.getIsCorrect() == 1? true : false);
        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClickCheckbox = true;
                mSelectedItem = holder.getAdapterPosition();
                notifyItemRangeChanged(0, lstAnswers.size());

                lstAnswers.get(position).setIsCorrect(holder.cb_AnswerName.isChecked()? 1: 0);
                for (int i = 0; i < lstAnswers.size(); i++) {
                    if(i!=position){
                        lstAnswers.get(i).setIsCorrect(holder.cb_AnswerName.isChecked()? 0: 1);
                    }
                }
            }
        };
        holder.cb_AnswerName.setOnClickListener(clickListener);

//        holder.cb_AnswerName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });
        setupEditAnswer(holder, data, position);
    }

    private void setupEditAnswer(final MyHolder holder, CheckList.LstAnswer data, final int position) {
        if(data.getIsEdit() == 1){
            holder.cb_AnswerName.setTextColor(context.getResources().getColor(R.color.blue));
            holder.cb_AnswerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditAnswerDialog dialog = new EditAnswerDialog(context);
                    dialog.showDialog();
                    dialog.setOnSubmitClick(new EditAnswerDialog.OnSubmitClick() {
                        @Override
                        public void onSubmitComment(String answer) {
                            holder.cb_AnswerName.setText("Khác: "+(!answer.equals("")? answer :holder.cb_AnswerName.getText().toString()));
                            lstAnswers.get(position).setAnswerName(!answer.equals("")? answer :holder.cb_AnswerName.getText().toString());
                            dialog.dismissDialog();
                        }
                    });
                }
            });
        }else {
            holder.cb_AnswerName.setTextColor(context.getResources().getColor(R.color.gray4));
        }
    }


    @Override
    public int getItemCount() {
        return lstAnswers.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_AnswerName)
        RadioButton cb_AnswerName;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
