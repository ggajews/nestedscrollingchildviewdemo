package com.getbase.nestedscrollingchilddemo;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class NestedScrollingChildView extends View implements NestedScrollingChild, OnGestureListener {
  private final NestedScrollingChildHelper mNestedScrollingChildHelper;
  private GestureDetectorCompat mDetector;

  public NestedScrollingChildView(Context context) {
    this(context, null);
  }

  public NestedScrollingChildView(Context context, AttributeSet attrs) {
    this(context, null, 0);
  }

  public NestedScrollingChildView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
    mDetector = new GestureDetectorCompat(context, this);
    setNestedScrollingEnabled(true);
  }

  @Override
  public void setNestedScrollingEnabled(boolean enabled) {
    mNestedScrollingChildHelper.setNestedScrollingEnabled(true);
  }

  @Override
  public boolean isNestedScrollingEnabled() {
    return mNestedScrollingChildHelper.isNestedScrollingEnabled();
  }

  @Override
  public boolean startNestedScroll(int axes) {
    return mNestedScrollingChildHelper.startNestedScroll(axes);
  }

  @Override
  public void stopNestedScroll() {
    mNestedScrollingChildHelper.stopNestedScroll();
  }

  @Override
  public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
  }

  @Override
  public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
  }

  @Override
  public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY);
  }

  @Override
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    mNestedScrollingChildHelper.onDetachedFromWindow();
  }

  @Override
  public boolean hasNestedScrollingParent() {
    return mNestedScrollingChildHelper.hasNestedScrollingParent();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event){
    final boolean handled = mDetector.onTouchEvent(event);
    if (!handled && event.getAction() == MotionEvent.ACTION_UP) {
      stopNestedScroll();
    }
    return true;
  }

  @Override
  public boolean onDown(MotionEvent e) {
    startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
    return true;
  }

  @Override
  public void onShowPress(MotionEvent e) {
  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    dispatchNestedPreScroll(0, (int) distanceY, null, null);
    dispatchNestedScroll(0, 0, 0, 0, null);
    return true;
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return true;
  }
}
