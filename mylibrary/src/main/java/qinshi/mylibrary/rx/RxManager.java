package qinshi.mylibrary.rx;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class RxManager {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void add(Subscription s) {
        compositeSubscription.add(s);
    }

    public void clear() {
        compositeSubscription.unsubscribe();
    }
}
