package com.example.characterDevelopment.domain.UseCases

import com.example.characterDevelopment.data.repository.CharactersRepositoryImpl
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepositoryImpl) {
    suspend operator fun invoke(orderPattern: Order = Order.NAME): List<CharacterDomainModel>? {
//Obtain the list of character profiles from database if posible
        var characters = repository.getAllCharactersFromDatabase(orderPattern)
        return if (characters.isNotEmpty()) {
            return repository.getAllCharactersFromDatabase(orderPattern)
        } else {
            return null
        }
    }
}
