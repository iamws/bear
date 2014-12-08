package org.iamwsbear.weightpk.common.broadcast;

import java.lang.ref.WeakReference;
import java.util.List;

import org.iamwsbear.weightpk.util.NetWorkUtil;

import com.google.common.collect.Lists;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetWorkBroadCast extends BroadcastReceiver {
    
    private static final String TAG = NetWorkBroadCast.class.getName();
    
    private static NetWorkBroadCast netWorkBroadCast;
    
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
        Log.d(TAG,"the connect is "+NetWorkUtil.isNetworkConnected(context));
        String notice="";
        switch (NetWorkUtil.getNetworkType(context)) {
        case NetWorkUtil.NET_INVALID:
            notice ="无网络";
            break;
        case NetWorkUtil.NET_WIFI:
            notice ="WIFI------";
            break;
        case NetWorkUtil.NET_2G:
            notice ="2G-------";
            break;
        case NetWorkUtil.NET_3G:
            notice ="3G----------";
            break;
        default:
            break;
        }
        Log.d(TAG, notice);
    }
    
    /**
     * 定义监听接口用于通知用户网络变化的情况
     * @author iamwsbear@gmail.com
     *
     */
    public interface NetWorkChangeListener{
        public void onNetWorkStateChangeListener();
    }
    
    private static List<WeakReference<NetWorkChangeListener>> netWorkListeners = Lists.newArrayList();

}
