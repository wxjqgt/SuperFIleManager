package com.weibo.superfilemanager.mvp.view.recyclerView;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/7/15.
 */
public class OnRecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener{

    private RecyclerView recyclerView;
    private GestureDetectorCompat gestureDetectorCompat;

    public OnRecyclerViewItemClickListener(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        this.gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),
                new GestureDetectorListener(this));
    }

    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        gestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public void onItemClickLitener(RecyclerView.ViewHolder viewHolder){}
    public void onItemLongClickLitener(RecyclerView.ViewHolder viewHolder){}

}
