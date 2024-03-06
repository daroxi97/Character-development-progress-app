package com.example.characterDevelopment.domain.models

import com.example.characterDevelopment.data.database.entities.CharacterEntity
import com.example.characterDevelopment.data.database.entities.Health
import com.example.characterDevelopment.data.database.entities.Mood
import com.example.characterDevelopment.data.database.entities.PhysicalCondition
import com.example.characterDevelopment.domain.useCases.CharacterLevelSetUseCase
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class CharacterDomainModel(
    var name: String,
    var date: String,
    var salary: Int,
    var age: Int,
    var patrimony: Int,
    var health: Health,
    var happiness: Mood,
    var physicalCondition: PhysicalCondition,
    var mainCharacter: Boolean,
    var id: Int = 0,
    //Use of an interface, in case we want to inject another way of calculating the level.
    var levelBuilder: ICharacterLevelSetUseCase = CharacterLevelSetUseCase()
) {
    var level: Int = 0

    init {
        level = levelBuilder(this)
    }
}

//map the data model (entity) to the domain model
fun CharacterEntity.toDomain(): CharacterDomainModel {
    return CharacterDomainModel(
        name,
        reformatDateToDomain(date),
        salary,
        age,
        patrimony,
        health,
        happiness,
        physicalCondition,
        mainCharacter,
        id
    )
}

private fun reformatDateToDomain(dateString: String): String {
    return try {
        //modify the format, to be more visually appealing for the UI
        val initialFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date: Date? = initialFormat.parse(dateString)
        val desiredFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        if (date != null) {
            desiredFormat.format(date)
        }
        else{
            dateString
        }
    } catch (e: ParseException) {
        e.printStackTrace()
        dateString
    }
}


