package com.eliane.myapplication.api

import com.eliane.myapplication.CharactersRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RickMorthAPI {

    @GET("character")
    fun listCharacters(
        @Query("page") page: Int
    ): Call<CharactersRequest>

    @GET("character/{id}")
    fun getCharacterById(
        @Path("id") id: Int
    ): Call<CharactersRequest>
}