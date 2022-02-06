package com.oded.food.delivery.admin.view

import android.graphics.Rect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.Dimension
import com.oded.food.delivery.admin.common.ProgressBarDialog
import com.oded.food.delivery.admin.logic.impl.IApplication

open class BaseActivity : AppCompatActivity() {

    protected var currentUser: FirebaseUser? = null
    protected var auth: FirebaseAuth? = null
    private var progressDialog: ProgressBarDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = (application as IApplication).auth()
        currentUser = (application as IApplication).auth().currentUser
    }


    protected fun showProgressBar(title: String) {

        if (progressDialog == null) {
            progressDialog = ProgressBarDialog(
                this@BaseActivity,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
            )
        }
        progressDialog!!.setProgressTitle(title)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
        defaultDialogSize()
    }

    protected fun showProgressBar(titleResourceId: Int) {

        if (progressDialog == null) {
            progressDialog = ProgressBarDialog(
                this@BaseActivity,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
            )
        }
        progressDialog!!.setProgressTitle(titleResourceId)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
        defaultDialogSize()
    }

    protected fun showProgressBar(titleResourceId: Int, subTitleResourceId: Int) {

        if (progressDialog == null) {
            progressDialog = ProgressBarDialog(
                this@BaseActivity,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
            )
        }
        progressDialog!!.setProgressTitle(titleResourceId)
        progressDialog!!.setProgressSubTitle(subTitleResourceId)
        progressDialog!!.show()
        defaultDialogSize()
    }

    protected fun updateProgressBar(aSubtitle: String) {

        if (progressDialog == null) {
            progressDialog = ProgressBarDialog(
                this@BaseActivity,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog
            )
        }

        progressDialog!!.setProgressSubTitle(aSubtitle)

    }

    protected fun dismissProgressBar() {

        if (progressDialog != null) {
            if (progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        }
        progressDialog = null

    }

    private fun defaultDialogSize() {
        val displayRectangle = Rect()
        val window = window
        val height = Dimension.dpToPx(150, this@BaseActivity)
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        progressDialog!!.window!!.setLayout((displayRectangle.width() * 0.95f).toInt(), height)
    }
}