package vn.tima.timainspection.listener;

/**
 * Created by hoangngoc on 8/16/16.
 */

public abstract class OnResponse<T, TF> {
    public void onStart(){}
    public void onFinish(){}
    public abstract void onResponseSuccess(String tag, T rs, TF extraData);
    public abstract void onResponseError(String tag, String message);
}
