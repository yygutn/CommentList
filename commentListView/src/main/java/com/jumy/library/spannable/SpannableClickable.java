package com.jumy.library.spannable;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;


public abstract class SpannableClickable extends ClickableSpan implements View.OnClickListener {

    /**
     * text颜色
     */
    private int textColor;

    protected SpannableClickable() {
        int DEFAULT_COLOR = Color.parseColor("#8290AF");
        this.textColor = DEFAULT_COLOR;
    }

    protected SpannableClickable(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        ds.setColor(textColor);
        ds.setUnderlineText(false);
        ds.clearShadowLayer();
    }
}
