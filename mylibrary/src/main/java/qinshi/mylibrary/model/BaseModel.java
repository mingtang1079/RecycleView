package qinshi.mylibrary.model;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BaseModel<T> {


    public  int code;
    public  String msg;
    public T t;


    public BaseModel(int mCode, String mMsg, T mT) {
        code = mCode;
        msg = mMsg;
        t = mT;
    }
}
