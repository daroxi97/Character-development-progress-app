package com.example.characterDevelopment.data.repository

import com.example.characterDevelopment.data.database.entities.CharacterEntity
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.domain.models.CharacterDomainModel

interface ICharactersRepository {
    suspend fun getAllCharactersFromDatabase(orderPattern: Order = Order.NAME): List<CharacterDomainModel>
    suspend fun getCharacterWithId(id: Int): CharacterDomainModel?
    suspend fun insertCharacters(characters: List<CharacterEntity>)
    suspend fun insertCharacter(character: CharacterEntity)
    suspend fun updateCharacter(id: Int, mainCharacter : Boolean)
    suspend fun deleteCharacter(id: Int)
    suspend fun deleteCharacters()
}

