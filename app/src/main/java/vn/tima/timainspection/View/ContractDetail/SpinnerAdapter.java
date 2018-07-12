package vn.tima.timainspection.View.ContractDetail;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.R;

/**
 * Created by tima on 12/1/17.
 */

public class SpinnerAdapter extends BaseAdapter {
    Context context;
    String[] countryNames;
    LayoutInflater inflter;

    public SpinnerAdapter(Context applicationContext, String[] countryNames) {
        this.context = applicationContext;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_spinner, null);
        TextView tvItem = (TextView) view.findViewById(R.id.tvItem);
        tvItem.setText(countryNames[i]);
        switch (i) {
            case 0:
                tvItem.setTextColor(Color.RED);
                break;
            case 1:
                tvItem.setTextColor(Color.parseColor("#0F7F12"));
                break;
            case 2:
                tvItem.setTextColor(Color.parseColor("#FD9B2D"));
                break;
        }
        return view;
    }

}
