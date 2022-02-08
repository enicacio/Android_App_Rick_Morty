package com.eliane.myapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.eliane.myapplication.R
import com.eliane.myapplication.databinding.PersonagemBinding
import com.eliane.myapplication.model.Character
import com.squareup.picasso.Picasso
import org.koin.java.KoinJavaComponent.inject


class CharacterDetails : AppCompatActivity() {

    lateinit var binding: PersonagemBinding
    private val imageLoader: Picasso by inject(Picasso::class.java)

    companion object {
        const val EXTRA_MESSAGE = "character-id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pegando a informação da intent criada
        val character = intent.extras?.getParcelable<Character>(EXTRA_MESSAGE)
        if (character == null) {
            Log.d("CharacterDetail", "FALHA ao carregar o personagem")
            finish()
        }

        binding = PersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            if (character != null) {
                imageLoader.load(character.image).into(binding.charImage)
                binding.charSpecies.text = character.species
                binding.charName.text = character.name
                binding.charStatus.text = character.status
                binding.charGender.text = character.gender
                binding.charOriginName.text = character.origin.name
                binding.charLocationName.text = character.location.name
            }
        }
    }
}


