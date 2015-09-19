package com.example.horinoa.mytoolbartemplate;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by horinoA on 2015/09/17.
 */
public class MyListFragment extends ListFragment{

    private MyListitem[] listitems = new MyListitem[]{
            new MyListitem(R.mipmap.ic_launcher,
                    "ああ田いい子",
                    "とてもあああいいい"),
            new MyListitem(R.mipmap.ic_launcher,
                    "うう田ええ子",
                    "とてもうううえええ")
            /*new MyListitem(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher),
                    "おお田かか子",
                    "とてもおおおかかか"),*/
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        MyListAdapter adapter = new MyListAdapter(getActivity().getApplicationContext(),listitems);
        setListAdapter(adapter);
        //getResources().getColor,API23より非推奨のためContextCompat.getColo使用
        Context ct = getActivity().getApplication().getApplicationContext();
        view.setBackgroundColor(ContextCompat.getColor(ct, R.color.material_grey_100));
        return view;
    }

    private class MyListAdapter extends ArrayAdapter<MyListitem>{

        public MyListAdapter(Context context, MyListitem[] objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyListitem data = getItem(position);
            ViewHolder holder;

            // Viewが準備されていなければ新しく生成
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item, null);
                holder = new ViewHolder();
                holder.thumbnail = (com.beardedhen.androidbootstrap.BootstrapCircleThumbnail)convertView.findViewById(R.id.myitemthum);
                holder.name =  (TextView)convertView.findViewById(R.id.myname);
                holder.text = (TextView)convertView.findViewById(R.id.mytext);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.thumbnail.setImage(data.icon);
            holder.name.setText(data.name);
            holder.text.setText(data.txt);
            return convertView;
        }

    }

    class ViewHolder{
        com.beardedhen.androidbootstrap.BootstrapCircleThumbnail thumbnail;
        TextView name;
        TextView text;
    }

    private class MyListitem {
        private int icon;
        private String name;
        private String txt;

        MyListitem(int icon,String name, String txt){
            this.icon = icon;
            this.name = name;
            this.txt = txt;
        }

    }

}
