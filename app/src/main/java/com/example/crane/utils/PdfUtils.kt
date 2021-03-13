package com.example.crane.utils

import android.content.Context
import android.os.Build
import android.os.Environment
import android.print.PrintAttributes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.ui_components.custom_view.CustomToast
import com.uttampanchasara.pdfgenerator.CreatePdf
import timber.log.Timber

fun saveFileInPdf(
    context: Context,
    root_cl: CoordinatorLayout,
    name: String,
    content: String,
    action: (() -> Unit)
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        CreatePdf(context)
            .setPdfName(name)
            .openPrintDialog(false)
            .setContentBaseUrl(null)
            .setPageSize(PrintAttributes.MediaSize.ISO_A4)
            .setContent(content)
            .setFilePath(context.cacheDir.absolutePath)
            .setCallbackListener(object : CreatePdf.PdfCallbackListener {
                override fun onFailure(errorMsg: String) {
                    Timber.i("Fail $errorMsg")
                    action()
                    CustomToast(root_cl).showMessage("Fail $errorMsg")
                }

                override fun onSuccess(filePath: String) {
                    action()
                    CustomToast(root_cl).showMessage("Saved $filePath")

                }
            })
            .create()

    } else {
        CreatePdf(context)
            .setPdfName(name)
            .openPrintDialog(false)
            .setContentBaseUrl(null)
            .setPageSize(PrintAttributes.MediaSize.ISO_A4)
            .setContent(content)
            .setFilePath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath)
            .setCallbackListener(object : CreatePdf.PdfCallbackListener {
                override fun onFailure(errorMsg: String) {
                    Timber.i("Fail $errorMsg")
                    action()
                    CustomToast(root_cl).showMessage("Fail $errorMsg")
                }

                override fun onSuccess(filePath: String) {
                    action()
                    CustomToast(root_cl).showMessage("Saved $filePath")

                }
            })
            .create()
    }

}