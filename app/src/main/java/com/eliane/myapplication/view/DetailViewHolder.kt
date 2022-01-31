package com.eliane.myapplication.view

import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.databinding.PersonagemBinding
import com.eliane.myapplication.model.Character
import com.squareup.picasso.Picasso

class DetailViewHolder(val binding: PersonagemBinding) : RecyclerView.ViewHolder(binding.root){

    val imageLoader: Picasso = Picasso.get()

    fun setDetailCharacter(character: Character) {
        imageLoader.load(character.image).into(binding.charImage)
        binding.charSpecies.text = character.species
        binding.charName.text = character.name
        binding.charStatus.text = character.status
        binding.charType.text = character.type
        binding.charGender.text = character.gender
        binding.charOriginName.text = character.origin.name
        binding.charLocationName.text = character.location.name
    }

}
