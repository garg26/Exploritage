package com.exploritage.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exploritage.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Prashant Goyal on 15-07-2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    private Map<String, String> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, Map<String, String> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(_listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.faq_list_description, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.faq_list_header_desc);
//        View view = convertView.findViewById(R.id.view);
//        view.setBackgroundResource(R.color.orange);
        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.faq_list_header, null);
        }

        TextView txtListHeader = (TextView) convertView.findViewById(R.id.tv_header_name);
        ImageView iv_arrow = (ImageView) convertView.findViewById(R.id.iv_arrow);
        LinearLayout listHeaderLayout = (LinearLayout) convertView.findViewById(R.id.ll_faq_list_header);

        txtListHeader.setText(headerTitle);
        if (isExpanded) {

            listHeaderLayout.setBackgroundResource(R.color.orange);
            txtListHeader.setTextColor(Color.WHITE);
            iv_arrow.setImageResource(R.drawable.arrow_navigate_down_white);

        } else {
            txtListHeader.setTextColor(Color.GRAY);
            listHeaderLayout.setBackgroundResource(R.color.white);
            iv_arrow.setImageResource(R.drawable.arrow_navigate_up_black);
        }


        return convertView;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}