package com.example.characterDevelopment.domain.useCases

import com.example.characterDevelopment.data.repository.CharactersRepositoryImpl
import javax.inject.Inject

class DeleteCharacterUseCase @Inject constructor(private val repository: CharactersRepositoryImpl) {
    suspend operator fun invoke(characterId: Int) {
//Check if the user removed the main profile from the database
        val isDeletedMainCharacter =
            repository.getCharacterWithId(characterId)?.mainCharacter ?: false

        repository.deleteCharacter(characterId)

        /*In case is the main profile, automatically set the first profile from the database as the main
        /profile as placeholder until the user set another one
         */
        if (isDeletedMainCharacter) {
            val characters = repository.getAllCharactersFromDatabase().toMutableList()
            if (characters.isNotEmpty()) {
                repository.updateCharacter(characters[0].id, true)
            }
        }
    }
}

