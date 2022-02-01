package com.eliane.myapplication

import com.eliane.myapplication.model.Character
import com.eliane.myapplication.model.Pagination

data class CharactersRequest(
    val info: Pagination,
    val results: List<Character>,
    val detailCharacter: Character,
)