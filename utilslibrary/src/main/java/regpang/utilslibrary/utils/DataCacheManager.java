package regpang.utilslibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class DataCacheManager {

	private static final int READ_SIZE_FINISHED = 1001;
	private static final int READ_SIZE_FAIL = 1002;
	private static final int CLEAN_FINISHED = 1003;
	private static final int CLEAN_FAIL = 1004;

	private static DataCacheManager mInstance;
	private Runnable mRunnable;
	private Handler mHandler;

	private CacheSizeCallBack mCacheSizeCallBack;
	private CacheCleanCallBack mCacheCleanCallBack;

	public interface CacheCleanCallBack {
		void cleanFinished();

		void cleanFail();
	}

	public interface CacheSizeCallBack {
		void onSize(String sizeString);

		void readFail();
	}

	public static DataCacheManager getInstance() {
		if (mInstance == null) {
			mInstance = new DataCacheManager();
		}
		return mInstance;
	}

	private DataCacheManager() {
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case READ_SIZE_FINISHED:
					if (mCacheSizeCallBack != null) {
						mCacheSizeCallBack.onSize((String) msg.obj);
					}
					break;
				case READ_SIZE_FAIL:
					if (mCacheSizeCallBack != null) {
						mCacheSizeCallBack.readFail();
					}
					break;
				case CLEAN_FINISHED:
					if (mCacheCleanCallBack != null) {
						mCacheCleanCallBack.cleanFinished();
					}
					break;
				case CLEAN_FAIL:
					if (mCacheCleanCallBack != null) {
						mCacheCleanCallBack.cleanFail();
					}
					break;
				default:
					break;
				}
			}
		};
	}

	public synchronized void readCacheSize(final Context context,
			CacheSizeCallBack callBack) {
		mCacheSizeCallBack = callBack;
		mRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					String size = com.learnknow.teacher.util.DataCleanUtil.getCacheSize(context
							.getExternalCacheDir());
					mHandler.obtainMessage(READ_SIZE_FINISHED, 0, 0, size)
							.sendToTarget();
				} catch (Exception e) {
					e.printStackTrace();
					mHandler.sendEmptyMessage(READ_SIZE_FAIL);
				}
			}
		};
		exec();
	}

	public synchronized void cleanCache(final Context context,
			CacheCleanCallBack callBack) {
		mCacheCleanCallBack = callBack;
		mRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					com.learnknow.teacher.util.DataCleanUtil.cleanExternalCache(context);
					mHandler.sendEmptyMessage(CLEAN_FINISHED);
				} catch (Exception e) {
					e.printStackTrace();
					mHandler.sendEmptyMessage(CLEAN_FAIL);
				}
			}
		};
		exec();
	}

	private void exec() {
		new Thread(mRunnable).start();
	}
}
