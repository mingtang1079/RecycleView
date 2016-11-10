package qinshi.mylibrary.rx;

import qinshi.mylibrary.utils.LogUtils;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class RxHelper {

    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
//        return tObservable -> tObservable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread());

        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> mTObservable) {
                return mTObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };


    }

    /**
     * 可自定义线程
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper(final Scheduler scheduler) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> mTObservable) {
                return mTObservable.subscribeOn(scheduler)
                        .unsubscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };


    }

    /**
     * 自定义处理 Rx 错误线程
     */
    public static void unifiedErrorHandler() {
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                LogUtils.e(e.toString());
            }
        });
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
