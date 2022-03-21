package com.android.composition.domain.repository

import com.android.composition.domain.entities.GameSettings
import com.android.composition.domain.entities.Level
import com.android.composition.domain.entities.Question

interface GameRepository {
    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}