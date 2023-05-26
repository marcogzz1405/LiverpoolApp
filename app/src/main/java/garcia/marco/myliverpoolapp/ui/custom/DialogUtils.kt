package garcia.marco.myliverpoolapp.ui.custom

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import garcia.marco.myliverpoolapp.databinding.DialogFilterBinding

object DialogUtils {

    fun showFilterDialog(
        activity: Activity,
        onConfirm: OnConfirmClicked
    ) {
        val dialog = Dialog(activity, android.R.style.Theme_Material_Light_Dialog_Alert)
        val layoutInflater = LayoutInflater.from(activity)
        val binding = DialogFilterBinding.inflate(layoutInflater, null, false).apply {
            var isSelected = 2
            ivClose.setOnClickListener { dialog.dismiss() }

            bPredefinida.setOnClickListener {
                isSelected = 2
                bPredefinida.isSelected = true
                bMenor.isSelected = false
                bMayor.isSelected = false
            }

            bMenor.setOnClickListener {
                isSelected = 0
                bPredefinida.isSelected = false
                bMenor.isSelected = true
                bMayor.isSelected = false
            }

            bMayor.setOnClickListener {
                isSelected = 1
                bPredefinida.isSelected = false
                bMenor.isSelected = false
                bMayor.isSelected = true
            }

            bFilter.setOnClickListener {
                onConfirm.onConfirm(isSelected, dialog)
            }

        }
        dialog.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(binding.root)
            window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }.show()
    }

    fun interface OnConfirmClicked {
        fun onConfirm(isSelected: Int, dialog: Dialog)
    }

    fun interface OnCancelClicked {
        fun onCancel(view: View, dialog: Dialog)
    }

    fun interface OnClose {
        fun onConfirm()
    }
}