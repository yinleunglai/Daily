package com.aspsine.zhihu.daily.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.zhihu.daily.R;
import com.aspsine.zhihu.daily.util.DensityUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by aspsine on 15-3-28.
 */
public class AvatarsView extends LinearLayout {

    private TextView title;
    private LinearLayout llAvatarContainer;
    private DisplayImageOptions mOptions;

    public AvatarsView(Context context) {
        this(context, null);
    }

    public AvatarsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AvatarsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.recycler_item_avatars, this, false);
        title = (TextView) view.findViewById(R.id.title);
        llAvatarContainer = (LinearLayout) view.findViewById(R.id.llAvatarContainer);
        addView(view);

        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .displayer(new CircleBitmapDisplayer())
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    public void bindData(String name, List<String> images) {
        title.setText(name);
        int hw = DensityUtil.dip2px(getContext(), 40);
        int margin = DensityUtil.dip2px(getContext(), 8);
        LayoutParams lp = new LayoutParams(hw, hw);
        lp.setMargins(margin, margin, margin, margin);
        llAvatarContainer.removeAllViews();
        if (images != null && images.size() > 0) {
            for (String url : images) {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(lp);
                llAvatarContainer.addView(imageView);
                ImageLoader.getInstance().displayImage(url, imageView, mOptions);
            }
        }
    }
}
