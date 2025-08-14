package app.random_word.data.repo_impl

import app.random_word.data.remote.RandomWordApi
import app.random_word.domain.repo.RandomWordRepository
import javax.inject.Inject

class RandomWordRepositoryImpl @Inject constructor(
    private val api: RandomWordApi
) : RandomWordRepository {
    override suspend fun getRandomWord(): Result<String> {
        return try {
            val response = api.getRandomWord()
            if (response.isNotEmpty()) {
                Result.success(response[0])
            } else {
                Result.failure(Exception("No word found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}