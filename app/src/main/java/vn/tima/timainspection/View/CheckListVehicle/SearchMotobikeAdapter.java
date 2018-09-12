package vn.tima.timainspection.View.CheckListVehicle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import vn.tima.timainspection.R;

/**
 * Created by My PC on 1/5/2017.
 */

public class SearchMotobikeAdapter extends RecyclerView.Adapter<SearchMotobikeAdapter.ViewHolder> implements Filterable {

    private List<String> mProvinceEntities;
    private List<String> mProvinceEntitiesFiltered;
    private Context mContext;

    public SearchMotobikeAdapter(Context context, List<String> contactEntities) {
        mProvinceEntities = contactEntities;
        mProvinceEntitiesFiltered = contactEntities;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dictionary, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String dic = mProvinceEntitiesFiltered.get(position);

        holder.tvDictionary.setText(dic);
    }

    @Override
    public int getItemCount() {
        return mProvinceEntitiesFiltered == null ? 0 : mProvinceEntitiesFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mProvinceEntitiesFiltered = mProvinceEntities;
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String row : mProvinceEntities) {
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    mProvinceEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mProvinceEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mProvinceEntitiesFiltered = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDictionary)
        TextView tvDictionary;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

//public class SearchMotobikeAdapter extends ArrayAdapter<String> {
//    private final String MY_DEBUG_TAG = "CustomerAdapter";
//    private ArrayList<String> items;
//    private ArrayList<String> itemsAll;
//    private ArrayList<String> suggestions;
//    private int viewResourceId;
//
//    public SearchMotobikeAdapter(Context context, int viewResourceId, ArrayList<String> items) {
//        super(context, viewResourceId, items);
//        this.items = items;
//        this.itemsAll = items;
//        this.suggestions = new ArrayList<String>();
//        this.viewResourceId = viewResourceId;
//    }
//    public void setItems(ArrayList<String> items){
//        this.itemsAll = items;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = convertView;
//        if (v == null) {
//            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            v = vi.inflate(viewResourceId, parent,false);
//        }
//        String customer = itemsAll.get(position);
//        if (customer != null) {
//            TextView customerNameLabel = (TextView) v.findViewById(R.id.tv_item_search_contract);
//            if (customerNameLabel != null) {
//                customerNameLabel.setText(customer);
//            }
//        }
//        return v;
//    }
//
//    @Override
//    public Filter getFilter() {
//        return nameFilter;
//    }
//
//    Filter nameFilter = new Filter() {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            String charString = constraint.toString();
//            if (charString.isEmpty()) {
//                itemsAll = items;
//            } else {
//                ArrayList<String> filteredList = new ArrayList<>();
//                for (String row : items) {
//
//                    // name match condition. this might differ depending on your requirement
//                    // here we are looking for name or phone number match
//                    if (row.toLowerCase().contains(charString.toLowerCase())) {
//                        filteredList.add(row);
//                    }
//                }
//
//                itemsAll = filteredList;
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = itemsAll;
//            return filterResults;
//
////            if(constraint != null) {
////                suggestions.clear();
////                // tach chuoi
////                String pattern = "" ;
////                try {
////                    if(constraint.toString().contains(" ")){
////                        String[] part = constraint.toString().split(" ");
////                        for(int i=0;i<part.length;i++){
////                                pattern += "(?=.*"+ part[i] +")";
////                        }
////                    }
////                    Log.e("Pattern",pattern);
////                }
////                catch (Exception e){
////                    e.printStackTrace();
////                }
////                //end tach chuoi
////                for (String customer : itemsAll) {
////                    if(pattern.length()==0) {
////                        if (VNCharacterUtils.removeAccent(customer.toLowerCase()).contains(VNCharacterUtils.removeAccent(constraint.toString().toLowerCase()))) {
////                            suggestions.add(customer);
////                        }
////                    }
////                    else {
////                        Pattern p = Pattern.compile(VNCharacterUtils.removeAccent(pattern.toLowerCase()));
////                        Matcher m = p.matcher(VNCharacterUtils.removeAccent(customer.toLowerCase()));
////                        if(m.find()){
////                            suggestions.add(customer);
////                        }
////                    }
////
////                }
////                FilterResults filterResults = new FilterResults();
////                filterResults.values = suggestions;
////                filterResults.count = suggestions.size();
////                return filterResults;
////            } else {
////                return new FilterResults();
////            }
//        }
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            itemsAll = (ArrayList<String>) results.values;
//            notifyDataSetChanged();
////            ArrayList<String> filteredList = (ArrayList<String>) results.values;
////            if(results != null && results.count > 0) {
////                clear();
////                for (String c : filteredList) {
////                    add(c);
////                }
////                notifyDataSetChanged();
////            }
//        }
//    };
//
//}
