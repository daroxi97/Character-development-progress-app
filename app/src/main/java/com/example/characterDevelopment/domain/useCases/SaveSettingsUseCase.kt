package com.example.characterDevelopment.domain.useCases

import com.example.characterDevelopment.data.database.entities.toDatabase
import com.example.characterDevelopment.data.repository.SettingsRepositoryImpl
import com.example.characterDevelopment.domain.models.SettingsDomainModel
import javax.inject.Inject

class SaveSettingsUseCase @Inject constructor(private val repository: SettingsRepositoryImpl) {
    suspend operator fun invoke(settings: SettingsDomainModel) {
        return repository.insertSettings(settings.toDatabase())
    }
}
