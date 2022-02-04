package com.eliane.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.view.CharactersViewHolder
import com.eliane.myapplication.databinding.ItemPersonagemBinding
import com.eliane.myapplication.model.Character

class CharactersAdapter(

    val layoutInflater: LayoutInflater,

): RecyclerView.Adapter<CharactersViewHolder>() {

    val listagem: ArrayList<Character> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemPersonagemBinding.inflate(layoutInflater, parent, false)
        return CharactersViewHolder(binding).also {
        }
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.setCharacter(listagem[position])
    }

    override fun getItemCount(): Int {
        return listagem.size
    }

    fun addCharacter(items: List<Character>) {
        listagem.addAll(items)
        notifyDataSetChanged()
    }
}

