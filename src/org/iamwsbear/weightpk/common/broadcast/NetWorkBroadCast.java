package org.iamwsbear.weightpk.common.broadcast;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

import org.iamwsbear.weightpk.util.NetWorkUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.google.common.collect.Lists;

/**
 * 注册网络变化通知广播
 * 
 * @author iamwsbear@gmail.com
 *
 */

public class NetWorkBroadCast extends BroadcastReceiver {

    private static final String TAG = NetWorkBroadCast.class.getName();

    private static NetWorkBroadCast netWorkBroadCast;
    
    private Handler handler = new Handler();

    public NetWorkBroadCast(Context context) {
        super();
    }

    public static synchronized NetWorkBroadCast instance(Context context) {
        if (netWorkBroadCast == null) {
            netWorkBroadCast = new NetWorkBroadCast(context);
        }
        return netWorkBroadCast;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "THE NET WORK IS CHANGE");
        Log.d(TAG, "the connect is " + NetWorkUtil.isNetworkConnected(context));
        String notice = "";
        switch (NetWorkUtil.getNetworkType(context)) {
        case NetWorkUtil.NET_INVALID:
            notice = "无网络";
            break;
        case NetWorkUtil.NET_WIFI:
            notice = "WIFI------";
            break;
        case NetWorkUtil.NET_2G:
            notice = "2G-------";
            break;
        case NetWorkUtil.NET_3G:
            notice = "3G----------";
            break;
        default:
            break;
        }
        Log.d(TAG, notice);
        notifyAllListeners(NetWorkUtil.getNetworkType(context));
    }

    /**
     * 定义监听接口用于通知用户网络变化的情况
     * 
     * @author iamwsbear@gmail.com
     *
     */
    public interface NetWorkChangeListener {
        public void onNetWorkStateChangeListener(int netType);
    }

    private static List<WeakReference<NetWorkChangeListener>> netWorkListenerList = Lists
            .newArrayList();

    public static void addNetWorkChangeListener(NetWorkChangeListener o) {
        if (o == null || netWorkListenerList == null) {
            return;
        } else {
            synchronized (netWorkListenerList) {
                for (WeakReference<NetWorkChangeListener> ref : netWorkListenerList) {
                    if (o.equals(ref.get())) {
                        return;
                    }
                }
                WeakReference<NetWorkChangeListener> ref = new WeakReference<NetWorkChangeListener>(
                        o);
                netWorkListenerList.add(ref);
            }
        }
    }

    public static void removeNetWorkChangeListener(NetWorkChangeListener o) {
        if (o == null || netWorkListenerList == null) {
            return;
        } else {
            synchronized (netWorkListenerList) {
                Iterator<WeakReference<NetWorkChangeListener>> iterator = netWorkListenerList
                        .iterator();
                while (iterator.hasNext()) {
                    WeakReference<NetWorkChangeListener> ref = iterator.next();
                    if (o.equals(ref.get())) {
                        ref.clear();
                        iterator.remove();
                        return;
                    }
                }
            }
        }
    }
    
    private void notifyAllListeners(final int netType){
        if(netWorkListenerList != null){
            Iterator<WeakReference<NetWorkChangeListener>> iterator = netWorkListenerList.iterator();
            while(iterator.hasNext()){
                final WeakReference<NetWorkChangeListener> ref = iterator.next();
                handler.post(new Runnable() {
                    
                    @Override
                    public void run() {
                         ref.get().onNetWorkStateChangeListener(netType);
                    }
                });
            }
        }
    }

}
