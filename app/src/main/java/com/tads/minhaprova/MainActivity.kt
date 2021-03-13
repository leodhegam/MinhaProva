package com.tads.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import com.tads.minhaprova.Livro.ActivityAcao2
import com.tads.minhaprova.Livro.ActivityAcao3
import com.tads.minhaprova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //pega os valores default do view model , e se modificado , troca
        binding.text1.text = viewModel.text1
        binding.text2.text = viewModel.text2

        var settings = getSharedPreferences("prefs", MODE_PRIVATE)
        var flag = settings.getBoolean("flag", true)
        if(flag){
            Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show()

            var edit = settings.edit()
            edit.putBoolean("flag", false)
            edit.apply()
        }


        binding.button1.setOnClickListener{
            var intent = Intent(this,ActivityAcao1::class.java)
            startActivityForResult(intent,1)
        }
        binding.button2.setOnClickListener{
            var dialog = DialogCoffee()
            dialog.show(supportFragmentManager,"Dialog")
        }
        binding.button3.setOnClickListener{
            var intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent,3)
        }
        binding.button4.setOnClickListener {
            var intent = Intent(this, ActivityAcao3::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // 1 e 3 , são os request code passados nos buttons.
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            1->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        val result = data?.extras?.getString("texto")
                        viewModel.text1 = result.toString()
                        binding.text1.text= result
                    }Activity.RESULT_CANCELED->{
                        Snackbar.make(binding.mainlayout,"Cancelado",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
            3->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        val result2 = data?.extras?.getString("result")
                        viewModel.text2 = result2.toString()
                        binding.text2.text = result2
                    }
                }
            }
        }
    }
}