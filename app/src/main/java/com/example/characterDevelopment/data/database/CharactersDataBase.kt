package com.example.characterDevelopment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.characterDevelopment.data.database.dao.CharactersDao
import com.example.characterDevelopment.data.database.entities.CharacterEntity
import com.example.characterDevelopment.data.database.entities.SettingsEntity

@Database(entities = [CharacterEntity::class, SettingsEntity::class], version = 4)
abstract class CharactersDataBase : RoomDatabase() {

    abstract fun getCharactersDao(): CharactersDao
}