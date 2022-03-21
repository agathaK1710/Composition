package com.android.composition.domain.usecases

import com.android.composition.domain.entities.GameSettings
import com.android.composition.domain.entities.Level
import com.android.composition.domain.repository.GameRepository

class GetGameSettingUseCase(private val repository: GameRepository){

    operator fun invoke(level: Level): GameSettings{
        return repository.getGameSettings(level)
    }
}