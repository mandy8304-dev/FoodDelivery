package com.oded.food.delivery.admin.common

import android.content.Context
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity

class Dimension {

    companion object {

        @JvmStatic
        val DIMENSION_WIDTH = "dimension.width"

        @JvmStatic
        val DIMENSION_HEIGHT = "dimension.height"

        @JvmStatic
        fun dpToPx(dp: Int, aContext: Context): Int {
            val displayMetrics = aContext.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        @JvmStatic
        fun pxToDp(px: Int, aContext: Context): Int {
            val displayMetrics = aContext.resources.displayMetrics
            return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        @JvmStatic
        fun DimensionDevice(aContext: Context, App: AppCompatActivity) {
            val metrics = DisplayMetrics()
            App.windowManager.defaultDisplay.getMetrics(metrics)
            Preferences.saveIntPreference(aContext, DIMENSION_WIDTH, metrics.heightPixels)
            Preferences.saveIntPreference(aContext, DIMENSION_HEIGHT, metrics.widthPixels)
        }

        @JvmStatic
        fun isTablet(context: Context): Boolean {
            val manager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            return manager.phoneType == TelephonyManager.PHONE_TYPE_NONE
        }
    }
}