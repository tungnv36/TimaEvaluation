package vn.tima.timainspection.listener;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * Created by My PC on 1/4/2017.
 */

public class MoneyTextWatcherListener implements TextWatcher {
    private DecimalFormat formatter = new DecimalFormat("###,###,###,###");
    private EditText ed_text;
    public MoneyTextWatcherListener(EditText ed_text){
        this.ed_text = ed_text;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            if(s.length()>0) {
                ed_text.removeTextChangedListener(this);
                String yourFormattedString = formatter.format(Double.parseDouble(s.toString().replaceAll("[,.]", "")));
                ed_text.setText(yourFormattedString);
                ed_text.setTextKeepState(yourFormattedString);
                Selection.setSelection(ed_text.getText(), yourFormattedString.length());
                ed_text.addTextChangedListener(this);
            }
            else {
                ed_text.setText("0");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
