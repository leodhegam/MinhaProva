package com.tads.minhaprova

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class DialogCoffee : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)

        builder.setMessage("Gostaria de uma xícara de café?")

            .setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, id ->

                Toast.makeText(activity, getString(R.string.good), Toast.LENGTH_SHORT).show()

            })

            .setNegativeButton("Não", DialogInterface.OnClickListener { dialog, id ->

                Toast.makeText(activity, getString(R.string.next), Toast.LENGTH_SHORT).show()

            })

        return builder.create()

    }

}