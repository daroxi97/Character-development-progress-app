package com.example.characterDevelopment.domain.Models


interface ICharacterLevelSetUseCase {

    operator fun invoke(character: CharacterDomainModel): Int

}