package qinshi.mylibrary.view.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import qinshi.mylibrary.view.LoadingFooter;

/**
 * Created by Administrator on 2015/9/14.
 */
@Deprecated
public abstract class EndlessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public View header;
    private LoadingFooter mLoadingFooter;
    public View footer;
    public Context context;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_OPTIONS = 1;
    public static final int TYPE_FOOTER = 2;

    public EndlessAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        dataSet = list;
        mLoadingFooter = new LoadingFooter(context);
        footer=mLoadingFooter.getView();
    }

    public List<String> dataSet;

    public View getHeader() {
        return header;
    }

    public View getFooter() {
        return footer;
    }

    public void setHeader(View header) {
        this.header = header;
    }

    public void setFooter(View footer) {
        this.footer = footer;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == dataSet.size() + 1) {
            return TYPE_FOOTER;
        } else {
            return TYPE_OPTIONS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (i == 0) {
            return new HeaderView(header);
        } else if (i == 1) {
            return getViewHolder(viewGroup);
        } else if (i == 2) {
            return new FooterView(footer);
        }

        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int type = getItemViewType(i);
        if (type == TYPE_OPTIONS) {
            bindVh(viewHolder, i);
        }
        if (type == TYPE_HEADER) {
            HeaderView h = (HeaderView) viewHolder;
        }
        if (type == TYPE_FOOTER) {
            FooterView h = (FooterView) viewHolder;
        }

    }

    public abstract RecyclerView.ViewHolder getViewHolder(ViewGroup viewGroup);

    public abstract void bindVh(RecyclerView.ViewHolder viewHolder, int i);

    @Override
    public int getItemCount() {
        return dataSet.size() + 2;
    }

    public class HeaderView extends RecyclerView.ViewHolder {

        public HeaderView(View itemView) {
            super(itemView);
        }
    }


    public class FooterView extends RecyclerView.ViewHolder {

        public FooterView(View itemView) {
            super(itemView);
        }
    }



}
