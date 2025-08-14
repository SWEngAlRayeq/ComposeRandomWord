package app.random_word.domain.repo

interface RandomWordRepository {
    suspend fun getRandomWord(): Result<String>
}