package com.example.truelogicchallenge.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truelogicchallenge.R
import com.example.truelogicchallenge.databinding.ActivityMainBinding
import com.example.truelogicchallenge.domain.model.Character
import com.example.truelogicchallenge.presentation.adapter.CharacterAdapter
import com.example.truelogicchallenge.presentation.model.PCharacter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()

    private val APP_PREF_1 = "shf"
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareObservers()
        initComponents()
    }

    private fun initComponents() {
        viewModel.initEvent(MainViewModel.MainEvent.FetchCharacterList)
        title = getString(R.string.main_title)
        preferences = this.getSharedPreferences(APP_PREF_1, Context.MODE_PRIVATE)
    }

    private fun prepareObservers() {
        viewModel.getViewState().observe(this@MainActivity) { state ->
            when(state) {
                is MainViewModel.MainViewState.GetCharacterList -> checkFavourite(state.characterList)
            }
        }
    }

    private fun handleCharacterList(characterList: List<PCharacter>) {
        binding.progressBar.visibility = View.GONE
        binding.characterRecycler.apply {
            this.layoutManager = LinearLayoutManager(context)
            val adapter = CharacterAdapter(::handleToggleClick)
            adapter.setList(characterList.sortedByDescending { it.isFavorite })
            this.adapter = adapter
        }
    }

    private fun handleToggleClick(toggleState: Boolean, characterId: Int) {
        if (toggleState) {
            if (!getSPList().contains(characterId)) {
                val set = getSPList()
                set.add(characterId)
                setLists(set)
            }
        } else {
            if (getSPList().contains(characterId)) {
                val set = getSPList()
                set.remove(characterId)
                setLists(set)
            }
        }
    }

    private fun checkFavourite(characterList: List<Character>) {
        val finalList = mutableListOf<PCharacter>()
        characterList.forEach {  character ->
            if (getSPList().contains(character.charId)) {
                finalList.add(
                    PCharacter(
                    charId = character.charId,
                    name = character.name,
                    nickname = character.nickname,
                    image = character.image,
                    isFavorite = true
                ))
            } else {
                finalList.add(
                    PCharacter(
                        charId = character.charId,
                        name = character.name,
                        nickname = character.nickname,
                        image = character.image,
                        isFavorite = false
                    ))
            }
        }
        handleCharacterList(finalList)
    }

    fun setLists(list: ArrayList<Int>){
        if (list.isNotEmpty()) {
            val gson = Gson()
            val json = gson.toJson(list)
            with(preferences.edit()) {
                putString("LIST", json)
                apply()
            }
        }
    }

    private fun getSPList(): ArrayList<Int>{
        val gson = Gson()
        val json = preferences.getString("LIST",null)
        return if (json != null) {
            val type = object : TypeToken<ArrayList<Int>>(){}.type
            gson.fromJson(json,type)
        } else {
            arrayListOf()
        }
    }
}