package com.supriyalahade.expense_manager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.supriyalahade.expense_manager.Model.FirstPage;
import com.supriyalahade.expense_manager.R;

import java.util.ArrayList;

/**
 * Created by BIDWAI on 27-07-2016.
 */
public class MainAdapter extends BaseAdapter {

    Context context;
    ArrayList<FirstPage> arrayList;
    LayoutInflater mlayoutInflater;



    public MainAdapter(Context context, ArrayList<FirstPage> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        mlayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FirstPage firstpage = new FirstPage();

        convertView= mlayoutInflater.inflate(R.layout.firstpage_layout,parent,false);

        ImageView imageview= (ImageView)convertView.findViewById(R.id.imageView);
        TextView textview = (TextView)convertView.findViewById(R.id.textView);

        imageview.setImageResource(arrayList.get(position).getImage());
        textview.setText(arrayList.get(position).getName());


        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return convertView;
    }
}
