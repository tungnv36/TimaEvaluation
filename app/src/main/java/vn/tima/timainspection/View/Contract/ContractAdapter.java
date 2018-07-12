package vn.tima.timainspection.View.Contract;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
//import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.Presenter.Comment.CommentPresenter;
import vn.tima.timainspection.Presenter.Contract.ContractPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.Util.GPSTracker;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;
import vn.tima.timainspection.View.ContractDetail.SpinnerAdapter;

/**
 * Created by tima on 11/25/17.
 */

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ContractViewHolder> {

    private List<ContractEntity> contractData;
    private List<WaitTDHSEntity> contractDataChoTD;
    private GPSTracker gps;
//    private List<String> lstThamDinh = new ArrayList<>();
//    private String[] lstThamDinh = { "Chưa thẩm định", "Thẩm định xong", "Đang thẩm định" };
//    private SpinnerAdapter spinnerAdapter;
    private Context context;
    private ContractPresenter contractPresenter;
    private boolean isSelectNha = false;
    private int type;

    public void setContractData(List<ContractEntity> contractData) {
        this.contractData = contractData;
    }

    public void setContractDataChoTD(List<WaitTDHSEntity> contractDataChoTD) {
        this.contractDataChoTD = contractDataChoTD;
    }

    public ContractAdapter(Context context, List<ContractEntity> contractData, List<WaitTDHSEntity> contractDataChoTD, ContractPresenter contractPresenter, int type) {
        this.type = type;
        this.contractData = contractData;
        this.contractDataChoTD = contractDataChoTD;
        this.context = context;
        this.contractPresenter = contractPresenter;
    }

    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contract, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContractViewHolder holder, int position) {
        try {
            if(type == 0) {
                final ContractEntity contractEntity = contractData.get(position);
                String ball = "";
                if(contractEntity.getUtmSource().equals("wolrdcup")) {
                    ball = "⚽";
                }
                holder.tvHoTen.setText(contractEntity.getFullname() + " (HĐ: " + contractEntity.getLoancreditid() + ")️ " + ball);
                holder.tvNgayThang.setText(Html.fromHtml("<p><font color=\"" + (contractEntity.getTimedelay() == 1 ? "red" : "#0F7F12") + "\">" + contractEntity.getTimedelayvalue() + "</font></p>"));
                holder.tvQuanHuyen.setText(contractEntity.getNamedistrict());
                holder.tvTongTien.setText(Common.formatMoney(contractEntity.getTotalmoney()));
                holder.tvTrangThai.setText(Common.getTypeContract(contractEntity.getTypeid()) + " : " + contractEntity.getLoantime() + " ngày");

                if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

                if (contractEntity.getIsthamdinhnha() == 0) {
                    holder.llNha.setVisibility(View.GONE);
                } else {
                    holder.llNha.setVisibility(View.VISIBLE);
                }

                if (contractEntity.getIsthamdinhcongty() == 0) {
                    if (contractEntity.getTypeid() == 1 || ((contractEntity.getTypeid() == 2 || contractEntity.getTypeid() == 5) && contractEntity.getTypeofownershipid() == 4)) {
                        holder.llCongTy.setVisibility(View.VISIBLE);
                        holder.btCongTy.setEnabled(false);
                    } else {
                        holder.llCongTy.setVisibility(View.GONE);
                    }
                } else {
                    holder.llCongTy.setVisibility(View.VISIBLE);
                }

                if(contractEntity.getTypeid() == 2 || contractEntity.getTypeid() == 5) {
//                    holder.tvCongTyTitle.setText("Xe: ");
//                    holder.btCongTy.setText("Thẩm định xe ▾");
                    holder.btThamDinhXe.setVisibility(View.VISIBLE);
                } else {
                    holder.btThamDinhXe.setVisibility(View.GONE);
                }

//                holder.llCongTy.setVisibility(View.VISIBLE);
//                if (contractEntity.getType() == 1) {
//                    holder.tvThamDinh.setText("Cty: ");
//                } else {
//                    holder.tvThamDinh.setText("Nhà: ");
//                }

                switch (contractEntity.getTypeid()) {
                    case 1://luong
                        holder.tvTrangThai.setTextColor(Color.parseColor("#0F7F12"));
                        break;
                    case 2://xe may
                        holder.tvTrangThai.setTextColor(Color.parseColor("#ef4223"));
                        break;
                    case 4://ho khau
                        holder.tvTrangThai.setTextColor(Color.RED);
                        break;
                    case 5://xe may
                        holder.tvTrangThai.setTextColor(Color.parseColor("#ef4223"));
                        break;
                    case 6://oto
                        holder.tvTrangThai.setTextColor(Color.BLACK);
                        break;
                    case 7://nha dat
                        holder.tvTrangThai.setTextColor(Color.BLUE);
                        break;
                    case 8://dang ky oto
                        holder.tvTrangThai.setTextColor(Color.BLACK);
                        break;
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gps = new GPSTracker(context);
                        if(!gps.canGetLocation()){
                            gps.showSettingsAlert();
                        } else {
                            Intent intent = new Intent(view.getContext(), ContractDetailActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("CustomerID", contractEntity.getCustomerid());
                            intent.putExtra("LoanID", contractEntity.getLoancreditid());
                            intent.putExtra("TypeID", contractEntity.getTypeid());
                            intent.putExtra("contractEntity", contractEntity);
                            intent.putExtra("tabPosition", 0);
                            view.getContext().startActivity(intent);
                        }
                    }
                });

                holder.btThamDinhXe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gps = new GPSTracker(context);
                        if(!gps.canGetLocation()){
                            gps.showSettingsAlert();
                        } else {
                            Intent intent = new Intent(view.getContext(), ContractDetailActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("CustomerID", contractEntity.getCustomerid());
                            intent.putExtra("LoanID", contractEntity.getLoancreditid());
                            intent.putExtra("TypeID", contractEntity.getTypeid());
                            intent.putExtra("contractEntity", contractEntity);
                            intent.putExtra("tabPosition", 2);
                            view.getContext().startActivity(intent);
                        }
                    }
                });

                holder.btNha.setText(getThamDinh(contractEntity.getAddressfieldsurveystatus(), holder.btNha));
                holder.btCongTy.setText(getThamDinh(contractEntity.getCompanyfieldsurveystatus(), holder.btCongTy));

                holder.btNha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gps = new GPSTracker(context);
                            if(!gps.canGetLocation()){
                                gps.showSettingsAlert();
                            } else {
                                PopupMenu popup = new PopupMenu(context, holder.btNha);
                                popup.inflate(R.menu.popup_menu);
                                popup.getMenu().findItem(R.id.chuaThamDinh).setTitle(Html.fromHtml("<font color='#ff0000'>Chưa thẩm định</font>"));
                                popup.getMenu().findItem(R.id.thamDinhXong).setTitle(Html.fromHtml("<font color='#0F7F12'>Thẩm định xong</font>"));
                                popup.getMenu().findItem(R.id.dangThamDinh).setTitle(Html.fromHtml("<font color='#FD9B2D'>Đang thẩm định</font>"));
                                popup.getMenu().findItem(R.id.dangThamDinhHSKhac).setTitle(Html.fromHtml("<font color='#2934FB'>Đang thẩm định HS khác</font>"));
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        switch (item.getItemId()) {
                                            case R.id.chuaThamDinh:
                                                contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 1, 0, -1);
                                                holder.btNha.setText(getThamDinh(0, holder.btNha));
                                                break;
                                            case R.id.thamDinhXong:
//                                            contractPresenter.insertProductAppraiser(contractEntity.getLoanCreditId(), contractEntity.getTypeID(), 1, 1, -1);
//                                            holder.btNha.setText(getThamDinh(1, holder.btNha));
                                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                builder.setMessage("Chọn tình trạng thẩm định.");
                                                builder.setPositiveButton("Đã làm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 1, 1, 1);
                                                        holder.btNha.setText(getThamDinh(1, holder.btNha));
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNegativeButton("Chưa làm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 1, 1, 0);
                                                        holder.btNha.setText(getThamDinh(1, holder.btNha));
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNeutralButton("Huỷ", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                                AlertDialog logout_dialog = builder.create();
                                                logout_dialog.show();
                                                break;
                                            case R.id.dangThamDinh:
                                                contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 1, 2, -1);
                                                holder.btNha.setText(getThamDinh(2, holder.btNha));
                                                break;
                                            case R.id.dangThamDinhHSKhac:
                                                ContractFragment.showDialogSelectContract(
                                                        context,
                                                        0,
                                                        contractEntity,
                                                        contractEntity.getLoancreditid(),
                                                        contractEntity.getFullname() + " (HĐ: " + contractEntity.getLoancreditid() + ")"
                                                );
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                popup.show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                holder.btCongTy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            gps = new GPSTracker(context);
                            if(!gps.canGetLocation()){
                                gps.showSettingsAlert();
                            } else {
                                PopupMenu popup = new PopupMenu(context, holder.btCongTy);
                                popup.inflate(R.menu.popup_menu);
                                popup.getMenu().findItem(R.id.chuaThamDinh).setTitle(Html.fromHtml("<font color='#ff0000'>Chưa thẩm định</font>"));
                                popup.getMenu().findItem(R.id.thamDinhXong).setTitle(Html.fromHtml("<font color='#0F7F12'>Thẩm định xong</font>"));
                                popup.getMenu().findItem(R.id.dangThamDinh).setTitle(Html.fromHtml("<font color='#FD9B2D'>Đang thẩm định</font>"));
                                popup.getMenu().findItem(R.id.dangThamDinhHSKhac).setTitle(Html.fromHtml("<font color='#2934FB'>Đang thẩm định HS khác</font>"));
                                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                    @Override
                                    public boolean onMenuItemClick(MenuItem item) {
                                        switch (item.getItemId()) {
                                            case R.id.chuaThamDinh:
                                                contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 2, 0, -1);
                                                holder.btCongTy.setText(getThamDinh(0, holder.btCongTy));
                                                break;
                                            case R.id.thamDinhXong:
//                                            contractPresenter.insertProductAppraiser(contractEntity.getLoanCreditId(), contractEntity.getTypeID(), 2, 1, -1);
//                                            holder.btCongTy.setText(getThamDinh(1, holder.btCongTy));
                                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                                builder.setMessage("Chọn tình trạng thẩm định.");
                                                builder.setPositiveButton("có làm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 2, 1, 1);
                                                        holder.btCongTy.setText(getThamDinh(1, holder.btCongTy));
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNegativeButton("không làm", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 2, 1, 0);
                                                        holder.btCongTy.setText(getThamDinh(1, holder.btCongTy));
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setNeutralButton("Huỷ", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();
                                                    }
                                                });
                                                AlertDialog logout_dialog = builder.create();
                                                logout_dialog.show();
                                                break;
                                            case R.id.dangThamDinh:
                                                contractPresenter.insertProductAppraiser(contractEntity.getLoancreditid(), contractEntity.getTypeid(), 2, 2, -1);
                                                holder.btCongTy.setText(getThamDinh(2, holder.btCongTy));
                                                break;
                                            case R.id.dangThamDinhHSKhac:
                                                ContractFragment.showDialogSelectContract(
                                                        context,
                                                        1,
                                                        contractEntity,
                                                        contractEntity.getLoancreditid(),
                                                        contractEntity.getFullname() + " (HĐ: " + contractEntity.getLoancreditid() + ")"
                                                );
                                                break;
                                        }
                                        return false;
                                    }
                                });
                                popup.show();
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            } else {
                final WaitTDHSEntity waitTDHSEntity = contractDataChoTD.get(position);
                String ball = "";
                if(waitTDHSEntity.getUtm_source().equals("wolrdcup")) {
                    ball = "⚽";
                }
                holder.tvHoTen.setText(waitTDHSEntity.getFullName() + " (HĐ: " + waitTDHSEntity.getLoanCreditId() + ") " + ball);
                holder.tvNgayThang.setText(Html.fromHtml("<p><font color=\"" + (waitTDHSEntity.getTimeDelay() == 1 ? "red" : "#0F7F12") + "\">" + waitTDHSEntity.getTimeDelayValue() + "</font></p>"));
                holder.tvQuanHuyen.setText(waitTDHSEntity.getNameDistrict());
                holder.tvTongTien.setText(Common.formatMoney(waitTDHSEntity.getTotalMoney()));
                holder.tvTrangThai.setText(Common.getTypeContract(waitTDHSEntity.getTypeID()) + " : " + waitTDHSEntity.getLoanTime() + " ngày");

                if (position % 2 == 0) {
                    holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
                } else {
                    holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

                holder.llNha.setVisibility(View.GONE);
                holder.llCongTy.setVisibility(View.GONE);

                switch (waitTDHSEntity.getTypeID()) {
                    case 1://luong
                        holder.tvTrangThai.setTextColor(Color.parseColor("#0F7F12"));
                        break;
                    case 2://xe may
                        holder.tvTrangThai.setTextColor(Color.parseColor("#ef4223"));
                        break;
                    case 4://ho khau
                        holder.tvTrangThai.setTextColor(Color.RED);
                        break;
                    case 5://xe may
                        holder.tvTrangThai.setTextColor(Color.parseColor("#ef4223"));
                        break;
                    case 6://oto
                        holder.tvTrangThai.setTextColor(Color.BLACK);
                        break;
                    case 7://nha dat
                        holder.tvTrangThai.setTextColor(Color.BLUE);
                        break;
                    case 8://dang ky oto
                        holder.tvTrangThai.setTextColor(Color.BLACK);
                        break;
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gps = new GPSTracker(context);
                        if(!gps.canGetLocation()){
                            gps.showSettingsAlert();
                        } else {
                            Intent intent = new Intent(view.getContext(), ContractDetailActivity.class);
                            intent.putExtra("type", type);
                            intent.putExtra("CustomerID", waitTDHSEntity.getCustomerId());
                            intent.putExtra("LoanID", waitTDHSEntity.getLoanCreditId());
                            intent.putExtra("TypeID", waitTDHSEntity.getTypeID());
                            intent.putExtra("contractChoTDEntity", waitTDHSEntity);
                            intent.putExtra("tabPosition", 0);
                            view.getContext().startActivity(intent);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getThamDinh(int id, Button bt) {
        switch (id) {
            case 0:
                bt.setTextColor(Color.RED);
                return "Chưa thẩm định ▾";
            case 1:
                bt.setTextColor(Color.parseColor("#0F7F12"));
                return "Thẩm định xong ▾";
            default:
                bt.setTextColor(Color.parseColor("#FD9B2D"));
                return "Đang thẩm định ▾";
        }
    }

    @Override
    public int getItemCount() {
        return type==0?contractData.size():contractDataChoTD.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvHoTen)
        TextView tvHoTen;
        @BindView(R.id.tvNgayThang)
        TextView tvNgayThang;
        @BindView(R.id.llNha)
        LinearLayout llNha;
        @BindView(R.id.tvTongTien)
        TextView tvTongTien;
        @BindView(R.id.tvTrangThai)
        TextView tvTrangThai;
        @BindView(R.id.tvQuanHuyen)
        TextView tvQuanHuyen;
        @BindView(R.id.llCongTy)
        LinearLayout llCongTy;
        @BindView(R.id.btNha)
        Button btNha;
        @BindView(R.id.btCongTy)
        Button btCongTy;
        @BindView(R.id.btThamDinhXe)
        Button btThamDinhXe;
        @BindView(R.id.tvCongTyTitle)
        TextView tvCongTyTitle;

        public ContractViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
