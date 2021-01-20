package com.e.spaceflight.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.e.spaceflight.R
import com.e.spaceflight.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_fragment_item_article.view.*


class DialogFragmentItemArticle : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val bundle = getbundle()
            val picasso = Picasso.get()
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            val dialogLayout = inflater.inflate(R.layout.dialog_fragment_item_article, null)


            dialogLayout.title_dialog_item.text = bundle.title
            dialogLayout.summary_dialog_item.text = bundle.summary
            picasso.load(bundle.imageUrl).into(dialogLayout.image_dialog)

            dialogLayout.btn_read_dialog.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(bundle.url))
                startActivity(browserIntent)
            }

            dialogLayout.btn_return_dialog.setOnClickListener {
                dismiss()
            }
            builder.setView(dialogLayout)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")

    }

    fun getbundle(): Article {
        val bund = arguments?.get("key") as Article
        return bund
    }

}