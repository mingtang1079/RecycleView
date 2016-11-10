package qinshi.mylibrary.view.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2015/9/10.
 */
public abstract class AutoAdapter extends MBaseAdapter {

    int layout_id;

    public AutoAdapter(Context context, List<?> list, int layoutID) {
        super(context, list);
        this.layout_id=layoutID;
    }

    public int getLayoutID() {
        return layout_id;
    }


    @Override
    public View getView2(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(getLayoutID(), parent, false);
        }
        getView2(position, convertView, ViewHolder.getViewHolder(convertView));
        return convertView;
    }


    public abstract void getView2(int position, View v, ViewHolder vh);



}
