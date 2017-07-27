package org.kscy.db.kscydatabase.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.model._source;

import java.util.ArrayList;

/**
 * Created by pyh42 on 2016-12-10.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    public ArrayList<_source> mListData = new ArrayList<_source>();

    public ListViewAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.highschool_name = (TextView)convertView.findViewById(R.id.highschool_name);
            holder.student_name = (TextView)convertView.findViewById(R.id.student_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        _source mData = mListData.get(position);

        holder.title.setText(mData.getResearch_name());
        holder.highschool_name.setText(mContext.getResources().getString(R.string.org) + " : " + mData.getOrg());
        holder.student_name.setText(mContext.getResources().getString(R.string.author) + " : " + mData.getResearcher_name());

        return convertView;
    }

    public void addItem(_source source){
        mListData.add(source);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}
