package regpang.utilslibrary.base.refresh;

import java.util.List;

/**
 * Created by fujinhu on 2017-1-23.
 * @param <T>
 *  更新数据接口
 */
public interface AdapterRefreshInterface<T> {

    public  void doRefresh(List<T> list, int page);

    public void complete();
}
