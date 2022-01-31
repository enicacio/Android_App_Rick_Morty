package com.eliane.myapplication.view

import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.databinding.ItemPersonagemBinding
import com.eliane.myapplication.model.Character
import com.squareup.picasso.Picasso
import org.koin.java.KoinJavaComponent.inject

class CharactersViewHolder(val binding: ItemPersonagemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageLoader: Picasso by inject(Picasso::class.java)

    fun setCharacter(character: Character) {
        imageLoader.load(character.image).into(binding.charImage)
        binding.charSpecies.text = character.species
        binding.charName.text = character.name
        binding.charId.text = character.id.toString()
    }
}