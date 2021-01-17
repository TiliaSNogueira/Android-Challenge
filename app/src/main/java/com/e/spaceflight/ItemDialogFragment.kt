package com.e.spaceflight

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.e.spaceflight.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_item.view.*
import kotlin.math.log

class ItemDialogFragment : DialogFragment() {

    //private lateinit var listener: DialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            val bund = getbundle()
            val picasso = Picasso.get()
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val dialogLayout = inflater.inflate(R.layout.dialog_fragment_item, null)


            dialogLayout.title_dialog_item.text = bund.title
            dialogLayout.summary_dialog_item.text = bund.summary
            picasso.load(bund.imageUrl).into(dialogLayout.image_dialog)

            dialogLayout.btn_read_dialog.setOnClickListener {
               //listener.onDialogReadClick(DialogFragment())
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(bund.url))
                startActivity(browserIntent)
            }

            dialogLayout.btn_return_dialog.setOnClickListener {
               // listener.onDialogReturnClick(DialogFragment())
                dismiss()
            }



            builder.setView(dialogLayout)
            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")


    }


//    interface DialogListener {
//        fun onDialogReadClick(dialog: DialogFragment)
//        fun onDialogReturnClick(dialog: DialogFragment)
//    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            listener = context as DialogListener
//        } catch (e: ClassCastException) {
//            // The activity doesn't implement the interface, throw exception
//            throw ClassCastException(
//                (context.toString() +
//                        " must implement NoticeDialogListener")
//            )
//        }
//    }

    fun getbundle(): Article {
        val bund = arguments?.get("key") as Article
        return bund
    }


//funionou mas nao consegui pegar o vlaor da resposta
//    fun arguments(bundleOf: Bundle) {
//
//        val resposta = bundleOf.get("key")
//        Log.i("ITEM DIALOG", resposta.toString())
//
//
//    }


}