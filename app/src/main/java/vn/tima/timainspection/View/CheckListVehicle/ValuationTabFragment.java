package vn.tima.timainspection.View.CheckListVehicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import vn.tima.timainspection.Model.Entity.ContractDetailEntity;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.Motobike;
import vn.tima.timainspection.Model.Entity.PostAppraisal;
import vn.tima.timainspection.Model.Entity.Question;
import vn.tima.timainspection.Model.Login.UserInfo;
import vn.tima.timainspection.Model.Product.ProductRequest;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.Constant;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;
import vn.tima.timainspection.View.ContractDetail.ContractDetailFragment;
import vn.tima.timainspection.View.Dialog.LoadingDialog;
import vn.tima.timainspection.View.Main.MainActivity;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by hoang do on 1/4/2017.
 */

public class ValuationTabFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ValuationTabFragment.class.getSimpleName();
    @BindView(R.id.act_model_motobike)
    AutoCompleteTextView atc_motobile;
    @BindView(R.id.bt_revaluation)
    ImageButton bt_revaluation;
    @BindView(R.id.rv_post_appraisal)
    RecyclerView rv_post_appraisal;
    @BindView(R.id.tv_total_money_discount)
    TextView tv_total_money_discount;
    @BindView(R.id.tv_money_discount_by_year)
    TextView tv_money_discount_by_year;
    @BindView(R.id.tv_name_valuation)
    TextView tv_name_valuation;
    @BindView(R.id.btn_post_appraisal)
    Button btn_post_appraisal;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.ll_wrap)
    LinearLayout ll_wrap;

    private HeaderAppraisalAdapter headerAppraisalAdapter;
    private ArrayList<String> listHeaderList;
    private int productId = 0;
    private SearchMotobikeAdapter adapter_search;
    private ArrayList<String> data_search = new ArrayList<>();
    private ArrayList<Motobike> data_motobike = new ArrayList<>();
    public static boolean isCancelLoan = false;
    private ArrayList<PostAppraisal> listAppraisal;
    boolean isValidCheck = false;
    private String totalPrice = "";
    public static int finalMonney = 0;
    private ContractEntity contractEntity;
    private UserInfo userInfo;
    ContractDetailEntity contractDetailEntity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_valuation, container, false);
        contractEntity = (ContractEntity) getActivity().getIntent().getSerializableExtra("contractEntity");
        contractDetailEntity = ContractDetailFragment.contractDetailEntity;

        userInfo = UserInfo.getInstance();
        ContractDetailActivity.totalMoneyDiscount = 0;
        listAppraisal = new ArrayList<>();
        ButterKnife.bind(this, view);
        btn_post_appraisal.setOnClickListener(this);
        if ((userInfo.getUser(getContext()).getGroupId().equals(Constant.GROUP_ID_TVV)
                || userInfo.getUser(getContext()).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA))
                && (contractEntity.getStatus() == Constant.STATUS_CHO_TU_VAN_VIEN_DI_LAY_HO_SO
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY_N_11
                || contractEntity.getStatus() == Constant.STATUS_HO_SO_BI_HUY_N_12
                || contractEntity.getStatus() == Constant.STATUS_TDTD_HO_SO))
            btn_post_appraisal.setVisibility(View.VISIBLE);
        else
            btn_post_appraisal.setVisibility(View.GONE);
        if(contractEntity.getTypeid() == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE || contractEntity.getTypeid() == Constant.TYPE_ID_KHOAN_VAY_THEO_DANG_KY_XE_K_CHINH_CHU){
            ll_wrap.setVisibility(View.VISIBLE);
            tv_message.setVisibility(View.GONE);
        }else{
            ll_wrap.setVisibility(View.GONE);
            tv_message.setVisibility(View.VISIBLE);
        }
        initGetSearchData();
        initRevaluation();
        initRecyclerViewHeaderAppraisal();

        return view;
    }

    private void initRevaluation() {
        if (userInfo.getUser(getContext()).getGroupId().equals(Constant.GROUP_ID_TVV)
                || userInfo.getUser(getContext()).getGroupId().equals(Constant.GROUP_ID_KSNB_TD_THUC_DIA)) {
            atc_motobile.setEnabled(true);
        } else {
            atc_motobile.setEnabled(false);
        }
        bt_revaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (atc_motobile.isEnabled()) {
                    bt_revaluation.setBackgroundResource(R.mipmap.ic_lock_search);
                    atc_motobile.setEnabled(false);
                } else {
                    bt_revaluation.setBackgroundResource(R.mipmap.ic_unlock_search);
                    atc_motobile.setEnabled(true);
                }
            }
        });

    }

    private void initSelectMotobike() {
        atc_motobile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    int real_potision = getPotisionByName(data_search.get(position));
                    Common.hideKeyboard(getActivity());
                    getDataQuestion(data_motobike.get(real_potision).getIdType() + "", data_motobike.get(real_potision).getId() + "");
                    productId = data_motobike.get(real_potision).getId();
                    totalPrice = data_motobike.get(real_potision).getTotalPrice() + "";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getPotisionByName(String name) {
        for (Motobike motobike : data_motobike) {
            if (motobike.getFullName().equals(name))
                return data_motobike.indexOf(motobike);
        }
        return -1;
    }

    private void initGetSearchData() {
        ProductRequest productRequest = ProductRequest.getInstance(getContext());
//        showProgressDialog("Đang tải dữ liệu, vui lòng đợi...");
        productRequest.getAllProduct(new OnResponse<String, ArrayList<Motobike>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, ArrayList<Motobike> extraData) {
                if (extraData != null) {
                    try {
//                        hideProgressDialog();
                        data_motobike = extraData;
                        Motobike motobike = getUpdateProduct(data_motobike);
                        data_search.clear();
                        // kiem tra xem da co tham dinh gia truoc day chua
                        if (motobike != null) {
                            // da co tham dinh truoc do
                            totalPrice = motobike.getTotalPrice() + "";
                            productId = motobike.getId();
                            atc_motobile.setText(motobike.getFullName());
                            //     atc_motobile.setEnabled(false);
                            //       bt_revaluation.setBackgroundResource(R.drawable.ic_lock_search);
                            if (!userInfo.getUser(getContext()).getShopID().equals(Constant.SHOP_ID)) {
                                bt_revaluation.setVisibility(View.INVISIBLE);
                            }
                            getDataQuestion(motobike.getIdType() + "", motobike.getId() + "");
                        } else {
                            //        bt_revaluation.setBackgroundResource(R.drawable.ic_unlock_search);
                            // chua duoc tham dinh
                        }
                        for (Motobike moto : data_motobike) {
                            data_search.add(moto.getFullName());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, data_search);
                        atc_motobile.setThreshold(1);//will start working from first character
                        atc_motobile.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

                        atc_motobile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        });

//                        adapter_search = new SearchMotobikeAdapter(getActivity(), data_search);
//                        atc_motobile.setAdapter(adapter_search);
                        initSelectMotobike();
                    } catch (Exception e) {
//                        hideProgressDialog();
                        e.printStackTrace();
                    }
                } else {
//                    hideProgressDialog(rs);
                }
            }

            @Override
            public void onResponseError(String tag, String message) {
//                hideProgressDialog(message);
            }

        }, userInfo.getUser(getContext()).getToken(), String.valueOf(contractEntity.getLoancreditid()));
    }

    public Motobike getUpdateProduct(ArrayList<Motobike> data) {
        try {
            for (Motobike motobike : data) {
                if (motobike.getProductReviewId() > 0) return motobike;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * Lấy tiêu chí thẩm định
    * @Author: hoàng anh
    * */

    private void initRecyclerViewHeaderAppraisal() {
        listHeaderList = new ArrayList<>();
        Common.setVerticalRecyclerView(getContext(), rv_post_appraisal);
        headerAppraisalAdapter = new HeaderAppraisalAdapter(contractEntity);
        headerAppraisalAdapter.setData(listHeaderList);
        rv_post_appraisal.setAdapter(headerAppraisalAdapter);
    }

    private void getDataQuestion(String idType, final String productId) {
        initRecyclerViewHeaderAppraisal();
        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), "Loading. . .");
        ContractDetailEntity.Customer customer = contractDetailEntity.getCustomer();
        tv_name_valuation.setText("Xe khách hàng đăng ký: " + customer.getManufacturer() + " " + customer.getModelPhone() + " " + customer.getYearMade());
        ContractDetailActivity.totalMoneyDiscount = 0;
        ContractDetailActivity.myPosition = -1;
        AppraisalAdapter.mapDeduction = new HashMap<>();
        Log.e(TAG, "getDataQuestion: myPosition: " + ContractDetailActivity.myPosition);
        isCancelLoan = false;
        isValidCheck = false;
        listHeaderList = new ArrayList<>();
        listAppraisal = new ArrayList<>();
        // callback khi check checkbox
        AppraisalAdapter.setOnCompletedTotal(new AppraisalAdapter.OnCompletedTotal() {
            @Override
            public void onTotalMonney(int totalMonney, int position, Question question) {
                Log.e("--------", totalMonney + "");
                listAppraisal.get(position).setIsCheck(question.getIsCheck());
                listAppraisal.get(position).setProductId(productId);
                listAppraisal.get(position).setProductReviewId(question.getId());
                listAppraisal.get(position).setLoanCreditId(contractEntity.getLoancreditid() + "");
                listAppraisal.get(position).setCancel(question.isCancelLoan());
                for (int i = 0; i < listAppraisal.size(); i++) {
                    if (listAppraisal.get(i).getIsCheck() == null) {
                        isValidCheck = false;
                        break;
                    } else {
                        isValidCheck = true;
                    }
                    if (listAppraisal.get(i).isCancel()) {
                        isCancelLoan = true;
                        break;
                    } else {
                        isCancelLoan = false;
                    }
                }
                finalMonney = (Integer.parseInt(totalPrice) - ContractDetailActivity.totalMoneyDiscount) / 2;
                long lamtron = Common.roundMoney(finalMonney, Constant.THRESHHOLD_ROUND);
                tv_total_money_discount.setText(isCancelLoan ? "0" : Common.formatMoney(Long.parseLong(finalMonney > 0 ? lamtron + "" : "0")));
                Log.e(TAG, "MecashApplication.totalMoneyDiscount = " + totalMonney);
            }
        });
        ProductRequest.getInstance(getContext()).getListQuestion(idType, productId,contractEntity.getLoancreditid()+"" ,new OnResponse<String, List<Question>>() {
            @Override
            public void onStart() {
                super.onStart();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                loadingDialog.dismissDialog();
            }

            @Override
            public void onResponseSuccess(String tag, String rs, List<Question> extraData) {
                handlerData(extraData, productId);
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        });
    }

    private void handlerData(List<Question> extraData, String productId) {
        Map<String, ArrayList<Question>> map = new HashMap<>();
        ArrayList<Question> listQuestion;
        for (int i = 0; i < extraData.size(); i++) {
            listQuestion = new ArrayList<>();
            if (extraData.get(i).getParentId().equals("0")) {
                listHeaderList.add(extraData.get(i).getName());
            }
            for (int j = 0; j < extraData.size(); j++) {
                String parentId = extraData.get(j).getParentId();
                String id = extraData.get(i).getId();
                // tách các câu hỏi con theo tiêu đề.
                if (parentId.equals(id)) {
                    listQuestion.add(extraData.get(j));
                    map.put(extraData.get(i).getName(), listQuestion);
                    setDataListAppraisal(extraData.get(j));
                    // check đã tick hết các tiêu chí thẩm định chưa
                    if (extraData.get(j).getIsCheck() != null) {
                        isValidCheck = true;
                        // check câu trả lời có trùng với kết quả không?
                        if (!extraData.get(j).getIsCheck().equalsIgnoreCase(extraData.get(j).getState())) {
                            ContractDetailActivity.totalMoneyDiscount += Integer.parseInt(extraData.get(j).getMoneyDiscount());
                            if (!AppraisalAdapter.mapDeduction.containsKey(extraData.get(j).getId())) {
                                AppraisalAdapter.mapDeduction.put(extraData.get(j).getId(), extraData.get(j).getMoneyDiscount());
                            }
                            if (extraData.get(j).getIsCancel().equalsIgnoreCase("true")) {
                                isCancelLoan = true;
                            }
                        }
                    } else
                        isValidCheck = false;
                }
            }
        }
        addLastItemHeader(extraData);
        tv_money_discount_by_year.setText(Common.formatMoney(Common.roundMoney((Long.parseLong(totalPrice) / 2), Constant.THRESHHOLD_ROUND)));
        finalMonney = (Integer.parseInt(totalPrice) - ContractDetailActivity.totalMoneyDiscount) / 2;
        long lamtron = Common.roundMoney(finalMonney, Constant.THRESHHOLD_ROUND);
        tv_total_money_discount.setText(isCancelLoan ? "0" : Common.formatMoney(Long.parseLong(lamtron + "")));
        Log.e(TAG, "MecashApplication.totalMoneyDiscount = " + ContractDetailActivity.totalMoneyDiscount);
        notifyDataSetChangedListHeader(map);
    }

    /*
    * add item chưa nội dung tiêu đề, parentId = 0
    * */
    private void addLastItemHeader(List<Question> extraData) {
        for (int i = 0; i < extraData.size(); i++) {
            if (extraData.get(i).getParentId().equals("0")) {
                PostAppraisal postAppraisal = new PostAppraisal();
                postAppraisal.setIsCheck("true");
                postAppraisal.setProductId(productId + "");
                postAppraisal.setProductReviewId(extraData.get(i).getId());
                postAppraisal.setLoanCreditId(contractEntity.getLoancreditid() + "");
                listAppraisal.add(postAppraisal);
            }
        }
    }

    private void setDataListAppraisal(Question question) {
        question.setIsCancelLoan();
        PostAppraisal postAppraisal = new PostAppraisal();
        postAppraisal.setIsCheck(question.getIsCheck());
        postAppraisal.setProductId(productId + "");
        postAppraisal.setProductReviewId(question.getId());
        postAppraisal.setLoanCreditId(contractEntity.getLoancreditid() + "");
        postAppraisal.setCancel(question.isCancelLoan());
        listAppraisal.add(postAppraisal);
    }

    private void notifyDataSetChangedListHeader(Map<String, ArrayList<Question>> map) {
        if (map.size() > 0) {
            headerAppraisalAdapter.setQuestionMap(map);
        }
        headerAppraisalAdapter.setData(listHeaderList);
        headerAppraisalAdapter.notifyDataSetChanged();
    }

    private boolean isValid() {
        if (!isValidCheck) {
            DialogUtils.showAlertDialog(getActivity(), "Bạn phải check hết các tiêu chí.", null);
            return false;
        }
        if (isCancelLoan) {
            DialogUtils.showAlertDialog(getActivity(), "Vi phạm không cho vay", null);
            return false;
        }
        return true;
    }

    private void postDataAppraisal() {
        final LoadingDialog loadingDialog = new LoadingDialog(getActivity(), "Thẩm định. . .");
        Gson gson = new Gson();
        String jsonElements = gson.toJson(listAppraisal);
        try {
            //String strListProductResultReview = URLEncoder.encode(jsonElements, "utf-8");
            if (isValid()) {
                ProductRequest.getInstance(getContext()).postAppraisal(contractEntity.getLoancreditid() + "",
                        productId + "", finalMonney + "", jsonElements, new OnResponse<String, String>() {
                            @Override
                            public void onStart() {
                                super.onStart();
                                loadingDialog.showDialog();
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                loadingDialog.dismissDialog();
                            }

                            @Override
                            public void onResponseSuccess(String tag, String rs, String extraData) {
                                DialogUtils.showAlertDialog(getActivity(), "Thông báo", " Thẩm định thành công", new DialogUtils.OnClickListener() {
                                    @Override
                                    public void onClickSuccess() {
                                        startActivity(new Intent(getActivity(), MainActivity.class));
                                        getActivity().finish();
                                    }
                                });
                            }

                            @Override
                            public void onResponseError(String tag, String message) {

                            }
                        });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_post_appraisal:
                DialogUtils.showExitDialog(ValuationTabFragment.this.getActivity(), "Bạn có chắc chắn đã hoàn thành thẩm định xe chưa?", "Chắc chắn", "Xem lại", new DialogUtils.OnClickListener2() {
                    @Override
                    public void onClickSuccess() {
                        postDataAppraisal();
                    }

                    @Override
                    public void onClickCancel() {

                    }
                });
                break;
        }
    }
}