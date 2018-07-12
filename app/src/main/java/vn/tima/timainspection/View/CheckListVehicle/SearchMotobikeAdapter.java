package vn.tima.timainspection.View.CheckListVehicle;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import vn.tima.timainspection.R;
import vn.tima.timainspection.Util.VNCharacterUtils;

/**
 * Created by My PC on 1/5/2017.
 */

public class SearchMotobikeAdapter extends ArrayAdapter<String> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<String> items;
    private ArrayList<String> itemsAll;
    private ArrayList<String> suggestions;
    private int viewResourceId;

    public SearchMotobikeAdapter(Context context, int viewResourceId, ArrayList<String> items) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<String>) items.clone();
        this.suggestions = new ArrayList<String>();
        this.viewResourceId = viewResourceId;
    }
    public void setItems(ArrayList<String> items){
        this.items = items;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, parent,false);
        }
        String customer = items.get(position);
        if (customer != null) {
            TextView customerNameLabel = (TextView) v.findViewById(R.id.tv_item_search_contract);
            if (customerNameLabel != null) {
                customerNameLabel.setText(customer);
            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                // tach chuoi
                String pattern = "" ;
                try {
                    if(constraint.toString().contains(" ")){
                        String[] part = constraint.toString().split(" ");
                        for(int i=0;i<part.length;i++){
                                pattern += "(?=.*"+ part[i] +")";
                        }
                    }
                    Log.e("Pattern",pattern);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                //end tach chuoi
                for (String customer : itemsAll) {
                    if(pattern.length()==0) {
                        if (VNCharacterUtils.removeAccent(customer.toLowerCase()).contains(VNCharacterUtils.removeAccent(constraint.toString().toLowerCase()))) {
                            suggestions.add(customer);
                        }
                    }
                    else {
                        Pattern p = Pattern.compile(VNCharacterUtils.removeAccent(pattern.toLowerCase()));
                        Matcher m = p.matcher(VNCharacterUtils.removeAccent(customer.toLowerCase()));
                        if(m.find()){
                            suggestions.add(customer);
                        }
                    }

                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<String> filteredList = (ArrayList<String>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (String c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
