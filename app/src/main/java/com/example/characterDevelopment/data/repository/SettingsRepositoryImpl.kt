package com.example.characterDevelopment.data.repository

import android.util.Log
import com.example.characterDevelopment.data.database.dao.CharactersDao
import com.example.characterDevelopment.data.database.entities.SettingsEntity
import com.example.characterDevelopment.domain.Models.SettingsDomainModel
import com.example.characterDevelopment.domain.Models.toDomain
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao) :
    ISettingsRepository {

    //Obtain the settings from room. In case the user didnt change anything yet, returns null.
    override suspend fun getSettingsFromDataBase(): SettingsDomainModel? {
        return try {
            charactersDao.getSettings()?.toDomain() ?: null
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error getting settings from room")
            null
        }
    }

    override suspend fun insertSettings(settings: SettingsEntity) {
        try {
            charactersDao.insertSettings(settings)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d("error", "Error inserting settings on room")
        }
    }

}
