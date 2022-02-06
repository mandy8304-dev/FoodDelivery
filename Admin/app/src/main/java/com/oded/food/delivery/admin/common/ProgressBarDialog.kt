package com.oded.food.delivery.admin.common

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.impl.IProgressBar

class ProgressBarDialog(context: Context, themeResId: Int) :
    Dialog(context, themeResId), IProgressBar {

    private var txtTitle: TextView? = null
    private var txtSubtitle: TextView? = null
    private var progressbar: ProgressBar? = null

    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.layout_progress_bar)

        progressbar = findViewById(R.id.progressbar)
        txtTitle = findViewById(R.id.txtTitle)
        txtSubtitle = findViewById(R.id.txtSubtitle)

    }

    override fun setProgressTitle(aTitle: String) {
        txtTitle!!.text = aTitle
    }

    override fun setProgressTitle(aStringResourceId: Int?) {
        txtTitle!!.text = context.getString(aStringResourceId!!)
    }

    override fun setProgressSubTitle(aSubTitle: String) {
        txtSubtitle!!.text = aSubTitle
        txtSubtitle!!.visibility = View.VISIBLE
        if (txtSubtitle!!.text.toString().isEmpty()) {
            txtSubtitle!!.visibility = View.GONE
        }
    }

    override fun setProgressSubTitle(aStringResourceId: Int?) {
        txtSubtitle!!.text = context.getString(aStringResourceId!!)
        txtSubtitle!!.visibility = View.VISIBLE
        if (txtSubtitle!!.text.toString().isEmpty()) {
            txtSubtitle!!.visibility = View.GONE
        }

    }

    override fun setBackgroundProgressTint(aColor: Int) {
        progressbar!!.setBackgroundColor(aColor)
    }


    interface IProgressBarValue {
        fun progressBarValue(s: String)
        fun progressBarValue(from: Int, to: Int)
    }
}
