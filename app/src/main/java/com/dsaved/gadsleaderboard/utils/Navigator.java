package com.dsaved.gadsleaderboard.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.app.ActivityOptionsCompat;
import android.view.View;

import com.dsaved.gadsleaderboard.R;

import java.io.Serializable;

public class Navigator {
    public static final String ANIM_SLIDE = "slide";
    public static final String ANIM_TOP = "top";
    public static final String ANIM_BOTTOM = "bottom";
    public static final String ANIM_FADE = "fade";
    private Activity activity;
    private Intent intent;
    private ActivityOptionsCompat compat = null;
    private String type = "slide";

    public Navigator(Activity activity) {
        this.activity = activity;
    }

    public Navigator(Context context) {
        this.activity = (Activity) context;
    }

    public Navigator put(String name, String value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, int value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, boolean value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, double value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, float value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, byte value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, short value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, char value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, CharSequence value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, Serializable value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, Parcelable value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, String[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, int[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, boolean[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, double[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, float[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, byte[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, short[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, char[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, CharSequence[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, Serializable[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, Parcelable[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, Bundle value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, long value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator put(String name, long[] value) {
        intent.putExtra(name, value);
        return this;
    }

    public Navigator anim(String animType) {
        this.type = animType;
        return this;
    }

    public Navigator to(Class newClass) {
        intent = new Intent(activity, newClass);
        return this;
    }

    public Navigator addFlags(int flag) {
        intent.addFlags(flag);
        return this;
    }

    public Navigator transitView(View view, String sharedElement) {
        compat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, sharedElement);
        return this;
    }

    public void openForResult(int code) {
        activity.startActivityForResult(intent, code);
        transition(type, "open");
    }

    public void setResult(int resultCode, Intent data) {
        activity.setResult(resultCode, data);
    }

    public void setResult(int resultCode) {
        activity.setResult(resultCode);
    }

    public void open() {
        open(false);
    }

    public void open(boolean finish) {
        if (null != compat) {
            activity.startActivity(intent, compat.toBundle());
        } else {
            activity.startActivity(intent);
        }
        transition(type, "open");
        if (finish) activity.finish();
    }

    public void close() {
        activity.finish();
        transition(Navigator.ANIM_SLIDE, "close");
    }

    public void close(String animType) {
        activity.finish();
        transition(animType, "close");
    }

    private void transition(String anim, String type) {
        switch (anim) {
            case ANIM_SLIDE:
                switch (type) {
                    case "open":
                        activity.overridePendingTransition(R.animator.right_in, R.animator.left_out);
                        break;
                    case "close":
                        activity.overridePendingTransition(R.animator.left_in, R.animator.right_out);
                        break;
                }
                break;
            case ANIM_FADE:
                switch (type) {
                    case "open":
                        activity.overridePendingTransition(R.animator.fade_in, R.animator.fade_out);
                        break;
                    case "close":
                        activity.overridePendingTransition(R.animator.fade_out, R.animator.fade_in);
                        break;
                }
                break;
            case ANIM_TOP:
                switch (type) {
                    case "open":
                        activity.overridePendingTransition(R.animator.top_down, R.animator.bottom_down);
                        break;
                    case "close":
                        activity.overridePendingTransition(R.animator.bottom_up, R.animator.top_up);
                        break;
                }
                break;
            case ANIM_BOTTOM:
                switch (type) {
                    case "open":
                        activity.overridePendingTransition(R.animator.bottom_up, R.animator.top_up);
                        break;
                    case "close":
                        activity.overridePendingTransition(R.animator.top_down, R.animator.bottom_down);
                        break;
                }
                break;
        }

    }
}
