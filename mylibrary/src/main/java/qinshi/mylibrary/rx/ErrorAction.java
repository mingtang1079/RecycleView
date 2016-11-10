package qinshi.mylibrary.rx;

import java.io.IOException;

import qinshi.mylibrary.base.BaseApplication;
import qinshi.mylibrary.utils.ToastUtils;
import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ErrorAction implements Action1<Throwable> {

    @Override
    public void call(Throwable mThrowable) {
        String errorMsg = "";
        if (mThrowable instanceof IOException) {
            errorMsg = "Please check your network status";   //无网络的情况
        } else if (mThrowable instanceof HttpException) { //错误的状态码
            HttpException httpException = (HttpException) mThrowable;
            // such as: "server internal error".
            errorMsg = httpException.getMessage();
        } else {
            errorMsg = "unknown error";
        }
        ToastUtils.showShort(BaseApplication.getInstance(),errorMsg);
    }
}
