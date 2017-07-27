package org.kscy.db.kscydatabase.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.kscy.db.kscydatabase.R;

import java.util.ArrayList;

/**
 * Created by pyh42 on 2016-12-10.
 */

public class ListViewAdapter extends BaseAdapter {

    private Context mContext = null;
    public ArrayList<ListData> mListData = new ArrayList<ListData>();

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

    public String getId(int position) {
        return mListData.get(position).id;
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

        ListData mData = mListData.get(position);

        holder.title.setText(mData.title);
        holder.highschool_name.setText("학교명 : " + mData.highschool_name);
        holder.student_name.setText("저자 : " + mData.student_name);

        return convertView;
    }

    public void addItem(String title, String highschool_name, String student_name, String id){
        ListData addInfo = null;
        addInfo = new ListData();

        addInfo.title = title;
        addInfo.highschool_name = highschool_name;
        addInfo.student_name = student_name;
        addInfo.id = id;

        mListData.add(addInfo);
    }

    public void remove(int position){
        mListData.remove(position);
        dataChange();
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }
}
