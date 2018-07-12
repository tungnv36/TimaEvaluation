package vn.tima.timainspection.View.ContractDetail;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.afollestad.sectionedrecyclerview.SectionedViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractDetailEntity;
import vn.tima.timainspection.Model.Entity.DetailEntity;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DateTimeHelper;

/**
 * Created by tima on 11/28/17.
 */

public class ContractDetailAdapter extends SectionedRecyclerViewAdapter<ContractDetailAdapter.MainVH> {

    private ContractDetailEntity contractDetailEntity;
    private List<DetailEntity> lstCustomer;
    private List<DetailEntity> lstWork;
    private List<DetailEntity> lstFamily;
    private List<DetailEntity> lstLoanCredit;
    private int TypeID;

    public ContractDetailAdapter(ContractDetailEntity contractDetailEntity, int TypeID) {
        this.contractDetailEntity = contractDetailEntity;
        lstCustomer = new ArrayList<>();
        lstWork = new ArrayList<>();
        lstFamily = new ArrayList<>();
        lstLoanCredit = new ArrayList<>();
        this.TypeID = TypeID;
        addCustomer();
        addWork();
        addFamily();
        addLoanCredit();
    }

    private void addCustomer() {
        addList(lstCustomer, "Họ và tên: ", contractDetailEntity.Customer.getFullName(), false);
        addList(lstCustomer, "Số ĐT: ", contractDetailEntity.Customer.getPhone(), false);
        addList(lstCustomer, "Số CMT: ", contractDetailEntity.Customer.getCardNumber(), false);
        addList(lstCustomer, "Ngày sinh: ", Common.formatOnlyDate(contractDetailEntity.Customer.getBirthday()), false);//DateTimeHelper.changeDateFormat(contractDetailEntity.Customer.Birthday)
        addList(lstCustomer, "Giới tính: ", contractDetailEntity.Customer.getGender()==0?"Nam":"Nữ", false);
        addList(lstCustomer, "Địa chỉ đang ở: ", contractDetailEntity.Customer.getStreet(), false);
        addList(lstCustomer, "Địa chỉ hộ khẩu: ", contractDetailEntity.Customer.getAddressHouseHold(), false);
        addList(lstCustomer, "Phường: ", contractDetailEntity.Customer.getNameWard(), false);
        addList(lstCustomer, "Quận huyện - Thành phố: ", contractDetailEntity.Customer.getNameDistrict() + " - " + contractDetailEntity.Customer.getNameCity(), false);
        addList(lstCustomer, "Hình thức sở hữu: ", contractDetailEntity.Customer.getTypeOfOwnership(), false);
        addList(lstCustomer, "Thời gian cư trú: ", contractDetailEntity.Customer.getLivingTime(), false);
    }

    private void addWork() {
        addList(lstWork, "Tên công ty: ", contractDetailEntity.Customer.CompanyName, false);
        addList(lstWork, "Địa chỉ công ty: ", contractDetailEntity.Customer.AddressCompany, false);
        addList(lstWork, "Quận huyện - Thành phố: ", contractDetailEntity.Customer.getCompanyDistrictName() + " - " + contractDetailEntity.Customer.getCompanyCityName(), false);
        addList(lstWork, "Số ĐT liên hệ: ", contractDetailEntity.Customer.CompanyPhone, false);
        addList(lstWork, "Vị trí: ", contractDetailEntity.Customer.Job, false);
        addList(lstWork, "Mô tả vị trí công việc: ", contractDetailEntity.Customer.DescriptionPositionJob, false);
        addList(lstWork, "Lương: ", Common.formatMoney(contractDetailEntity.Customer.Salary), false);
        addList(lstWork, "Tên đồng nghiệp: ", contractDetailEntity.Customer.FullNameColleague, false);
        addList(lstWork, "SĐT đồng nghiệp: ", contractDetailEntity.Customer.PhoneColleague, false);
    }

    private void addFamily() {
        addList(lstFamily, "Tên người thân: ", contractDetailEntity.Customer.FullNameFamily, false);
        addList(lstFamily, "Mối quan hệ: ", contractDetailEntity.Customer.RelativeFamily, false);
        addList(lstFamily, "SĐT người thân: ", contractDetailEntity.Customer.PhoneFamily, false);
    }

    private void addLoanCredit() {
        addList(lstLoanCredit, "Tiền ĐL cho vay: ", Common.formatMoney(contractDetailEntity.LoanCredit.TotalMoney), false);
        addList(lstLoanCredit, "Tiền khách cần vay: ", Common.formatMoney(contractDetailEntity.LoanCredit.TotalMoneyFirst), false);
        addList(lstLoanCredit, "Ngày đăng ký: ", Common.formatOnlyDate(contractDetailEntity.LoanCredit.CreateDate), false);
        addList(lstLoanCredit, "Số ngày vay: ", String.valueOf(contractDetailEntity.LoanCredit.LoanTime) + " ngày", false);
        addList(lstLoanCredit, "Ngày đóng lãi: ", contractDetailEntity.InterestPaymentDate, false);
        addList(lstLoanCredit, "Chu kì đóng lãi: ", contractDetailEntity.LoanCredit.Frequency + " ngày", false);
        addList(lstLoanCredit, "Ngày tất toán: ", contractDetailEntity.FinalSettlementDate, false);
        addList(lstLoanCredit, "Số kỳ trả: ", contractDetailEntity.NumberPeriod + " kỳ", false);
        addList(lstLoanCredit, "Tổng phí tư vấn: ", Common.formatMoney(Long.parseLong(contractDetailEntity.TotalMoneyConsulant.replace(",","").replace(".",""))), false);
        addList(lstLoanCredit, "Lãi và phí trả hàng kỳ: ", Common.formatMoney(Long.parseLong(contractDetailEntity.TotalMoneyPaymentOnePeriod.replace(",","").replace(".",""))), false);
        addList(lstLoanCredit, "Tổng phí dịch vụ: ", Common.formatMoney(Long.parseLong(contractDetailEntity.TotalMoneyService.replace(",","").replace(".",""))), false);
        addList(lstLoanCredit, "Tiền tất toán: ", Common.formatMoney(Long.parseLong(contractDetailEntity.TotalMoneyAccounting.replace(",","").replace(".",""))), false);
        addList(lstLoanCredit, "Tổng tiền lãi: ", Common.formatMoney(Long.parseLong(contractDetailEntity.TotalMoneyInterest.replace(",","").replace(".",""))), false);
    }

    void addList(List<DetailEntity> lstCustomer, String title, String value, Boolean isTitle){
        DetailEntity detailEntity = new DetailEntity();
        detailEntity.setTitle(title);
        detailEntity.setValue(value);
        detailEntity.setTitle(isTitle);
        lstCustomer.add(detailEntity);
    }

    @Override
    public int getSectionCount() {
        return 4;
    }

    @Override
    public int getItemCount(int section) {
        switch (section) {
            case 0:
                return lstCustomer.size();
            case 1:
                return lstWork.size();
            case 2:
                return lstFamily.size();
            case 3:
                return lstLoanCredit.size();
            default:
                return 0;
        }
    }

    @Override
    public void onBindHeaderViewHolder(MainVH holder, int section, boolean expanded) {
        switch (section) {
            case 0:
                holder.title.setText("Thông tin cá nhân");
                break;
            case 1:
                holder.title.setText("Thông tin việc làm");
                break;
            case 2:
                holder.title.setText("Thông tin người thân");
                break;
            case 3:
                holder.title.setText(Html.fromHtml("Thông tin khoản vay <font color=\"black\">(" + Common.getTypeContract(TypeID) + ")</font>"));
                break;
        }
        holder.caret.setImageResource(expanded ? R.drawable.ic_expand_more_black_24dp : R.drawable.ic_expand_less_black_24dp);
    }

    @Override
    public void onBindFooterViewHolder(MainVH holder, int section) {
        holder.title.setText(String.format("Section footer %d", section));
    }

    @Override
    public void onBindViewHolder(
            MainVH holder, int section, int relativePosition, int absolutePosition) {
        switch (section) {
            case 0:
                holder.title.setText(lstCustomer.get(relativePosition).getTitle());
                holder.value.setText(lstCustomer.get(relativePosition).getValue());
                break;
            case 1:
                holder.title.setText(lstWork.get(relativePosition).getTitle());
                holder.value.setText(lstWork.get(relativePosition).getValue());
                break;
            case 2:
                holder.title.setText(lstFamily.get(relativePosition).getTitle());
                holder.value.setText(lstFamily.get(relativePosition).getValue());
                break;
            case 3:
                holder.title.setText(lstLoanCredit.get(relativePosition).getTitle());
                holder.value.setText(lstLoanCredit.get(relativePosition).getValue());
                break;
        }
        if ((section == 0 && relativePosition == 5) || (section == 1 && relativePosition == 1 && TypeID == 1)) {
            holder.value.setTypeface(null, Typeface.BOLD);
            holder.title.setTypeface(null, Typeface.BOLD);
        } else {
            holder.value.setTypeface(null, Typeface.NORMAL);
            holder.title.setTypeface(null, Typeface.NORMAL);
        }
        holder.title.setTextColor(Color.parseColor("#5F5E5F"));
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public MainVH onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                layout = R.layout.row_header_detail;
                break;
            case VIEW_TYPE_ITEM:
                layout = R.layout.row_main_detail;
                break;
            case VIEW_TYPE_FOOTER:
                layout = R.layout.row_footer_detail;
                break;
            default:
                layout = R.layout.row_main_bold_detail;
                break;
        }

        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new MainVH(v, this);
    }

    static class MainVH extends SectionedViewHolder implements View.OnClickListener {

        final TextView title;
        final TextView value;
        final ImageView caret;
        final ContractDetailAdapter adapter;

        MainVH(View itemView, ContractDetailAdapter adapter) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.value= itemView.findViewById(R.id.value);
            this.caret = itemView.findViewById(R.id.caret);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (isFooter()) {
                return;
            }

            if (isHeader()) {
                adapter.toggleSectionExpanded(getRelativePosition().section());
            } else {
            }
        }
    }
}

