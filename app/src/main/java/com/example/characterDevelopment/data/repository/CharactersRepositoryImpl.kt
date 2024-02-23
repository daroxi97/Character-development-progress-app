package com.example.characterDevelopment.data.repository

import android.util.Log
import com.example.characterDevelopment.data.database.dao.CharactersDao
import com.example.characterDevelopment.data.database.entities.CharacterEntity
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import com.example.characterDevelopment.domain.Models.toDomain
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao) :
    ICharactersRepository {

    override suspend fun getAllCharactersFromDatabase(orderPattern: Order): List<CharacterDomainModel> {
        return try {
            val characters: List<CharacterEntity> = when (orderPattern) {
                Order.NAME -> charactersDao.getAllCharactersOrderedByName()
                Order.LEVEL -> charactersDao.getAllCharactersOrderedByLevel()
                Order.DATE -> charactersDao.getAllCharactersOrderedByDate()
                else -> charactersDao.getAllCharactersOrderedByName()
            }
            mapCharactersModelFromDatabaseToDomain(characters)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error getting character profiles from room")
            emptyList()
        }
    }

    override suspend fun getCharacterWithId(id: Int): CharacterDomainModel? {
        return try {
            charactersDao.getCharacterFromId(id).toDomain()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error getting a profile with the id: $id")
            null
        }
    }


    override suspend fun insertCharacters(characters: List<CharacterEntity>) {
        try {
            charactersDao.insertAllCharacters(characters)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error inserting a list of character profiles on room")
        }
    }

    override suspend fun insertCharacter(character: CharacterEntity) {
        try {
            charactersDao.insertCharacter(character)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error inserting a character profile on room")
        }
    }

    override suspend fun updateCharacter(id: Int, mainCharacter : Boolean) {
        try {
            charactersDao.updateMainCharacter(id, mainCharacter)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error updating the character profile on room with the id : $id")
        }
    }

    override suspend fun deleteCharacters() {
        try {
            charactersDao.deleteAllCharacters()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error deleting all the profiles from database")
        }
    }

    override suspend fun deleteCharacter(id: Int) {
        try {
            charactersDao.deleteCharacterWithId(id)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error deleting the character with the id: $id")
        }
    }

    private fun mapCharactersModelFromDatabaseToDomain(characters: List<CharacterEntity>): List<CharacterDomainModel> {
        val mappedCharacters = characters.map {
            it.toDomain()
        }
        return mappedCharacters
    }
}




