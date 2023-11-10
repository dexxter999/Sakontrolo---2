package com.example.anagramsapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.anagramsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var anagramsGroup = mutableListOf<MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val word = binding.etEnterAnagram.text.toString()

            if (word.isNotEmpty()) {
                addWordToAnagramGroups(word)
                binding.etEnterAnagram.text.clear()
            }

        }

        binding.buttonOutput.setOnClickListener {
            groupAnagrams()
            updateAnagramsTextView()
            updateAnagramsCount()
        }

    }


    private fun addWordToAnagramGroups(word: String) {
        for (group in anagramsGroup) {
            if (group.any { it.toCharArray().sorted() == word.toCharArray().sorted() }) {
                group.add(word)
                return
            }
        }

        anagramsGroup.add(mutableListOf(word))
    }

    private fun groupAnagrams() {
        val inputWords = binding.etEnterAnagram.text.toString().split(",").map { it.trim() }


        for (word in inputWords) {
            addWordToAnagramGroups(word)
        }
    }


    private fun updateAnagramsCount() {
        "Anagrams Count: ${anagramsGroup.size}".also { binding.tvAnagramsCount.text = it }
    }

    private fun updateAnagramsTextView() {
        val anagramsList = mutableListOf<String>()
        val nonAnagramsList = mutableListOf<String>()

        for (group in anagramsGroup) {
            if (group.size > 1) {
                anagramsList.addAll(group)
            } else {

                nonAnagramsList.addAll(group)
            }
        }

        val anagramsText = "Anagrams: ${anagramsGroup.joinToString(", ")}"

        binding.tvAnagramsContainer.text = anagramsText
    }
}










