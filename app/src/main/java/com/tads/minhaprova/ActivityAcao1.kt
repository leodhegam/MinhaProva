package com.tads.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.tads.minhaprova.databinding.ActivityAcao1Binding

class ActivityAcao1 : AppCompatActivity() {
    lateinit var binding: ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)
        binding.apply {
            buttonOk.setOnClickListener{
                var intent = Intent()
                intent.putExtra("texto",editText1.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        buttonCancelar.setOnClickListener {
            val intent = Intent()
            intent.putExtra("CANCELAR", "Cancelado")
            setResult(Activity.RESULT_CANCELED, intent)
            finish()

            }
        }

    }
}