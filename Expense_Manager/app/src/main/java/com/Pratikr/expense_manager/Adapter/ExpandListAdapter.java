package com.supriyalahade.expense_manager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.supriyalahade.expense_manager.Model.Data_Details;
import com.supriyalahade.expense_manager.R;

import java.util.HashMap;
import java.util.List;



/**
 * Created by BIDWAI on 15-08-2016.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String,List<Data_Details>> listDataChild;
    private List<Data_Details> dataDetailsList;



    public ExpandListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Data_Details>> listDataChild) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {



        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //final String dateText = (String)getChild(groupPosition,childPosition);
        // final String itemText = (String)getChild(groupPosition,childPosition);
        // final String costText = (String)getChild(groupPosition,childPosition);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);

        }

        TextView date = (TextView)convertView.findViewById(R.id.date_textView);
        date.setText(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getDate());
        TextView item = (TextView)convertView.findViewById(R.id.item_textView);
        item.setText(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getItem());
        TextView cost = (TextView)convertView.findViewById(R.id.cost_textView);
        cost.setText(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getCost());

        // date.setText(dateText);
        // item.setText(itemText);
        //cost.setText(costText);




        return convertView;

    }



    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();
    }


    @Override
    public Object getGroup(int groupPosition) {




        return this.listDataHeader.get(groupPosition);
    }



    @Override
    public long getGroupId(int groupPosiiton) {
        return groupPosiiton;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }



    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final String headerText = (String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_headers,null);

        }

        TextView header = (TextView)convertView.findViewById(R.id.header_textView);
        header.setText(headerText);

        return convertView;
    }




    @Override
    public boolean hasStableIds() {
        return false;
    }




    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
