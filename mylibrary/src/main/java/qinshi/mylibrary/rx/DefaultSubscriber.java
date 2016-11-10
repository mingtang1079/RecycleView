package qinshi.mylibrary.rx;


import android.content.Context;

import qinshi.mylibrary.utils.ErrorInfoUtils;
import qinshi.mylibrary.utils.ToastUtils;
import rx.Subscriber;

/**
 * 通用订阅者,用于统一处理回调
 */
public class DefaultSubscriber<T> extends Subscriber<T> {

    private Context context;

    public DefaultSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onCompleted() {
        // sub
    }

    @Override
    public void onError(Throwable throwable) {
        // 统一处理错误回调，显示Toast
        String errorInfo = ErrorInfoUtils.parseHttpErrorInfo(throwable);
        ToastUtils.showShort(context, errorInfo);
    }

    @Override
    public void onNext(T t) {
        // sub
    }


}
