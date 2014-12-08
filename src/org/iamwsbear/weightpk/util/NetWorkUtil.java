package org.iamwsbear.weightpk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
/**
 * 网络综合管理
 * @author iamwsbear@gmail.com
 *
 */
public class NetWorkUtil {

    public static final int NET_INVALID = 0;

    public static final int NET_WIFI = 1;

    public static final int NET_2G = 2;

    public static final int NET_3G = 3;

    public static final int UNKNOW_NET = 4;
    
    /**
     * 获取网络连接状态
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isConnected()
                        || (networkInfo.isAvailable() && networkInfo
                                .isConnectedOrConnecting());
            }
        }
        return false;
    }

    /**
     * 获取网络连接类型
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        int netType = UNKNOW_NET;
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (networkInfo != null) {
                int type = networkInfo.getType();
                if (type == ConnectivityManager.TYPE_WIFI
                        || type == ConnectivityManager.TYPE_WIMAX) {
                    netType = NET_WIFI;
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    int subType = networkInfo.getSubtype();
                    if (subType == TelephonyManager.NETWORK_TYPE_1xRTT
                            || subType == TelephonyManager.NETWORK_TYPE_UMTS
                            || subType == TelephonyManager.NETWORK_TYPE_EHRPD
                            || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                            || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                            || subType == TelephonyManager.NETWORK_TYPE_EVDO_B
                            || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                            || subType == TelephonyManager.NETWORK_TYPE_HSPA
                            || subType == TelephonyManager.NETWORK_TYPE_HSPAP
                            || subType == TelephonyManager.NETWORK_TYPE_HSUPA
                            || subType == TelephonyManager.NETWORK_TYPE_LTE) {
                        netType = NET_3G;
                    } else if (subType == TelephonyManager.NETWORK_TYPE_GPRS
                            || subType == TelephonyManager.NETWORK_TYPE_CDMA
                            || subType == TelephonyManager.NETWORK_TYPE_EDGE
                            || subType == TelephonyManager.NETWORK_TYPE_IDEN) {
                        netType = NET_2G;
                    }
                }
            }else{
                netType = NET_INVALID;
            }
        }
        return netType;
    }

}
