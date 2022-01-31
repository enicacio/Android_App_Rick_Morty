package com.eliane.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.eliane.myapplication.R
import com.eliane.myapplication.adapters.CharactersAdapter
import com.eliane.myapplication.adapters.DetailCharacterAdapter
import com.eliane.myapplication.api.RickMorthAPI
import com.eliane.myapplication.databinding.DetailPersonagemBinding
import com.eliane.myapplication.databinding.PersonagemBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.Dispatchers.IO
import kotlin.properties.Delegates

class CharacterDetails : Activity(), View.OnClickListener {

    lateinit var binding: DetailPersonagemBinding
    lateinit var adapter: DetailCharacterAdapter
    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailPersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pegando a informação da intent criada
        var message = intent.getStringExtra("character id")
        if (message != null) {
            id = message.toInt()
        } else {
            Log.d("CharacterDetail", "FALHA ao carregar o message")
        }
        initList()
        carregarPersonagem()

        //OnClick do botão Voltar
        findViewById<Button>(R.id.button_voltar)?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent(this, CharactersList::class.java)
        startActivity(intent)
    }

    private fun initList() {
        adapter = DetailCharacterAdapter(layoutInflater)
        binding.charsDetails.adapter = adapter
    }

    private fun carregarPersonagem() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: RickMorthAPI = retrofit.create(RickMorthAPI::class.java)

        GlobalScope.launch(IO) {
            val call = service.getCharacterById(id).execute()
            val detail = call.body()?.detailCharacter

            withContext(Main) {
                detail?.let {
                    adapter.loadCharacter
                }
            }
        }
    }
}