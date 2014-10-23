package org.iamwsbear.weightpk.adapter;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.ryanharter.viewpager.PagerAdapter;

public class VerticalPagerAdapter extends PagerAdapter {

	private List<View> list;

	public VerticalPagerAdapter(List<View> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list.get(position));
		return list.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list.get(position));
	}
}
