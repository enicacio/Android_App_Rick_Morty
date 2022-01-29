package com.eliane.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.databinding.DetailPersonagemBinding
import com.eliane.myapplication.databinding.PersonagemBinding
import com.eliane.myapplication.view.DetailViewHolder
import com.eliane.myapplication.model.Character
import java.util.*
import kotlin.jvm.internal.Ref

class DetailCharacterAdapter (

    val layoutInflater: LayoutInflater

        ): RecyclerView.Adapter<DetailViewHolder>() {

    val loadCharacter: Character? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val binding = PersonagemBinding.inflate(layoutInflater, parent, false)
        return DetailViewHolder(binding).also {
        }
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        if (loadCharacter != null) {
            holder.setDetailCharacter(loadCharacter)
        }
    }

    override fun getItemCount(): Int {
        return 0
    }


//    fun addDetail(info: Character) {
//        loadCharacter
//    }

}
