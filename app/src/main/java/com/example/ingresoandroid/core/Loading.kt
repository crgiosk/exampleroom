package com.example.ingresoandroid.core

import androidx.fragment.app.FragmentManager
import com.example.ingresoandroid.view.dialogs.LoadingDialog

abstract class Loading {

    // Dialogs
    private var loadingDialog: LoadingDialog? = null

    private fun showLoading(message: String, context: FragmentManager) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.newInstance(message)
            loadingDialog!!.show(context, LoadingDialog.TAG)
        }
    }

    /**
     * Hide Loading
     */
    private fun hideLoading() {
        if (loadingDialog != null) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }
}