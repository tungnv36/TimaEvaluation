package vn.tima.timainspection.View.Config;

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

public class ConfigFragment extends Fragment {

    public static ConfigFragment newInstance() {
        ConfigFragment fragment = new ConfigFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Cấu hình");
        return inflater.inflate(R.layout.fragment_config, container, false);
    }

}
