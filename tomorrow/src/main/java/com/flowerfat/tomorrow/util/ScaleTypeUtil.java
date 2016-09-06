package com.flowerfat.tomorrow.util;

import android.widget.ImageView.ScaleType;

/**
 * Created by 明明大美女 on 2016/9/5.
 *
 * 一个工具类，可以直接用
 * 一个ScaleType的理解
 */
public class ScaleTypeUtil {

    /* the image maybe ugly */
    public static ScaleType MATCH_MATCH() {
        return ScaleType.FIT_XY;
    }

    ////////////////////////////////////////////////////////////////////
    // these three types are a group . 下面三个是一系列的
    // they match one side .  他们会充满一边（充满高 或 充满宽）
    //
    // if(imageview's width:height > image's width:height)
    //      fit the height, the width is wrap
    // 如果 ImageView的宽高比 大于 图片的宽高比， 则充满高度，宽度随缘
    //
    // in a word, scale the image, until the image's width or height fit the ImageView's width or height .
    // 说白了，把图片缩放，直到宽或高充满了ImageView的宽或高
    ////////////////////////////////////////////////////////////////////
    public static ScaleType MATCH_ONE_SIDE__START() {
        return ScaleType.FIT_START;
    }

    public static ScaleType MATCH_ONE_SIDE__END() {
        return ScaleType.FIT_END;
    }

    public static ScaleType MATCH_ONE_SIDE__CENTER() {
        return ScaleType.FIT_CENTER;
    }

    /**
     *  just put the image into the imageview's center. 仅仅是把图片放到ImageView的中间
     *  do not scale the image   不缩放图片
     *
     *  so maybe the image has empty(Image's size > ImageView's size) ,
     *  所以，可能图片周围有留白（图片小于ImageView），
     *
     *  or the image has not shown completely(Image's size < ImageView's size) .
     *  或者 图片显示不全（图片大于ImageView）
    */
    public static ScaleType JUST_PUT_THE_IMAGE__CENTER() {
        return ScaleType.CENTER;
    }

    /**
     * the image will full of the ImageView 图片充满ImageView
     * so always the image will not show fully 所以大部分时候，图片会显示不全
     * @return
     */
    public static ScaleType MATCH_TWO_SIDE__CENTER() {
        return ScaleType.CENTER_CROP;
    }

    /**
     * 这种情况最多了。。。
     *
     *
     *
     *
     * @return
     */
    public static ScaleType CHANGEABLE__INSIDE() {
        return ScaleType.CENTER_INSIDE;
    }


}
