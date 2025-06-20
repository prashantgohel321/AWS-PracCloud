package com.example.learningapptry2.chaptertopics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class ExpandableHeightGridView extends GridView {

    boolean expanded = false;

    public ExpandableHeightGridView(Context context) {
        super(context);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableHeightGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean isExpanded(){
        return expanded;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isExpanded()){
            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK, MeasureSpec.AT_MOST
            );
            super.onMeasure(widthMeasureSpec, expandSpec);
            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void setExpanded(boolean expanded){
        this.expanded = expanded;
    }
}
