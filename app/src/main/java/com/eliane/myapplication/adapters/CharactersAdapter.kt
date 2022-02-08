package com.eliane.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.view.CharactersViewHolder
import com.eliane.myapplication.databinding.ItemPersonagemBinding
import com.eliane.myapplication.model.Character
import com.eliane.myapplication.view.onClickCharacterListenner

class CharactersAdapter(

    val layoutInflater: LayoutInflater,
    val onItemClick: onClickCharacterListenner

): RecyclerView.Adapter<CharactersViewHolder>() {

    val listagem: ArrayList<Character> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemPersonagemBinding.inflate(layoutInflater, parent, false)
        return CharactersViewHolder(binding).also {
        }
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.setCharacter(listagem[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return listagem.size
    }

    fun addCharacter(items: List<Character>) {
        listagem.addAll(items)
        notifyDataSetChanged()
    }
}

