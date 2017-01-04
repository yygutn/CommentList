package com.jumy.library.commentList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jumy.library.R;
import com.jumy.library.spannable.CircleMovementMethod;
import com.jumy.library.spannable.SpannableClickable;
import com.jumy.library.spannable.UrlUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jumy on 17/1/4 11:19.
 * Copyright (c) 2017, yygutn@gmail.com All Rights Reserved.
 */
public class CommentListView<T extends BaseComment> extends LinearLayout {
    private int itemColor;
    private int itemSelectorColor;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private List<T> mData;
    private LayoutInflater layoutInflater;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mData = data;
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mData;
    }

    public CommentListView(Context context) {
        super(context);
        initViewAttrs(context,null);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViewAttrs(context,attrs);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViewAttrs(context,attrs);
    }

    protected void initViewAttrs(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        if (attrs == null) {
            return;
        }
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CommentListView, 0, 0);
        try {
            //textview的默认颜色
            int len = typedArray.getIndexCount();
            for (int i = 0; i < len; i++) {
                int position = typedArray.getIndex(i);
                if (position == R.styleable.CommentListView_item_color) {
                    itemColor = typedArray.getColorStateList(position)
                            .getColorForState(getDrawableState(), Color.parseColor("#333333"));
                } else if (position == R.styleable.CommentListView_item_selector_color) {
                    itemSelectorColor = typedArray.getColorStateList(position)
                            .getColorForState(getDrawableState(), Color.parseColor("#cccccc"));
                }
            }
//            itemColor = typedArray.getColor(R.styleable.CommentListView_item_color, Color.parseColor("#333333"));
//            itemSelectorColor = typedArray.getColor(R.styleable.CommentListView_item_selector_color, Color.parseColor("#cccccc"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            typedArray.recycle();
        }
    }

    public void notifyDataSetChanged() {

        removeAllViews();
        if (mData == null || mData.size() == 0) {
            return;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mData.size(); i++) {
            View view = getView(i);
            if (view == null) {
                throw new NullPointerException("listView item layout is null, please check getView()...");
            }

            addView(view, i, layoutParams);
        }

    }

    private View getView(final int position) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(getContext());
        }
        View convertView = layoutInflater.inflate(R.layout.item_comment, null, false);

        TextView commentTv = (TextView) convertView.findViewById(R.id.commentTv);
        final CircleMovementMethod circleMovementMethod = new CircleMovementMethod(itemSelectorColor, itemSelectorColor);

        final T bean = mData.get(position);
        String name = bean.getCreatorName();
//        String id = bean.getId();
        String toReplyName = bean.get2ReplyUserRealName();

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(name, bean.getCreatorId()));

        if (!TextUtils.isEmpty(toReplyName) && !toReplyName.contains(name) && !name.contains(toReplyName)) {
            builder.append(" 回复 ");
            builder.append(setClickableSpan(toReplyName, bean.getOriginCommentId()));
        }

        builder.append(": ");
        //转换表情字符
        String contentBodyStr = bean.getComment();
        builder.append(UrlUtils.formatUrlString(contentBodyStr));
        commentTv.setText(builder);

        commentTv.setMovementMethod(circleMovementMethod);
        commentTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            }
        });
        commentTv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(position);
                    }
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }

    @NonNull
    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(itemColor) {
            @Override
            public void onClick(View widget) {
                Toast.makeText(getContext(), textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
            }
        }, 0, subjectSpanText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }


}
