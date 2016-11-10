package qinshi.mylibrary.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import qinshi.mylibrary.R;

public class LoadingFooter {

    private View mLoadingFooter;
    private TextView mLoadingText;
    private State mState = State.Idle;


    public static enum State {
        Idle, TheEnd, Loading
    }

    public LoadingFooter(Context context) {
        mLoadingFooter = LayoutInflater.from(context).inflate(
                R.layout.loading_footer, null);
        mLoadingFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mLoadingText = (TextView) mLoadingFooter.findViewById(R.id.footerText);

        setState(State.Idle);
    }

    public View getView() {
        return mLoadingFooter;
    }

    public State getState() {
        return mState;
    }

    public void setState(final State state, long delay) {
        mLoadingFooter.postDelayed(new Runnable() {
            @Override
            public void run() {
                setState(state);
            }
        }, delay);
    }

    public void setState(State status) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {
            case Loading:
                mLoadingFooter.setVisibility(View.VISIBLE);
                mLoadingText.setText("Loading");

                break;
            case TheEnd:

                mLoadingText.setText("No More");
                mLoadingFooter.setVisibility(View.VISIBLE);
                break;
            default:

                mLoadingFooter.setVisibility(View.GONE);
                break;
        }
    }
}
