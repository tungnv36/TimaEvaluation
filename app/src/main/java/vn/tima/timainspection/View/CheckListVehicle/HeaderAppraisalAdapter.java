package vn.tima.timainspection.View.CheckListVehicle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.Question;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class HeaderAppraisalAdapter extends RecyclerView.Adapter<HeaderAppraisalAdapter.MyViewHolder> {
    public static final String TAG = HeaderAppraisalAdapter.class.getSimpleName();
    private ArrayList<String> listMenu = new ArrayList<>();
    private Map<String, ArrayList<Question>> questionMap = new HashMap<>();
    Context context;
    ContractEntity contractEntity;
    public void setQuestionMap(Map<String, ArrayList<Question>> questionMap) {
        this.questionMap = questionMap;
    }

    public HeaderAppraisalAdapter(ContractEntity contractEntity) {
        this.contractEntity = contractEntity;
    }

    public void setData(ArrayList<String> listMenu) {
        this.listMenu = listMenu;
    }

    public interface OnclickTitleListener {
        public void onClick(int position);
    }

    public OnclickTitleListener listener;

    public void setListener(OnclickTitleListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_header_appraisal, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tile_question.setText(listMenu.get(position));
        initRecyclerViewQuestion(position, holder.rv_question);
    }

    private void initRecyclerViewQuestion(int position, RecyclerView rv) {
        String keyQuestion = listMenu.get(position);
        ArrayList<Question> listQuestions = new ArrayList<Question>();
        if (questionMap.get(keyQuestion) != null) {
            listQuestions = questionMap.get(keyQuestion);
        }
        Common.setVerticalRecyclerView(context, rv);
        AppraisalAdapter adapter = new AppraisalAdapter(contractEntity);
        adapter.setData(listQuestions);
        rv.setAdapter(adapter);
        Log.e(TAG, "initRecyclerViewQuestion: " + questionMap.size());
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_tile_question)
        TextView tv_tile_question;
        @BindView(R.id.rv_question)
        RecyclerView rv_question;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
