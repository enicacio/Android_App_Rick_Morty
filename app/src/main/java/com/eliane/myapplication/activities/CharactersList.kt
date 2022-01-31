package com.eliane.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.R
import com.eliane.myapplication.adapters.CharactersAdapter
import com.eliane.myapplication.api.RickMorthAPI
import com.eliane.myapplication.databinding.ListagemBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharactersList : Activity(), View.OnClickListener {

    lateinit var binding: ListagemBinding
    lateinit var adapter: CharactersAdapter

    var page = 1
    var carregando = false
    val serviceAPI: RickMorthAPI by inject(RickMorthAPI::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListagemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initList()
        carregarPersonagens()

        // O click no botão aciona o listener
        findViewById<Button>(R.id.char_button_details)?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        var idCharacter = findViewById<TextView>(R.id.char_id)
        var message = idCharacter.text.toString()
        val intent = Intent(this, CharacterDetails::class.java).apply {
            putExtra("character id", message)
        }
        startActivity(intent)
    }

    private fun initList() {
        adapter = CharactersAdapter(layoutInflater)
        binding.charsList.adapter = adapter

        //Scroller
        binding.charsList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               if(!recyclerView.canScrollVertically(1) && !carregando) {
                    carregarPersonagens()
                }
            }
        })
    }

    private fun carregarPersonagens() {
        binding.progressBar.visibility = View.VISIBLE //imagem loading
        carregando = true

        GlobalScope.launch(IO) {
            val call = serviceAPI.listCharacters(page).execute()
            val list = call.body()?.results

            withContext(Main) {
                list?.let {
                    adapter.addCharacter(it)
                    page++
                }
            }
            carregando = false
            binding.progressBar.visibility = View.GONE
        }
    }
}