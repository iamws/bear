package org.iamwsbear.weightpk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceUtil {
	
	private Context context;

	private static SharePreferenceUtil instance;
	
	private SharedPreferences sp = context.getSharedPreferences(preferName, Context.MODE_PRIVATE);

	public final static String preferName = "WEIGHT_PK";

	public static SharePreferenceUtil getInstance() {
		if (instance == null) {
			instance = new SharePreferenceUtil();
		}
		return instance;
	}
	
	public Editor getSpEdit(){
		return sp.edit();
	}
	
	public void commitSpEdit(){
		sp.edit().commit();
	}

}
