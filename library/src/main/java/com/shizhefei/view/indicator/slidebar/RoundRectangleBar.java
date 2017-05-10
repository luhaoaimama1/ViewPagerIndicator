package com.shizhefei.view.indicator.slidebar;

import android.content.Context;
import android.view.View;

/**
 * 
 * 通过颜色来设置滑动块
 */
public class RoundRectangleBar implements ScrollBar {
	protected Gravity gravity;
	protected View view;
	protected int color;
	protected int height;
	protected int width;

	public RoundRectangleBar(Context context, int color, int height, float rx) {
		this(context, color, height, Gravity.BOTTOM,rx);
	}

	public RoundRectangleBar(Context context, int color, int height, Gravity gravity, float rx) {
		view = new RoundRectangleView(context,rx);
		this.color = color;
//		view.setBackgroundColor(color);
		this.height = height;
		this.gravity = gravity;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
//		view.setBackgroundColor(color);
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getHeight(int tabHeight) {
		if (height == 0) {
			return tabHeight;
		}
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getWidth(int tabWidth) {
		if (width == 0) {
			return tabWidth;
		}
		return width;
	}

	@Override
	public View getSlideView() {
		return view;
	}

	@Override
	public Gravity getGravity() {
		return gravity;
	}

	public void setGravity(Gravity gravity) {
		this.gravity = gravity;
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}
	@Override
	public float slideViewRadio() {
		return 0.8F;
	}
}
