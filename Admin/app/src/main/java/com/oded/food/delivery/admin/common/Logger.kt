package com.oded.food.delivery.admin.common

import android.annotation.SuppressLint
import android.util.Log
import com.oded.food.delivery.admin.BuildConfig

class Logger {

    companion object {

        private const val TAG = BuildConfig.APPLICATION_ID

        /** Log Level Error  */
        @SuppressLint("LongLogTag")
        @JvmStatic
        fun e(message: String) {
            if (BuildConfig.DEBUG) Log.e(TAG, msg(message))
        }

        /** Log Level Warning  */
        @SuppressLint("LongLogTag")
        @JvmStatic
        fun w(message: String) {
            if (BuildConfig.DEBUG) Log.w(TAG, msg(message))
        }

        /** Log Level Information  */
        @SuppressLint("LongLogTag")
        @JvmStatic
        fun i(message: String) {
            if (BuildConfig.DEBUG) Log.i(TAG, msg(message))
        }

        /** Log Level Debug  */
        @SuppressLint("LongLogTag")
        @JvmStatic
        fun d(message: String) {
            if (BuildConfig.DEBUG) Log.d(TAG, msg(message))
        }

        /** Log Level Verbose  */
        @SuppressLint("LongLogTag")
        @JvmStatic
        fun v(message: String) {
            if (BuildConfig.DEBUG) Log.v(TAG, msg(message))
        }


        private fun msg(message: String): String {
            val ste =
                Thread.currentThread().stackTrace[4]
            val sb = StringBuilder()
            sb.append("[")
            sb.append(ste.fileName.replace(".java", ""))
            sb.append("::")
            sb.append(ste.methodName)
            sb.append("]")
            sb.append(message)
            return sb.toString()
        }

    }
}