package com.example.characterDevelopment.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity(tableName = "characters_table")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "salary") var salary: Int,
    @ColumnInfo(name = "health") var health: Health,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "happiness") var happiness: Mood,
    @ColumnInfo(name = "physicalCondition") var physicalCondition: PhysicalCondition,
    @ColumnInfo(name = "patrimony") var patrimony: Int,
    @ColumnInfo(name = "mainCharacter") var mainCharacter: Boolean,
    @ColumnInfo(name = "level") var level: Int = 0
)

fun CharacterDomainModel.toDatabase() =
    CharacterEntity(
        name = name,
        date = reformatDateToDataBase(date),
        salary = salary,
        health = health,
        age = age,
        happiness = happiness,
        physicalCondition = physicalCondition,
        patrimony = patrimony,
        mainCharacter = mainCharacter,
        level = level
    )

private fun reformatDateToDataBase(dateString: String): String {
    return try {
        // Modificar el formato para que Room pueda ordenar adecuadamente los caracteres por fecha
        val initialFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date: Date = initialFormat.parse(dateString)

        val desiredFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        desiredFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
        dateString
    }
}

//The descriptions will be used in the views for the user
enum class Mood(var description: String) {
    DEVASTATED("Devastated"), SAD("Sad"), NORMAL("Normal"), HAPPY("Happy"), ECSTATIC("Ecstatic")

}

enum class Health(var description: String) {
    HORRIBLE("Horrible"), UNHEALTHY("Unhealthy"), NORMAL("Normal"), FINE("Fine"), HEALTHY("Healthy")
}

enum class PhysicalCondition(var description: String) {
    UNFIT("Unfit"), NOSHAPE("Out of shape"), AVERAGE("Average"), FINE("Fine"), FIT("Fit")
}

//Create the list of moods this way so in case I add more moods later, it will get updated automatically in the rest of the app.
var moods: List<String> = emptyList()
    get() {
        return mutableListOf<String>().apply {
            for (moodValue in Mood.values()) {
                add(moodValue.description)
            }
        }
    }

var healths: List<String> = emptyList()
    get() {
        return mutableListOf<String>().apply {
            for (healthValue in Health.values()) {
                add(healthValue.description)
            }
        }
    }

var physicalConditions: List<String> = emptyList()
    get() {
        return mutableListOf<String>().apply {
            for (physicValue in PhysicalCondition.values()) {
                add(physicValue.description)
            }
        }
    }


