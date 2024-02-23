package com.example.characterDevelopment.data.di

import android.content.Context
import androidx.room.Room
import com.example.characterDevelopment.data.database.CharactersDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val CHARACTERS_DATABASE_NAME = "characters_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CharactersDataBase::class.java, CHARACTERS_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCharactersDao(db: CharactersDataBase) = db.getCharactersDao()
}


