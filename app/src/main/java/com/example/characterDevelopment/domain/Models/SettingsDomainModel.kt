package com.example.characterDevelopment.domain.Models

import com.example.characterDevelopment.data.database.entities.AppLanguage
import com.example.characterDevelopment.data.database.entities.SettingsEntity
import com.example.characterDevelopment.data.database.entities.Theme
import com.example.characterDevelopment.data.database.entities.Order

data class SettingsDomainModel(

    var theme: Theme = Theme.DARK,
    var language: AppLanguage = AppLanguage.DEFAULT,
    var order: Order = Order.NAME
)

//map the settings data model (entity) to the domain model
fun SettingsEntity.toDomain() =
    SettingsDomainModel(
        theme = theme,
        language = language,
        order = order
    )


