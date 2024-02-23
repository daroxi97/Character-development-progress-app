package com.example.characterDevelopment.domain.UseCases

import android.util.Log
import com.example.characterDevelopment.data.database.entities.toDatabase
import com.example.characterDevelopment.data.repository.CharactersRepositoryImpl
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import javax.inject.Inject

class SaveCharacterUseCase @Inject constructor(private val repository: CharactersRepositoryImpl) {
    suspend operator fun invoke(character: CharacterDomainModel) {


        var characters = repository.getAllCharactersFromDatabase().toMutableList()

        //If user created a new main profile, delete the last one state as main profile
        if (character.mainCharacter) {
            removeLastMainProfile(characters)
        }
        //if is the first profile created, by default set as main profile
        if (characters.size <= 1) {
            character.mainCharacter = true
        }
        //Add new profile to the database
        repository.insertCharacter(character.toDatabase())
    }

    private suspend fun removeLastMainProfile(characters: MutableList<CharacterDomainModel>) {
        characters.forEach {
            if (it.mainCharacter) {
                repository.updateCharacter(it.id, false)
            }
        }
    }
}
