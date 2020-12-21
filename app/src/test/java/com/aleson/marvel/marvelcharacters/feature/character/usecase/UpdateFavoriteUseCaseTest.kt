package com.aleson.marvel.marvelcharacters.feature.character.usecase

import com.aleson.marvel.marvelcharacters.core.model.character.*
import com.aleson.marvel.marvelcharacters.core.model.error.ErrorModel
import com.aleson.marvel.marvelcharacters.feature.character.repository.CharactersRepository
import com.aleson.marvel.marvelcharacters.feature.character.repository.data.CharactersDataSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class UpdateFavoriteUseCaseTest {

    private val limit = "20"
    private val offset = 0
    private val order = "name"
    private val name = "John Doe"

    lateinit var character: Character

    @Mock
    lateinit var source: CharactersDataSource

    lateinit var repository: CharactersRepository

    lateinit var useCase: UpdateFavoriteUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CharactersRepository(source)
        useCase = UpdateFavoriteUseCase(repository)
        character = Character(1, Image(), "John Doe", "Does Heroes stuff", Comics(), Series(), true)
    }

    @Test
    fun execute() {
        val onResponse: (UpdateFavoriteResponse?) -> Unit = {}

        val onError: (ErrorModel?) -> Unit = {}

        val characters: MutableList<Character> = mutableListOf()

        characters.add(character)

        useCase.request = UpdateFavoriteRequest(character)
        useCase.execute(onResponse, onError)
    }
}