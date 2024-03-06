package com.example.characterDevelopment.data.repository

import com.example.characterDevelopment.data.database.entities.SettingsEntity
import com.example.characterDevelopment.domain.models.SettingsDomainModel

interface ISettingsRepository {
    suspend fun getSettingsFromDataBase(): SettingsDomainModel?
    suspend fun insertSettings(settings: SettingsEntity)

}
