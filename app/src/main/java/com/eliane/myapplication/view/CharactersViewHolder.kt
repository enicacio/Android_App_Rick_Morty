package com.eliane.myapplication.view

import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.databinding.ItemPersonagemBinding
import com.eliane.myapplication.model.Character
import com.squareup.picasso.Picasso
import org.koin.java.KoinJavaComponent.inject

interface onClickCharacterListenner {
    fun onDetailCharacter(character: Character)
}

class CharactersViewHolder(val binding: ItemPersonagemBinding) : RecyclerView.ViewHolder(binding.root) {

    private val imageLoader: Picasso by inject(Picasso::class.java)

    fun setCharacter(character: Character, onClickListenner: onClickCharacterListenner) {
        imageLoader.load(character.image).into(binding.charImage)
        binding.charSpecies.text = character.species
        binding.charName.text = character.name
        binding.root.setOnClickListener { onClickListenner?.onDetailCharacter(character) }
    }
}