package com.example.horinoa.mytoolbartemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by horinoA on 2015/01/01.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem> {

        public DrawerAdapter(Context context, DrawerItem[] objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DrawerItem data = getItem(position);
            ViewHolder holder;

            // Viewが準備されていなければ新しく生成
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.drawer_list_item, null);
                holder = new ViewHolder();
                holder.iconT = (com.beardedhen.androidbootstrap.FontAwesomeText)convertView.findViewById(R.id.item_icon);
                holder.title =  (TextView)convertView.findViewById(R.id.item_title);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.iconT.setIcon(data.mIcon);
            holder.title.setText(data.mTitle);

            return convertView;
        }

    class ViewHolder{
        com.beardedhen.androidbootstrap.FontAwesomeText iconT;
        TextView title;
    }

}



