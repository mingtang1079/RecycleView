package qinshi.mylibrary.rx;

import qinshi.mylibrary.model.BaseModel;
import qinshi.mylibrary.network.ErrorCode;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/11/7.
 */
public abstract class SuccessAction<T extends BaseModel> implements Action1<T> {

    @Override
    public void call(T mT) {
        int mCode = mT.code;
        if (mCode != 0) {  //沒有得到正确的数据
            throw new ErrorCode(mT.msg);
        } else
            onSuccess(mT);
    }

    public abstract void onSuccess(T mT);
}
