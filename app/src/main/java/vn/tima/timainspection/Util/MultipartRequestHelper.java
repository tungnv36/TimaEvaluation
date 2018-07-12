package vn.tima.timainspection.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import vn.tima.timainspection.Model.Login.UserInfo;


/**
 * Created by DinhAnh on 7/15/2016.
 */
public class MultipartRequestHelper extends Request<String> {
    private static final String TAG = "MultipartRequestHelper";
    private Response.Listener<String> mListener;
    private Context mContext;
    private ArrayList<String> listGallery;
    private HttpEntity mHttpEntity;
    public static final int TYPE_SEND_PDF = 1;
    public static final int TYPE_SEND_IMAGE = 0;

    int customerId;
    int loanId;

    private  UserInfo userInfo;
    String TypeId;

    public MultipartRequestHelper(String TypeId, int typeSend,int customerId,int loanId,
                                  Context context, String url, ArrayList<String> listGallery, Response.ErrorListener errorListener,
                                  Response.Listener<String> listener) {
        super(Method.POST, url, errorListener);
        userInfo = UserInfo.getInstance();
        this.TypeId = TypeId;
        this.mListener = listener;
        this.mContext = context;
        this.listGallery = listGallery;
        this.customerId = customerId;
        this.loanId = loanId;
        if (typeSend == TYPE_SEND_IMAGE) {
            this.mHttpEntity = buildMultipartEntity();
        } else if (typeSend == TYPE_SEND_PDF) {
            this.mHttpEntity = buildMultipartFilePDF();
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    private Bitmap createImage(Bitmap source) {
        Matrix matrix = new Matrix();

        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    public HttpEntity buildMultipartEntity() {
        ArrayList<File> listFile = new ArrayList<File>();
        for (int i = 0; i < listGallery.size(); i++) {
            try {
                ExifInterface exif = new ExifInterface(listGallery.get(i));
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL);
                Bitmap bitmap = BitmapFactory.decodeFile(listGallery.get(i));
                File fileImg = new File(listGallery.get(i));
                Log.e("fileImg size 1: ", "" + fileImg.length());

                fileImg = Common.compressImage(mContext, fileImg);

//                FileOutputStream out1 = new FileOutputStream(fileImg);
//                switch (orientation) {
//                    case ExifInterface.ORIENTATION_ROTATE_90:
//                        bitmap = rotateImage(bitmap, 90);
//                        break;
//                    case ExifInterface.ORIENTATION_ROTATE_180:
//                        bitmap = rotateImage(bitmap, 180);
//                        break;
//                    case ExifInterface.ORIENTATION_ROTATE_270:
//                        bitmap = rotateImage(bitmap, 270);
//                        break;
//                    case ExifInterface.ORIENTATION_NORMAL:
//                    default:
//                        break;
//                }
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 60, out1);
//                out1.flush();
//                out1.close();



                Log.e("fileImg size 2: ", "" + fileImg.length());
//                File file = new File(listGallery.get(i));
                listFile.add(fileImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buildFileMultipartEntity(listFile);
    }

    public HttpEntity buildMultipartFilePDF() {
        ArrayList<File> listFile = new ArrayList();
        for (int i = 0; i < listGallery.size(); i++) {
            try {
                File file = new File(listGallery.get(i));
                listFile.add(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buildFileMultipartEntity(listFile);
    }

    private HttpEntity buildFileMultipartEntity(ArrayList<File> fileArrayList) {
        MultipartEntityBuilder builder = null;
        String token = userInfo.getUser(mContext).getToken();

        try {
            builder = MultipartEntityBuilder.create();
            Charset chars = Charset.forName("UTF-8");
            builder.setCharset(chars);
            FileBody fileBody = new FileBody(fileArrayList.get(0));
            builder.addPart("image[]", fileBody);
            builder.addTextBody("token", token);
            builder.addTextBody("device", "android");
            builder.addTextBody("CustomerId", customerId + "");
            builder.addTextBody("LoanCreditId", loanId + "");
            builder.addTextBody("UserID", userInfo.getUser(mContext).getID());
            builder.addTextBody("TypeId", TypeId);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

//    @Override
//    protected Map<String, String> getParams() throws AuthFailureError {
//        return super.getParams();
//    }
//
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        Map<String, String> map = new HashMap<>();
//        map.put("Content-Type", "json");
//        return map;
//    }

    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    protected String getParamsEncoding() {
        return "UTF-8";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            mHttpEntity.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String str = new String(response.data, "UTF-8");
            Log.e(TAG, "parseNetworkResponse: " + str);
            return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
        Log.e(TAG, "deliverResponse = " + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (!jsonObject.getString("error").equals("0")) {

            }
        } catch (Exception e) {
        }

    }
}
