package com.oded.food.delivery.admin.common


import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.oded.food.delivery.admin.R
import com.oded.food.delivery.admin.common.impl.ICallback

class Dialog {

    companion object {

        const val ACTION_YES = "yes"
        const val ACTION_CANCEL = "cancel"

        @JvmStatic
        fun showForm(context: Context, title: String, hint: String, inputType: Int,
                     callback: ICallback?, text: String = "", required: Boolean = false) {

            val builder = MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
            builder.setTitle(title)
            val viewInflated: View = LayoutInflater.from(context)
                .inflate(R.layout.layout_text_input, null, false)
            viewInflated.findViewById<TextInputLayout>(R.id.textInputLayout).hint = hint
            viewInflated.findViewById<TextInputEditText>(R.id.input).inputType = inputType

            if (!text.isNullOrEmpty()) {
                viewInflated.findViewById<TextInputEditText>(R.id.input).setText(text)
            }

            builder.setView(viewInflated)
            builder.setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                var m = viewInflated.findViewById<TextInputEditText>(R.id.input).text.toString()
                callback?.callback(m)
            }
            builder.setNegativeButton(context.getText(R.string.cancel)) { dialog, _ -> dialog.cancel() }
            val dialog =  builder.show()
            if (required) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
                viewInflated.findViewById<EditText>(R.id.input).addTextChangedListener(object :
                    TextWatcher {
                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.isNullOrEmpty()
                    }

                    override fun afterTextChanged(s: Editable?) {

                    }

                })
            }

        }

        @JvmStatic
        fun showDialogAskChecked(
            context: Context, callback: ICallback?, title:String, ask: String, positiveButton: String,
            negativeButton: String
        ) {

            var isChecked = false
            val viewInflated: View = LayoutInflater.from(context)
                .inflate(R.layout.layout_chexbox, null, false)
            val checkBox = viewInflated.findViewById(R.id.checkbox) as CheckBox
            checkBox.setOnCheckedChangeListener { _, ch ->
                isChecked = ch
            }

            val alert = MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle(title)
                .setMessage(ask)
                .setCancelable(false)
                .setView(viewInflated)

            if (negativeButton.isNotEmpty()) {
                alert.setNegativeButton(negativeButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(hashMapOf(
                        "action" to "view"
                    ))
                }
            }

            if (positiveButton.isNotEmpty()) {
                alert.setPositiveButton(positiveButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(
                        hashMapOf(
                            "action" to "yes",
                            "checked" to isChecked
                        ))
                }
            }

            alert.show()
        }

        @JvmStatic
        fun showDialogAsk(
            context: Context, callback: ICallback?, ask: String, positiveButton: String,
            negativeButton: String
        ) {

            val alert = MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle(R.string.app_name)
                .setMessage(ask)

            if (negativeButton.isNotEmpty()) {
                alert.setNegativeButton(negativeButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_CANCEL)
                }
            }

            if (positiveButton.isNotEmpty()) {
                alert.setPositiveButton(positiveButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_YES)
                }
            }

            alert.show()
        }

        @JvmStatic
        fun showDialogAsk(
            context: Context, callback: ICallback?, title: String, ask: String, positiveButton: String,
            negativeButton: String
        ) {

            val alert = MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle(title)
                .setMessage(ask)

            if (negativeButton.isNotEmpty()) {
                alert.setNegativeButton(negativeButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_CANCEL)
                }
            }

            if (positiveButton.isNotEmpty()) {
                alert.setPositiveButton(positiveButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_YES)
                }
            }

            alert.show()
        }


        @JvmStatic
        fun showDialogAsk(context: Context, callback: ICallback?, ask: String) {

            MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog).setTitle(R.string.app_name)
                .setMessage(ask)
                .setNegativeButton(context.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_CANCEL)
                }
                .setPositiveButton(context.getString(R.string.ok)) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_YES)
                }
                .show()
        }

        @JvmStatic
        fun showDialogInfo(
            context: Context, callback: ICallback?, title: String, ask: String, positiveButton: String
        ) {

            val alert = MaterialAlertDialogBuilder(context,
                R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle(title)
                .setMessage(ask)
                .setCancelable(false)

                alert.setPositiveButton(positiveButton) { dialog, _ ->
                    dialog.dismiss()
                    callback?.callback(ACTION_YES)
                }

            alert.show()
        }
    }


}