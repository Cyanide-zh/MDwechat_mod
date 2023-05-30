package com.blanke.mdwechat.util;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.Arrays;

/**
 * Created by blanke on 2017/9/2.
 */

public class DrawableUtils {

    // 为单个drawable着色并返回着色后的drawable
    public static Drawable setDrawableColor(Drawable drawable, Integer colorResId) {
        Drawable modeDrawable = drawable.mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        DrawableCompat.setTint(temp, colorResId);
        return temp;
    }

    public static Drawable getNineDrawable(Resources resources, Bitmap bitmap) {
        return NinePatchBitmapFactory.createNinePatchDrawable(resources, bitmap);
//        byte[] chunk = bitmap.getNinePatchChunk();
//        boolean result = NinePatch.isNinePatchChunk(chunk);
//        LogUtil.log("chunk=" + Arrays.toString(chunk) + "result=" + result);
//        LogUtil.log("h=" + bitmap.getHeight() + ",w=" + bitmap.getWidth());
//        if (chunk == null || !result) {
//            return new BitmapDrawable(bitmap);
//        }
//        return new NinePatchDrawable(resources, bitmap, chunk, new Rect(), null);
    }

    public static RippleDrawable getTransparentColorRippleDrawable(int normalColor, int pressedColor) {
        return new RippleDrawable(ColorStateList.valueOf(pressedColor), null, getRippleMask(normalColor));
    }

    public static RippleDrawable getColorRippleDrawable(int normalColor, int pressedColor) {
        return new RippleDrawable(ColorStateList.valueOf(pressedColor), new ColorDrawable(normalColor), getRippleMask(normalColor));
    }

    private static Drawable getRippleMask(int color) {
        float[] outerRadii = new float[8];
        // 3 is radius of final ripple,
        // instead of 3 you can give required final radius
        Arrays.fill(outerRadii, 3);

        RoundRectShape r = new RoundRectShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(r);
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

//    public static void drawableToFile(Drawable drawable, String filePath, Bitmap.CompressFormat format) {
//        if (drawable == null)
//            return;
//
//        try {
//            File file = new File(filePath);
//
//            if (file.exists())
//                file.delete();
//
//            if (!file.exists())
//                file.createNewFile();
//
//            FileOutputStream out = null;
//            out = new FileOutputStream(file);
//            ((BitmapDrawable) drawable).getBitmap().compress(format, 100, out);
//            out.close();
//        } catch (IOException e) {
//            LogUtil.logXp(e);
//        }
//    }
}
