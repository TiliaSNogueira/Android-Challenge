package com.e.spaceflight.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.DialogFragment
import com.e.spaceflight.R
import kotlinx.android.synthetic.main.dialog_fragment_error.view.*

class DialogFragmentError : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val dialogLayout = inflater.inflate(R.layout.dialog_fragment_error, null)

            dialogLayout.btn_error_dialog.setOnClickListener {
                //listener.onDialogReadClick(DialogFragment())
                finishAffinity(this.requireActivity())
            }
            builder.setView(dialogLayout)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}