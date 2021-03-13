package com.tads.minhaprova.Livro

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.tads.minhaprova.R
import com.tads.minhaprova.databinding.ActivityAcao2Binding

class ActivityAcao2 : AppCompatActivity() {
    lateinit var binding: ActivityAcao2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)

            binding.buttonConfirm.setOnClickListener{
                var nome = binding.editTextNome.text
                var autor = binding.editTextAutor.text
                var ano = binding.editTextAno.text
                var nota = binding.ratingBar.rating
            // seta um novo livro com as informações passadas acima
                var livro = Livro(0, nome.toString(), autor.toString(), ano.toString().toInt(), nota)
                var datab = LivroDBOpener(this)

                datab.insert(livro)

                setResult(Activity.RESULT_OK,Intent().putExtra("result","cadastrado"))
                finish()
            }
        binding.buttonCancelar.setOnClickListener {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
//
//
//        //ratingBar.setOnRatingBarChangeListener{ratingBar, fl, b ->
        //  Toast.makeText(this, "Teste", Toast.LENGTH_SHORT).show()
        //}
    }
}