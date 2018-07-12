package vn.tima.timainspection.View.Contract;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Entity.ContractEntity;
import vn.tima.timainspection.Model.Entity.WaitTDHSEntity;
import vn.tima.timainspection.Presenter.Comment.CommentPresenter;
import vn.tima.timainspection.Presenter.Contract.ContractPresenter;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.GeoPoint;
import vn.tima.timainspection.View.Comment.CommentHouseFragment;
import vn.tima.timainspection.View.ContractDetail.ContractDetailActivity;

//import android.widget.PopupMenu;

/**
 * Created by tima on 11/25/17.
 */

public class SelectContractAdapter extends RecyclerView.Adapter<SelectContractAdapter.ContractViewHolder> {

    private List<ContractEntity> contractData;

    private Context context;
    private ContractPresenter contractPresenter;
    private boolean isSelectNha = false;
    private int isHouse;
    private int loanID;
    private String content;

    public void setContractData(List<ContractEntity> contractData) {
        this.contractData = contractData;
    }

    public SelectContractAdapter(Context context, List<ContractEntity> contractData, ContractPresenter contractPresenter, int isHouse, int loanID, String content) {
        this.contractData = contractData;
        this.context = context;
        this.contractPresenter = contractPresenter;
        this.isHouse = isHouse;
        this.loanID = loanID;
        this.content = content;
    }

    @Override
    public ContractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contract, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContractViewHolder holder, int position) {
        try {
            final ContractEntity contractEntity = contractData.get(position);
            holder.tvHoTen.setText(contractEntity.getFullname() + " (HĐ: " + contractEntity.getLoancreditid() + ")");
            holder.tvNgayThang.setText(Html.fromHtml("<p><font color=\"" + (contractEntity.getTimedelay() == 1 ? "red" : "#0F7F12") + "\">" + contractEntity.getTimedelayvalue() + "</font></p>"));
            holder.tvQuanHuyen.setText(contractEntity.getNamedistrict());
            holder.tvTongTien.setText(Common.formatMoney(contractEntity.getTotalmoney()));
            holder.tvTrangThai.setText(Common.getTypeContract(contractEntity.getTypeid()) + " : " + contractEntity.getLoantime() + " ngày");

            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#EEEEEE"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }

            holder.llNha.setVisibility(View.GONE);
            holder.llCongTy.setVisibility(View.GONE);

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
                    ContractFragment.dialog.dismiss();
                    ContractFragment.commentPresenter.addComment(
                            loanID,
                            "<p>Đang thẩm định hồ sơ <strong>" + contractEntity.getFullname() + " (HĐ: " + contractEntity.getLoancreditid() + ")</strong></p>",
                            Common.getDeviceName(),
                            "0.0",
                            "0.0",
                            "",
                            "");
//                    AlertDialog.Builder builder;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                        builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
//                    } else {
//                        builder = new AlertDialog.Builder(context);
//                    }
//                    builder.setTitle("Thông báo!")
//                            .setMessage("Bạn có chắc chắn muốn comment không?")
//                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    ContractFragment.commentPresenter.addComment(
//                                            loanID,
//                                            "<p>Đang thẩm định hồ sơ <strong>" + content + "</strong></p>",
//                                            Common.getDeviceName(),
//                                            "0.0",
//                                            "0.0",
//                                            "",
//                                            "");
//                                }
//                            })
//                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // do nothing
//                                }
//                            })
//                            .setIcon(android.R.drawable.ic_dialog_alert)
//                            .show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return contractData.size();
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

        public ContractViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
