package com.example.characterDevelopment.domain.useCases

import com.example.characterDevelopment.data.repository.CharactersRepositoryImpl
import javax.inject.Inject

class DeleteAllCharactersUseCase @Inject constructor(private val repository: CharactersRepositoryImpl) {
    suspend operator fun invoke() {
        repository.deleteCharacters()
    }
}

