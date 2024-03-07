package com.example.characterDevelopment.domain.useCases

import com.example.characterDevelopment.data.database.entities.AppLanguage
import com.example.characterDevelopment.data.database.entities.Order
import com.example.characterDevelopment.data.database.entities.Theme
import com.example.characterDevelopment.data.repository.SettingsRepositoryImpl
import com.example.characterDevelopment.domain.models.SettingsDomainModel
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(private val repository: SettingsRepositoryImpl) {
    suspend operator fun invoke(): SettingsDomainModel {
//Obtain the settings from database if posible, if not create default settings.

        return repository.getSettingsFromDataBase()
            ?: SettingsDomainModel(
                language = AppLanguage.DEFAULT,
                theme = Theme.DARK,
                order = Order.NAME
            )

    }
}
