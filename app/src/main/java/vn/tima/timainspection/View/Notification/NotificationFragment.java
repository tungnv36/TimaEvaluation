package vn.tima.timainspection.View.Notification;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tima.timainspection.R;
import vn.tima.timainspection.View.Main.MainActivity;

/**
 * Created by tima on 11/25/17.
 */

public class NotificationFragment extends Fragment {

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Danh sách cảnh báo");
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

}
