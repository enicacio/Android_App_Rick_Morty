package com.eliane.myapplication

import com.eliane.myapplication.model.Character

data class CharactersRequest(
    val info: Pagination,
    val results: List<Character>,
    val detailCharacter: Character,
)