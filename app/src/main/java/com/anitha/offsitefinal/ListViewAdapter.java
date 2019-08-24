package com.anitha.offsitefinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class ListViewAdapter  extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Names> NamesList = null;
    private ArrayList<Names> arraylist;

    public ListViewAdapter(Context context, ArrayList<Names>NamesList) {
        mContext = context;
        this.NamesList = NamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Names>();
        this.arraylist.addAll(NamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return NamesList.size();
    }

    @Override
    public Names getItem(int position) {
        return NamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.nameLabel);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(NamesList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        NamesList.clear();
        if (charText.length() == 0) {
            NamesList.addAll(arraylist);
        } else {
            for (Names wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    NamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
