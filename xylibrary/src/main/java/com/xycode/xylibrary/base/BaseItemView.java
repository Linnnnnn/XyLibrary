package com.xycode.xylibrary.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xycode.xylibrary.R;

/**
 * Created by XY on 2016-08-08.
 */
public abstract class BaseItemView extends RelativeLayout {

    private SparseArray<View> viewList;

    protected int itemType;
    protected int itemIcon;
    protected int itemColor;
    protected int itemChildColor;
    protected int itemBg;
    protected int itemRes;
    protected int itemNum;
    protected int itemCount;
    protected int itemVisible;
    protected int itemChildVisible;
    protected int itemIndex;
    protected boolean itemBool;
    protected boolean itemCheck;
    protected float itemFloat;
    protected String itemName;
    protected String itemHint;
    protected String itemTitle;
    protected String itemContent;
    protected String itemDetail;
    protected String itemDescription;

    protected OnViewSenseListener onViewSenseListener;

    protected int layoutId = R.layout.layout_blank;

    public BaseItemView(Context context) {
        super(context, null);
    }

    public BaseItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        viewList = new SparseArray<>();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BaseItemView);

        itemNum = a.getInt(R.styleable.BaseItemView_itemNum, 0);
        itemCount = a.getInt(R.styleable.BaseItemView_itemCount, 0);
        itemIndex = a.getInt(R.styleable.BaseItemView_itemIndex, 0);
        itemBool = a.getBoolean(R.styleable.BaseItemView_itemBool, false);
        itemCheck = a.getBoolean(R.styleable.BaseItemView_itemCheck, false);
        itemVisible = a.getInt(R.styleable.BaseItemView_itemVisible, VISIBLE);
        itemChildVisible = a.getInt(R.styleable.BaseItemView_itemVisible, VISIBLE);
        itemFloat = a.getFloat(R.styleable.BaseItemView_itemFloat, 0);
        itemIcon = a.getResourceId(R.styleable.BaseItemView_itemIcon, 0);
        itemColor = a.getResourceId(R.styleable.BaseItemView_itemColor, 0);
        itemChildColor = a.getResourceId(R.styleable.BaseItemView_itemChildColor, 0);
        itemBg = a.getResourceId(R.styleable.BaseItemView_itemBg, 0);
        itemRes = a.getResourceId(R.styleable.BaseItemView_itemRes, 0);
        itemName = a.getString(R.styleable.BaseItemView_itemName);
        itemHint = a.getString(R.styleable.BaseItemView_itemHint);
        itemTitle = a.getString(R.styleable.BaseItemView_itemTitle);
        itemContent = a.getString(R.styleable.BaseItemView_itemContent);
        itemDetail = a.getString(R.styleable.BaseItemView_itemDetail);
        itemDescription = a.getString(R.styleable.BaseItemView_itemDescription);
        itemName = itemName == null ? "" : itemName;
        itemHint = itemHint == null ? "" : itemHint;
        itemTitle = itemTitle == null ? "" : itemTitle;
        itemContent = itemContent == null ? "" : itemContent;
        itemDetail = itemDetail == null ? "" : itemDetail;
        itemDescription = itemDescription == null ? "" : itemDescription;
        itemType = a.getInt(R.styleable.BaseItemView_itemType, -1);
        if (setExtendEnumStyle() != R.styleable.BaseItemView && setItemTypeEnumStyle() != R.styleable.BaseItemView_itemType) {
            a.recycle();
            a = context.obtainStyledAttributes(attrs, setExtendEnumStyle());
            int type = a.getInt(setItemTypeEnumStyle(), -1);
            if (type != -1) itemType = type;
        }
        if (itemType == -1) itemType = 0;
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(getLayoutId(itemType), this, true);
        setViews(itemType);
    }

    public int getItemNum() {
        return itemNum;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public float getItemFloat() {
        return itemFloat;
    }

    public <T extends View> T getView(int viewId) {
        View view = viewList.get(viewId);
        if (view == null) {
            view = findViewById(viewId);
            viewList.put(viewId, view);
        }
        return (T) view;
    }

    public BaseItemView setText(int viewId, @StringRes int textRes) {
        setText(viewId, getContext().getString(textRes));
        return this;
    }

    public BaseItemView setText(int viewId, String text) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof EditText) {
                ((EditText) view).setText(text);
            } else if (view instanceof Button) {
                ((Button) view).setText(text);
            } else if (view instanceof TextView) {
                ((TextView) view).setText(text);
            }
        }
        return this;
    }

    public BaseItemView setTextColor(int viewId, @ColorRes int textColor) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof EditText) {
                ((EditText) view).setTextColor(getResources().getColor(textColor));
            } else if (view instanceof Button) {
                ((Button) view).setTextColor(getResources().getColor(textColor));
            } else if (view instanceof TextView) {
                ((TextView) view).setTextColor(getResources().getColor(textColor));
            }
        }
        return this;
    }

    public BaseItemView setImageUrl(int viewId, String url) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof SimpleDraweeView) {
                ((SimpleDraweeView) view).setImageURI(Uri.parse(url));
            } else if (view instanceof ImageView) {
                ((ImageView) view).setImageURI(Uri.parse(url));
            }
        }
        return this;
    }

    public BaseItemView setImageURI(int viewId, Uri uri) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof SimpleDraweeView) {
                ((SimpleDraweeView) view).setImageURI(uri);
            } else if (view instanceof ImageView) {
                ((ImageView) view).setImageURI(uri);
            }
        }
        return this;
    }

    public BaseItemView setImageBitmap(int viewId, Bitmap bitmap) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageBitmap(bitmap);
            }
        }
        return this;
    }

    public BaseItemView setImageRes(int viewId, @DrawableRes int drawableRes) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof ImageView) {
                ((ImageView) view).setImageResource(drawableRes);
            }
        }
        return this;
    }

    public BaseItemView setRippleComplete(int viewId, RippleView.OnRippleCompleteListener completeListener) {
        View view = getView(viewId);
        if (view != null) {
            if (view instanceof RippleView) {
                ((RippleView) view).setOnRippleCompleteListener(completeListener);
            }
        }
        return this;
    }

    public BaseItemView setClick(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public BaseItemView setLongClick(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnLongClickListener(listener);
        }
        return this;
    }

    public BaseItemView setEnable(int viewId, boolean enable) {
        View view = getView(viewId);
        if (view != null) {
            view.setEnabled(enable);
        }
        return this;
    }

    public BaseItemView setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
        return this;
    }

    public void setOnViewSenseListener(OnViewSenseListener onViewSenseListener) {
        this.onViewSenseListener = onViewSenseListener;
    }

    protected abstract int getLayoutId(int type);

    protected abstract void setViews(int type);

    protected
    @StyleableRes
    int setItemTypeEnumStyle() {
        return R.styleable.BaseItemView_itemType;
    }

    protected
    @StyleableRes
    int[] setExtendEnumStyle() {
        return R.styleable.BaseItemView;
    }

    public interface OnViewSenseListener {
        void onClick(View view, Object object);
    }

}
