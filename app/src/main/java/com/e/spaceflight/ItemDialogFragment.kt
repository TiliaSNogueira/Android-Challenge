package com.e.spaceflight

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_fragment_item.*
import kotlinx.android.synthetic.main.dialog_fragment_item.view.*

class ItemDialogFragment : DialogFragment() {

    private lateinit var listener: DialogListener



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {






            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val dialogLayout = inflater.inflate(R.layout.dialog_fragment_item, null)




            builder.setView(dialogLayout)





            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    interface DialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as DialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(
                (context.toString() +
                        " must implement NoticeDialogListener")
            )
        }
    }




}