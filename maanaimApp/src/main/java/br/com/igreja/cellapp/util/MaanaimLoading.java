package br.com.igreja.cellapp.util;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import br.com.igreja.cellapp.R;

/**
 * Created by vilmar.filho on 1/5/16.
 */
public class MaanaimLoading extends ImageView {

    public MaanaimLoading(Context context) {
        super(context);
    }

    public MaanaimLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupLoading();
    }

    public MaanaimLoading(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setupLoading();
    }

    private void setupLoading() {
        setBackgroundResource(R.drawable.loading_animated);

        post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable frameAnimation = (AnimationDrawable) getBackground();
                frameAnimation.start();
            }
        });
    }
}
