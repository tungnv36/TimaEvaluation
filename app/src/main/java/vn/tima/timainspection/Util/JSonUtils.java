package vn.tima.timainspection.Util;

import org.json.JSONObject;

/**
 * Created by My PC on 1/5/2017.
 */

public class JSonUtils {
    public static boolean checkSuccess(JSONObject jsonObject){
        try {
            if(jsonObject.getInt(Constant.TAG_STATUS) == 1 && jsonObject.getInt(Constant.TAG_CODE) == 1)
                return true;
            else
                return false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static String getMessage(JSONObject jsonObject){
        try {
            return  jsonObject.getString(Constant.TAG_MESSAGE);
//            return  jsonObject.getString(Constant.TAG_MESSAGE)+Constant.TAG_TOKEN+jsonObject.getInt(Constant.TAG_STATUS) +Constant.TAG_TOKEN+jsonObject.getInt(Constant.TAG_CODE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
