package vn.tima.timainspection.View.PostContract;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.Model.Contract.ContractRequest;
import vn.tima.timainspection.Model.Entity.LibraryImage;
import vn.tima.timainspection.Model.Entity.PostImage;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.Common;
import vn.tima.timainspection.Util.DialogUtils;
import vn.tima.timainspection.View.Dialog.ViewImageDialog;
import vn.tima.timainspection.listener.OnResponse;

/**
 * Created by DinhAnh on 11/7/2016.
 */

public class PostImageAdapter extends RecyclerView.Adapter<PostImageAdapter.MyViewHolder> {
    public static final String TAG = PostImageAdapter.class.getSimpleName();
    private ArrayList<PostImage> listMenu = new ArrayList<>();
    private Activity mActivity;

    private Map<Integer, ArrayList<LibraryImage>> mapListImage = new HashMap<>();
    private Map<Integer, LibraryImageAdapter> mapLibraryImageAdapter = new HashMap<>();
    private Map<Integer, RecyclerView> mapRecyclerView = new HashMap<>();
    String loanId;
    public Map<Integer, ArrayList<LibraryImage>> getMapListImage() {
        return mapListImage;
    }

    public PostImageAdapter(Activity mActivity, String loanId) {
        this.mActivity = mActivity;
        this.loanId = loanId;
    }

    public void setListMenu(ArrayList<PostImage> listMenu) {
        this.listMenu = listMenu;
    }

    public interface OnTypePostUploadSuccess {
        void onTypeIdSuccess(int type);
    }

    public interface OnclickTitleListener {
        void onClick(PostImage data);
    }

    public OnTypePostUploadSuccess typePostUploadSuccess;

    public OnclickTitleListener listener;

    public void setTypePostUploadSuccess(OnTypePostUploadSuccess typePostUploadSuccess) {
        this.typePostUploadSuccess = typePostUploadSuccess;
    }

    public void setListener(OnclickTitleListener listener) {
        this.listener = listener;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post_contract_image, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PostImage data = listMenu.get(position);
        holder.tv_title.setText(data.getName());
        holder.tv_stt.setText(String.valueOf(position + 1));
        initRecyclerView(holder.rv_image, data);
        holder.btn_post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: " + position + data.getTypePost());
                listener.onClick(data);
            }
        });
        getImageVouchers(String.valueOf(data.getTypePost()), data.getTypePost());
    }

    private void initRecyclerView(RecyclerView rv, final PostImage data) {
        final LibraryImageAdapter adapterImageChose = new LibraryImageAdapter();
        final ArrayList<LibraryImage> listImage = new ArrayList<>();

        mapLibraryImageAdapter.put(data.getTypePost(), adapterImageChose);
        mapListImage.put(data.getTypePost(), listImage);
        mapRecyclerView.put(data.getTypePost(), rv);

        mapLibraryImageAdapter.get(data.getTypePost()).setListImage(listImage);
        Common.setHorizontalRecyclerView(mActivity, mapRecyclerView.get(data.getTypePost()));
        mapRecyclerView.get(data.getTypePost()).setAdapter(adapterImageChose);
        mapLibraryImageAdapter.get(data.getTypePost()).setShowDelete(true);
        mapLibraryImageAdapter.get(data.getTypePost()).setListener(new LibraryImageAdapter.OnclickListener() {
            @Override
            public void onClick(int position) {
                ViewImageDialog dialog = new ViewImageDialog(mActivity, listImage, position);
                dialog.setCancelable(true);
                dialog.showDialog();
            }

            @Override
            public void onButtonDeleteListener(final int position) {
                DialogUtils.showExitDialog(mActivity, mActivity.getString(R.string.delete_image), new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickSuccess() {
                        ContractRequest.getInstance(mActivity).deleteImage(loanId,listImage.get(position).getID(), new OnResponse<String, String>() {
                            @Override
                            public void onResponseSuccess(String tag, String rs, String extraData) {
                                if (!Common.checkToken(rs, mActivity)) {
                                    return;
                                }
                                listImage.remove(position);
                                notifyDataSetChangeImageChose(data.getTypePost());
                                DialogUtils.showAlertDialog(mActivity,"Thông báo","Bạn đã xóa ảnh thành công");
                            }

                            @Override
                            public void onResponseError(String tag, String message) {

                            }
                        });
                    }
                });
            }
        });
    }

    public void notifyDataSetChangeImageChose(final int type) {
        Log.e(TAG, "notifyDataSetChangeImageChose type: " + type);
        mapLibraryImageAdapter.get(type).setListImage(mapListImage.get(type));
        mapLibraryImageAdapter.get(type).notifyDataSetChanged();
    }

    // get image last update
    private void getImageVouchers(String typeId, final int typePost) {
        String loanCreditId = String.valueOf(loanId);
        ContractRequest.getInstance(mActivity).getListImage(typeId, loanCreditId, new OnResponse<String, List<LibraryImage>>() {
            @Override
            public void onResponseSuccess(String tag, String rs, List<LibraryImage> extraData) {
                if (extraData.size() > 0 && typePostUploadSuccess != null) {
                    typePostUploadSuccess.onTypeIdSuccess(typePost);
                }
                mapListImage.get(typePost).addAll(extraData);
                notifyDataSetChangeImageChose(typePost);
            }

            @Override
            public void onResponseError(String tag, String message) {

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: "+ listMenu.size());
        return listMenu.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_stt)
        TextView tv_stt;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.rv_image)
        RecyclerView rv_image;
        @BindView(R.id.btn_post_image)
        Button btn_post_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
