package vn.tima.timainspection.View.CheckListVehicle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.Question;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class AppraisalAdapter extends RecyclerView.Adapter<AppraisalAdapter.MyViewHolder> {
    public static final String TAG = AppraisalAdapter.class.getSimpleName();
    private ArrayList<Question> listData = new ArrayList<>();
    public static Map<String, String> mapDeduction = new HashMap<>();
    private ContractEntity contractEntity;
    public void setData(ArrayList<Question> listMenu) {
        this.listData = listMenu;
    }
    public UserInfo userInfo;
    public Context context;
    public interface OnclickTitleListener {
        void onClick(int position);
    }

    public OnclickTitleListener listener;

    public void setListener(OnclickTitleListener listener) {
        this.listener = listener;
    }

    public interface OnCompletedTotal {
        void onTotalMonney(int totalMonney, int position, Question question);
    }

    public static OnCompletedTotal onCompletedTotal;

    public static void setOnCompletedTotal(OnCompletedTotal onCompletedTotal) {
        AppraisalAdapter.onCompletedTotal = onCompletedTotal;
    }

    public AppraisalAdapter(ContractEntity contractEntity) {
        this.contractEntity = contractEntity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_appraisal, parent, false);
        userInfo = UserInfo.getInstance();
        ContractDetailActivity.myPosition += 1;
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.e(TAG, "MecashApplication.myPosition " + ContractDetailActivity.myPosition);
        final int positionCall = ContractDetailActivity.myPosition;
        final Question question = listData.get(position);
        holder.tv_question.setText(listData.get(position).getName());
        setupRadioButton(holder, question);
        setMoneyDiscount(holder, question, false, position);
        if ((userInfo.getUser(context).getGroupId().equals(Constant.GROUP_ID_TVV)
                || userInfo.getUser(context).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA))
                && (contractEntity.getStatus() == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY_N_11
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY_N_12
                || contractEntity.getStatus() == Constant.STATUS_TDTD_HO_SO)) {
            holder.rd_yes.setEnabled(true);
            holder.rd_no.setEnabled(true);
        } else {
            holder.rd_yes.setEnabled(false);
            holder.rd_no.setEnabled(false);
        }
        holder.rg_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (holder.rd_yes.isChecked())
                    question.setIsCheck("true");
                else
                    question.setIsCheck("false");
                setMoneyDiscount(holder, question, true, position);
                onCompletedTotal.onTotalMonney(ContractDetailActivity.totalMoneyDiscount, positionCall, question);
                Log.e(TAG, "onCheckedChanged: position " + positionCall);
            }
        });
    }

    private void setMoneyDiscount(MyViewHolder holder, Question question, boolean isActionSum, int position) {
        question.setIsCancelLoan();
        if (question.getIsCheck() != null) {
            if (!question.getIsCheck().equalsIgnoreCase(question.getState())) {
                if (!userInfo.getUser(context).getGroupId().equals(Constant.GROUP_ID_TVV)) {
                    holder.tv_money_discount.setText(question.getMoneyDiscount().equals("0") ? "" : "- " + Common.formatMoney(Long.parseLong(question.getMoneyDiscount())));
                }
                if (isActionSum) {
                    mapDeduction.put(question.getId(), question.getMoneyDiscount());
                    ContractDetailActivity.totalMoneyDiscount += Integer.parseInt(question.getMoneyDiscount());
                }
            } else {
                holder.tv_money_discount.setText("");
                if (mapDeduction.containsKey(question.getId())) {
                    ContractDetailActivity.totalMoneyDiscount -= Integer.parseInt(mapDeduction.get(question.getId()));
                    mapDeduction.remove(question.getId());
                }
            }
        }
    }

    private void setupRadioButton(MyViewHolder holder, Question question) {
        if (question.getIsCheck() != null && question.getIsCheck().equalsIgnoreCase("true")) {
            holder.rd_yes.setChecked(true);
        } else if (question.getIsCheck() != null && question.getIsCheck().equalsIgnoreCase("false")) {
            holder.rd_no.setChecked(true);
        } else {
            holder.rd_yes.setChecked(false);
            holder.rd_no.setChecked(false);
        }
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_question)
        TextView tv_question;
        @BindView(R.id.tv_money_discount)
        TextView tv_money_discount;
        @BindView(R.id.rd_no)
        RadioButton rd_no;
        @BindView(R.id.rd_yes)
        RadioButton rd_yes;
        @BindView(R.id.rg_check)
        RadioGroup rg_check;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}