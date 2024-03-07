package com.example.characterDevelopment.domain.models


interface ICharacterLevelSetUseCase {

    operator fun invoke(character: CharacterDomainModel): Int

}