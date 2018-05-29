package regpang.utilslibrary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;


/**
 * @author xmqian
 * @date 2018/1/15 0015
 * @link(https://blog.csdn.net/lxping51/article/details/70145322)
 * @desc 网络监听, 及网络状态获取, 以下是涉及的权限
 * <uses-permission android:name="android.permission.INTERNET" />
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 * <uses-permission android:name="android.permission.CHANGE_NETWORK_STATE" />
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
 */
public class PhoneNetStateUtils {

    /**
     * 网络状态类型
     */
    enum NetState {
        WIFI("wifi", 1), CDMA("2G", 2), UMTS("3G", 3), LTE("4G", 4), UNKOWN("unkonw", 5);
        private int state;
        private String type;

        NetState(String type, int state) {
            this.state = state;
            this.type = type;
        }
    }


    private static NetStateBroadcastReceiver netStateBroadcastReceiver;

    public static BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int s = getWifiState(context);
            String toast;
            if (s < -50) {
                toast = "Wifi信号弱";
            } else {
                toast = "Wifi信号良好";
            }
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 注册网络变化监听
     *
     * @param context
     * @param netChangeListener
     */
    public static void registerNetState(Context context, NetChangeListener netChangeListener) {
        if (netStateBroadcastReceiver == null) {
            netStateBroadcastReceiver = new NetStateBroadcastReceiver();
        }
        netStateBroadcastReceiver.addListener(netChangeListener);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(netStateBroadcastReceiver, filter);
        //是wifi的情况下，注册wifi信号监听
        if (getCurrentNetType(context).equals(NetState.WIFI.type)) {
            context.registerReceiver(wifiStateReceiver, new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
        } else {
            context.unregisterReceiver(wifiStateReceiver);
        }
    }

    /**
     * 取消注册网络变化监听
     *
     * @param context
     */
    public static void unregisterNetState(Context context) {
        context.unregisterReceiver(netStateBroadcastReceiver);
        if (getCurrentNetType(context).equals(NetState.WIFI.type)) {
            context.unregisterReceiver(wifiStateReceiver);
        }
    }

    /**
     * 获取当前的网络状态
     *
     * @param context
     * @return
     */
    public static String getCurrentNetType(Context context) {
        // String type = "unknown";
        int state = NetState.UNKOWN.state;
        String type = NetState.UNKOWN.type;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info == null) {
            // type = "unknown";
            state = NetState.UNKOWN.state;
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            // type = "wifi";
            state = NetState.WIFI.state;
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                // type = "2g";
                state = NetState.CDMA.state;
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                // type = "3g";
                state = NetState.UMTS.state;
            } else {// LTE是3g到4g的过渡，是3.9G的全球标准 if (subType ==
                // TelephonyManager.NETWORK_TYPE_LTE)
                // type = "4g";
                state = NetState.LTE.state;
            }
        }
        switch (state) {
            case 1:
                type = NetState.WIFI.type;
                break;
            case 2:
                type = NetState.CDMA.type;
                break;
            case 3:
                type = NetState.UMTS.type;
                break;
            case 4:
                type = NetState.LTE.type;
                break;
            case 5:
            default:
                type = NetState.UNKOWN.type;
                break;
        }
        return type;
    }

    /**
     * 获取WiFi的信号强度
     * 这里得到信号强度就靠wifiinfo.getRssi()这个方法。
     * 得到的值是一个0到-100的区间值，是一个int型数据，
     * 其中0到-50表示信号最好，-50到-70表示信号偏差，
     * 小于-70表示最差，有可能连接不上或者掉线，一般Wifi已断则值为-200
     *
     * @param context
     * @return
     */
    public static int getWifiState(Context context) {
        WifiManager wifi_service = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifi_service.getConnectionInfo();
        int rssi = wifiInfo.getRssi();
//        String s = String.valueOf(rssi) + "dBm";
        return rssi;
    }

    /**
     * 判断当前网络是否为wifi
     *
     * @param mContext
     * @return 是：true;不是：false
     */

    public static boolean isWifi(Context mContext) {
        if (getCurrentNetType(mContext).equals(NetState.WIFI.type)) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return 可用：true,不可用：false
     */
    public static boolean isNetWorkAvailble(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if ((info != null) && (info.isConnected())) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 网络变化广播接收器
     */
    public static class NetStateBroadcastReceiver extends BroadcastReceiver {
        private NetChangeListener netChangeListener;

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                String netWorkState = getCurrentNetType(context);
                if (netChangeListener != null) {
                    netChangeListener.onNetStateChange(netWorkState);
                }
            }
        }

        public void addListener(NetChangeListener netChangeListener) {
            this.netChangeListener = netChangeListener;
        }
    }

    /**
     * 网络切换接口
     */
    public interface NetChangeListener {
        void onNetStateChange(String netWorkState);
    }
}
