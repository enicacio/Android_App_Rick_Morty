package com.eliane.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.eliane.myapplication.R
import com.eliane.myapplication.adapters.DetailCharacterAdapter
import com.eliane.myapplication.api.RickMorthAPI
import com.eliane.myapplication.databinding.DetailPersonagemBinding
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers.IO
import org.koin.java.KoinJavaComponent.inject


class CharacterDetails : Activity(), View.OnClickListener {

    lateinit var binding: DetailPersonagemBinding
    lateinit var adapter: DetailCharacterAdapter

    var id = 1
    val serviceAPI: RickMorthAPI by inject(RickMorthAPI::class.java)

    companion object {
        const val EXTRA_MESSAGE = "character-id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pegando a informação da intent criada
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        if (message != null) {
            id = message.toInt()
        } else {
            Log.d("CharacterDetail", "FALHA ao carregar o message")
            finish()
        }

        binding = DetailPersonagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        GlobalScope.launch(IO) {
            val call = serviceAPI.getCharacterById(id).execute()
            val detail = call.body()?.detailCharacter

            withContext(Main) {
                detail?.let {
                    adapter.loadCharacter
                }
            }
        }
    }
}