package com.example.characterDevelopment.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.characterDevelopment.domain.models.SettingsDomainModel

@Entity(tableName = "settings_table")
data class SettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int = 1,
    @ColumnInfo(name = "theme") var theme: Theme = Theme.DARK,
    @ColumnInfo(name = "language") var language: AppLanguage = AppLanguage.DEFAULT,
    @ColumnInfo(name = "order") var order: Order = Order.DATE,

    )

fun SettingsDomainModel.toDatabase() =
    SettingsEntity(
        theme = theme,
        language = language,
        order = order
    )

/*The description will be used in the views for the user
  The value will be used as a key to change the language
 */
enum class AppLanguage(var description: String, val value: String) {
    DEFAULT("", ""), CATALAN("Català", "ca"), SPANISH("Español", "es"), ENGLISH("English", "en")
}

enum class Theme(var description: String) {
    DARK("Dark mode"), LIGHT("Light mode")
}

//The description will be used in the views for the user
enum class Order(var description: String) {
    NAME("Order by name"), LEVEL("Order by level"), DATE("Order by date")
}

//Create the list of languages this way so in case I add more moods later, it will get updated automatically in the rest of the app.
val languages: List<String>
    get() {
        return mutableListOf<String>().apply {
            for (languageValue in AppLanguage.values()) {
                if (languageValue != AppLanguage.DEFAULT) {
                    add(languageValue.description)
                }
            }
        }
    }

val themes: List<String>
    get() {
        return mutableListOf<String>().apply {
            for (themeValue in Theme.values()) {
                add(themeValue.description)
            }
        }
    }

val orders: List<String>
    get() {
        return mutableListOf<String>().apply {
            for (orderValue in Order.values()) {
                add(orderValue.description)
            }
        }
    }


