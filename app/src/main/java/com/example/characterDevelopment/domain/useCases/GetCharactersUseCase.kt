package com.example.characterDevelopment.domain.useCases

import com.example.characterDevelopment.data.repository.CharactersRepositoryImpl
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.domain.models.CharacterDomainModel
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepositoryImpl) {
    suspend operator fun invoke(orderPattern: Order = Order.NAME): List<CharacterDomainModel>? {
//Obtain the list of character profiles from database if posible
        val characters = repository.getAllCharactersFromDatabase(orderPattern)
        //return characters if are available on database, if not return null
        return characters.takeIf { it.isNotEmpty() }

    }
}
