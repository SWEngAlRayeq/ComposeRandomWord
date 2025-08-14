package app.random_word.domain.usecase

import app.random_word.domain.repo.RandomWordRepository
import javax.inject.Inject

class GetRandomWordUseCase @Inject constructor(
    private val repository: RandomWordRepository
) {
    suspend operator fun invoke(): Result<String> {
        return repository.getRandomWord()
    }
}