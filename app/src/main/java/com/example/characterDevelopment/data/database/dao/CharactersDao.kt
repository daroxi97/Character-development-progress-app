package com.example.characterDevelopment.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.characterDevelopment.data.database.entities.CharacterEntity
import com.example.characterDevelopment.data.database.entities.SettingsEntity

@Dao
interface CharactersDao {
    @Query("SELECT * FROM characters_table ORDER BY date DESC, name ASC, level DESC")
    suspend fun getAllCharactersOrderedByDate (): List <CharacterEntity>

    @Query("SELECT * FROM characters_table ORDER BY name ASC, date DESC, level DESC")
    suspend fun getAllCharactersOrderedByName (): List <CharacterEntity>

    @Query("SELECT * FROM characters_table ORDER BY level DESC, name ASC, date DESC")
    suspend fun getAllCharactersOrderedByLevel (): List <CharacterEntity>

    @Query("SELECT * FROM characters_table  WHERE id = :id")
    suspend fun getCharacterFromId (id: Int): CharacterEntity

    @Query("SELECT * FROM settings_table WHERE id = 1")
    suspend fun getSettings (): SettingsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterEntity)

    @Query("UPDATE characters_table SET mainCharacter=:mainCharacter WHERE id = :id")
    suspend fun updateMainCharacter (id: Int, mainCharacter: Boolean)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters (characters : List <CharacterEntity>)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings (settings : SettingsEntity)

    @Query("DELETE FROM characters_table WHERE id = :id")
    suspend fun deleteCharacterWithId (id: Int)

    @Query ("DELETE FROM characters_table")
    suspend fun deleteAllCharacters ()

    @Query ("DELETE FROM settings_table")
    suspend fun deleteSettings ()

}