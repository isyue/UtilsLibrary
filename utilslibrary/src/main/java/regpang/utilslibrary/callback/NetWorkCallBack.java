package regpang.utilslibrary.callback;

/**
 * 数据请的返回值
 * Created by yue on 2016-05-26.
 */
public interface NetWorkCallBack {
    void success(String result);//请求成功

    void fail(String msg);//请求失败
}
