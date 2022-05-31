package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //esconder barra
        supportActionBar?.hide()

        //verifica se existe nome
        verifyUserName()
        binding.btnToSave.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_to_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            //popula o sharedPreferences
            SecurityPreferences(this).storeString(MotivationConstants.Key.USER_NAME, name)
            //chama a proxima tela e cria a mesma
           startActivity(Intent(this, MainActivity::class.java))
            //nao deixa voltar para tras destroi a tela
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show()
        }

    }


    private fun verifyUserName(){
        //le o sharedPreferences
        val name =  SecurityPreferences(this).getString(MotivationConstants.Key.USER_NAME)
        if(name != "") {
            //chama a proxima tela e cria a mesma
            startActivity(Intent(this, MainActivity::class.java))
            //nao deixa voltar para tras destroi a tela
            finish()
        }
    }
}