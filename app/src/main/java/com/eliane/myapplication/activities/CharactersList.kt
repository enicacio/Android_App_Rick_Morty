package com.eliane.myapplication.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.eliane.myapplication.activities.CharacterDetails.Companion.EXTRA_MESSAGE
import com.eliane.myapplication.adapters.CharactersAdapter
import com.eliane.myapplication.api.RickMorthAPI
import com.eliane.myapplication.databinding.ListagemBinding
import com.eliane.myapplication.model.Character
import com.eliane.myapplication.view.onClickCharacterListenner
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject


class CharactersList : Activity(), onClickCharacterListenner{

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
    }


    private fun initList() {
        adapter = CharactersAdapter(layoutInflater, this)
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
        binding.imageView2.visibility = View.VISIBLE
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

    override fun onDetailCharacter(character: Character) {
        val intent = Intent(this, CharacterDetails::class.java).apply {
            putExtra(EXTRA_MESSAGE, character)
        }
        startActivity(intent)
    }
}