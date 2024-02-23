package com.example.characterDevelopment.domain.UseCases

import com.example.characterDevelopment.data.database.entities.Health
import com.example.characterDevelopment.data.database.entities.Mood
import com.example.characterDevelopment.data.database.entities.PhysicalCondition
import com.example.characterDevelopment.domain.Models.CharacterDomainModel
import com.example.characterDevelopment.domain.Models.ICharacterLevelSetUseCase
import kotlin.math.pow


class CharacterLevelSetUseCase() : ICharacterLevelSetUseCase {
    lateinit var character: CharacterDomainModel
    private var formulaVariables = 5f

    override operator fun invoke(character: CharacterDomainModel): Int {
        this.character = character
        var divisor = 1f / formulaVariables
        return (divisor * setHappinessValue() + divisor * setHealthValue() + divisor * setSalaryValue() + divisor * setPhysicConditionValue() + divisor * setPatrimonyValue()).toInt()
    }

    private fun setSalaryValue(): Float {
        var exponent = 0.00023
        return exponentialFunction(character.salary, exponent)
    }

    private fun setPatrimonyValue(): Float {
        var exponent = 0.000003
        return exponentialFunction(character.patrimony, exponent)
    }

    private fun setHealthValue(): Float {
        return when (character.health) {
            Health.HORRIBLE -> 0f
            Health.UNHEALTHY -> 25f
            Health.NORMAL -> 50f
            Health.FINE -> 75f
            Health.HEALTHY -> 100f
        }
    }

    private fun setHappinessValue(): Float {
        return when (character.happiness) {
            Mood.DEVASTATED -> 0f
            Mood.SAD -> 25f
            Mood.NORMAL -> 50f
            Mood.HAPPY -> 75f
            Mood.ECSTATIC -> 100f
        }
    }

    private fun setPhysicConditionValue(): Float {
        return when (character.physicalCondition) {
            PhysicalCondition.UNFIT -> 0f
            PhysicalCondition.NOSHAPE -> 25f
            PhysicalCondition.AVERAGE -> 50f
            PhysicalCondition.FINE -> 75f
            PhysicalCondition.FIT -> 100f
        }
    }

    private fun exponentialFunction(parametro: Int, exponent: Double): Float {
        /*return a value from 0 to 100 according to an initial parameter and a custom exponent to create a
         exponential function according to the user requirements
         */
        var base = kotlin.math.E
        val result = 1 - base.pow(-exponent * parametro)
        return result.coerceIn(0.0, 1.0).toFloat() * 100f
    }


}