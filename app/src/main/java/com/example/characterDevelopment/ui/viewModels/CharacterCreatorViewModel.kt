package com.example.characterDevelopment.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterDevelopment.data.database.entities.Health
import com.example.characterDevelopment.data.database.entities.Mood
import com.example.characterDevelopment.data.database.entities.PhysicalCondition
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import com.example.characterDevelopment.domain.UseCases.DeleteAllCharactersUseCase
import com.example.characterDevelopment.domain.UseCases.DeleteCharacterUseCase
import com.example.characterDevelopment.domain.UseCases.GetCharactersUseCase
import com.example.characterDevelopment.domain.UseCases.GetSettingsUseCase
import com.example.characterDevelopment.domain.UseCases.SaveCharacterUseCase
import com.example.characterDevelopment.utils.getOrderFromDescription
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterCreatorViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val deleteAllCharactersUseCase: DeleteAllCharactersUseCase

) : ViewModel() {

    //I use livedata instead of mutable state to show different approaches of reacting code.
    private val _listCharacters = MutableLiveData<List<CharacterDomainModel>?>()
    val listCharacters: MutableLiveData<List<CharacterDomainModel>?> get() = _listCharacters

    //The current character will be the one set by default to show to the user his main information.
    private val _currentCharacter = MutableLiveData<CharacterDomainModel>()
    val currentCharacter: LiveData<CharacterDomainModel> get() = _currentCharacter

    //I use this variable to make sure the list of profiles is updated before letting the user trying to use them
    private val _firstCoroutineCompleted = MutableLiveData<Boolean>(false)
    val firstCoroutineCompleted: LiveData<Boolean> get() = _firstCoroutineCompleted

    init {
        updateListOfCharacters()
    }

    private fun updateListOfCharacters() {
        //Update the list of profiles with the information of the database.
        viewModelScope.launch() {
            val configuration = getSettingsUseCase()
            val characters = getCharactersUseCase(configuration.order)
            _listCharacters.value = characters
            _firstCoroutineCompleted.value = true
        }
    }

    fun addNewCharacter(
        name: String,
        date: String,
        salary: Int,
        age: Int,
        patrimony: Int,
        health: Health,
        mood: Mood,
        physicalCondition: PhysicalCondition,
        mainCharacter: Boolean
    ) {
        //Pass variables one by one instead of passing directly the domainModel, to give less responsibilities to the view
        val character = CharacterDomainModel(
            name,
            date,
            salary,
            age,
            patrimony,
            health,
            mood,
            physicalCondition,
            mainCharacter
        )
        viewModelScope.launch {
            saveCharacterUseCase(character)
            updateListOfCharacters()
        }
    }

    fun setSelectedCharacter(character: CharacterDomainModel) {
        _currentCharacter.value = character
    }

    fun getMainCharacter(): CharacterDomainModel? {
        return listCharacters.value?.find { it.mainCharacter }
    }

    fun removeCharacter(characterId: Int) {
        viewModelScope.launch {
            deleteCharacterUseCase(characterId)
            updateListOfCharacters()
        }
    }

    fun deleteAllCharactersFromDatabase() {
        viewModelScope.launch {
            deleteAllCharactersUseCase()
            updateListOfCharacters()
        }
    }

    fun reorderCharacterList(orderDescription: String) {
        //Reorder the actual list of profiles. The configuration save of the new reorder relies on configuration viewModel.
        viewModelScope.launch() {
            val orderPattern = getOrderFromDescription(orderDescription)
            val characters = getCharactersUseCase(orderPattern)
            _listCharacters.value = characters
        }
    }
}
