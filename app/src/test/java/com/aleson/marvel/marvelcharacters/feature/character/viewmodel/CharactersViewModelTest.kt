package com.aleson.marvel.marvelcharacters.feature.character.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aleson.marvel.marvelcharacters.MainCoroutineRule
import com.aleson.marvel.marvelcharacters.core.model.character.Character
import com.aleson.marvel.marvelcharacters.core.model.character.Comics
import com.aleson.marvel.marvelcharacters.core.model.character.Image
import com.aleson.marvel.marvelcharacters.core.model.character.Series
import com.aleson.marvel.marvelcharacters.feature.character.usecase.*
import com.aleson.marvel.marvelcharacters.feature.character.view.event.CharactersViewEvent
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var updateFavoriteUseCase: UpdateFavoriteUseCase

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    lateinit var getFavoritesUseCase: GetFavoritesUseCase

    @Mock
    lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Mock
    lateinit var onResponse: (Boolean?) -> Unit

    lateinit var viewModel: CharactersViewModel

    lateinit var character: Character

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CharactersViewModel(
            getCharactersUseCase,
            updateFavoriteUseCase,
            getFavoriteUseCase,
            getFavoritesUseCase
        )
        character = Character(1, Image(), "John Doe", "Does Heroes stuff", Comics(), Series(), true)
    }

    @Test
    fun getFavorites() = mainCoroutineRule.testDispatcher.runBlockingTest {

        val characters: MutableList<Character> = mutableListOf()

        characters.add(character)

        `when`(getFavoritesUseCase.execute(any(), any())).thenAnswer {
            viewModel.events.postValue(CharactersViewEvent.OnLoadFavorites(characters))
        }

        viewModel.getFavorites()

        Assert.assertNotNull(viewModel.events.value)
        Assert.assertEquals(
            "John Doe",
            (viewModel.events.value as CharactersViewEvent.OnLoadFavorites).characters?.first()?.name
        )
    }


    @Test
    fun fetch() = mainCoroutineRule.testDispatcher.runBlockingTest {

        val characters: MutableList<Character> = mutableListOf()

        characters.add(character)

        `when`(getCharactersUseCase.execute(any(), any())).thenAnswer {
            viewModel.events.postValue(CharactersViewEvent.OnLoadMoreCharacters(characters))
        }

        viewModel.fetch("", 0)

        Assert.assertNotNull(viewModel.events.value)
        Assert.assertEquals(
            "John Doe",
            (viewModel.events.value as CharactersViewEvent.OnLoadMoreCharacters).characters?.first()?.name
        )
    }

    @Test
    fun search() = mainCoroutineRule.testDispatcher.runBlockingTest {

        val characters: MutableList<Character> = mutableListOf()

        characters.add(character)

        `when`(getCharactersUseCase.execute(any(), any())).thenAnswer {
            viewModel.events.postValue(CharactersViewEvent.OnLoadMoreCharacters(characters))
        }

        viewModel.fetch("", 0)

        Assert.assertNotNull(viewModel.events.value)
        Assert.assertEquals(
            "John Doe",
            (viewModel.events.value as CharactersViewEvent.OnLoadMoreCharacters).characters?.first()?.name
        )
    }

    @Test
    fun getFavoriteStatus() {

        viewModel.getFavoriteStatus(0, onResponse)

        `when`(getFavoriteUseCase.execute(any(), any())).thenAnswer { true }

        viewModel.getFavoriteStatus(0) {
            print("")
        }

    }

    @Test
    fun updateFavorite() {

        `when`(updateFavoriteUseCase.execute(any(), any())).thenAnswer {
            viewModel.events.postValue(CharactersViewEvent.OnFavoriteUpdated(character))

            viewModel.updateFavorite(character)

            Assert.assertNotNull(viewModel.events.value)
        }
    }

    @Test
    fun onError() {
    }

}