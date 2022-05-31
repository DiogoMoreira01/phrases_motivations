package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.Filter.FILTER_IMAGE_ALL_INCLUSIVE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //esconder barra
        supportActionBar?.hide()
        //valor padrao da barra superior
        handlFilter(R.id.image_all_inclusive)
        //frase para inicio do app
        handleNextPhrase()
        handleUserName()
        //eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase){
          handleNextPhrase()
        } else if( view.id in listOf(R.id.image_all_inclusive, R.id.image_happy, R.id.image_sunny)){
            handlFilter(view.id)
        }
    }

    private fun handleUserName(){
        val name =  SecurityPreferences(this).getString(MotivationConstants.Key.USER_NAME)
        binding.textApresentacaoNome.text = "Olá, $name!"
    }

    private fun handlFilter(id: Int){
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.Filter.FILTER_IMAGE_ALL_INCLUSIVE
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.Filter.FILTER_IMAGE_HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.Filter.FILTER_IMAGE_SUNNY
            }
        }
    }


   private fun handleNextPhrase(){

       binding.textFrases.text = Mock().getPhrases(categoryId)
    }
}