package com.oded.food.delivery.admin.common.impl

interface IProgressBar {

    fun setProgressTitle(aTitle: String)

    fun setProgressTitle(aStringResourceId: Int?)

    fun setProgressSubTitle(aSubTitle: String)

    fun setProgressSubTitle(aStringResourceId: Int?)

    fun setBackgroundProgressTint(aColor: Int)
}