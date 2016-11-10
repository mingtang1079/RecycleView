package qinshi.mylibrary.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

import qinshi.mylibrary.utils.LogUtils;
import qinshi.mylibrary.view.LoadingFooter;

/**
 * @author zhaokaiqiang
 */
public class AutoLoadListView extends ListView implements
        AbsListView.OnScrollListener {

    private LoadingFooter mLoadingFooter;

    private OnLoadMoreListener mLoadNextListener;

    public AutoLoadListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public AutoLoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoLoadListView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mLoadingFooter = new LoadingFooter(getContext());
        addFooterView(mLoadingFooter.getView());
        setOnScrollListener(this);
    }

    public void setOnLoadNextListener(OnLoadMoreListener listener) {
        mLoadNextListener = listener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (mLoadingFooter.getState() == LoadingFooter.State.Loading
                || mLoadingFooter.getState() == LoadingFooter.State.TheEnd) {
            return;
        }
        LogUtils.d("fitst--->",String.valueOf(firstVisibleItem));
        LogUtils.d("visible---->", String.valueOf(visibleItemCount));
        if (firstVisibleItem + visibleItemCount >= totalItemCount
                && totalItemCount != 0
                && totalItemCount != (getHeaderViewsCount() + getFooterViewsCount())
                && mLoadNextListener != null) {
            mLoadingFooter.setState(LoadingFooter.State.Loading);
            mLoadNextListener.onLoadMore();
        }
    }

    public void setState(LoadingFooter.State status) {
        mLoadingFooter.setState(status);
    }

    public void setState(LoadingFooter.State status, long delay) {
        mLoadingFooter.setState(status, delay);
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }

}
