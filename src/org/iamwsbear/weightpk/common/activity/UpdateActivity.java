package org.iamwsbear.weightpk.common.activity;

import org.iamwsbear.weightpk.R;
import org.iamwsbear.weightpk.common.broadcast.NetWorkBroadCast;
import org.iamwsbear.weightpk.common.broadcast.NetWorkBroadCast.NetWorkChangeListener;
import org.iamwsbear.weightpk.util.NetWorkUtil;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UpdateActivity extends Activity implements NetWorkChangeListener{
    
    private static final String TAG = UpdateActivity.class.getName();
	
	private Dialog noticeDialog;
	
	private ProgressBar noticeProgressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		NetWorkBroadCast.addNetWorkChangeListener(this);
	}

    @Override
    public void onNetWorkStateChangeListener(int netType) {
        switch (netType) {
        case NetWorkUtil.NET_INVALID:
            Toast.makeText(this, "短嗤利大", Toast.LENGTH_SHORT).show();
            break;
        case NetWorkUtil.NET_2G:
            Toast.makeText(this, "2g利大", Toast.LENGTH_SHORT).show();
            break;
        case NetWorkUtil.NET_3G:
            Toast.makeText(this, "3g利大", Toast.LENGTH_SHORT).show();
            break;

        default:
            break;
        }
    }
    
    @Override
    protected void onDestroy() {
        NetWorkBroadCast.removeNetWorkChangeListener(this);
        super.onDestroy();
    }

}
