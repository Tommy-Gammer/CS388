package com.example.wordle

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    private lateinit var wordToGuess: String
    private var guessCount = 0
    private lateinit var input: EditText
    private lateinit var btnGuess: Button
    private lateinit var tvGuess1: TextView
    private lateinit var tvGuess2: TextView
    private lateinit var tvGuess3: TextView
    private lateinit var tvCheck1: TextView
    private lateinit var tvCheck2: TextView
    private lateinit var tvCheck3: TextView
    private lateinit var tvAnswer: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordToGuess = FourLetterWordList.getRandomFourLetterWord()

        // tv is for text view

        input = findViewById(R.id.userGuess)
        btnGuess = findViewById(R.id.guessButton)
        tvGuess1 = findViewById(R.id.Guess1)
        tvGuess2 = findViewById(R.id.Guess2)
        tvGuess3 = findViewById(R.id.Guess3)
        tvCheck1 = findViewById(R.id.Check1)
        tvCheck2 = findViewById(R.id.Check2)
        tvCheck3 = findViewById(R.id.Check3)
        tvAnswer = findViewById(R.id.Answer)
        tvAnswer.text = wordToGuess
        tvAnswer.visibility = TextView.GONE

        btnGuess.setOnClickListener {
            if (guessCount >= 3) {
                Toast.makeText(this, "No guesses left!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val guess = input.text.toString().trim().uppercase()

            if (guess.length < 4) {
                Toast.makeText(this, "Enter exactly 4 letters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val check = checkGuess(guess)

            when (guessCount) {
                0 -> { tvGuess1.text = guess; tvCheck1.text = check }
                1 -> { tvGuess2.text = guess; tvCheck2.text = check }
                2 -> { tvGuess3.text = guess; tvCheck3.text = check }
            }

            guessCount++

            input.setText("")
            input.requestFocus()

            if (guessCount >= 3) {
                tvAnswer.visibility = TextView.VISIBLE
                btnGuess.isEnabled = false
                Toast.makeText(this, "Game over!", Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun checkGuess(guess: String): SpannableStringBuilder {
        val result = SpannableStringBuilder("XXXX")  // placeholder length 4

        for (i in 0..3) {
            val ch: Char
            val color: Int

            if (guess[i] == wordToGuess[i]) {
                ch = 'O'
                color = Color.GREEN
            } else if (guess[i] in wordToGuess) {
                ch = '+'
                color = Color.YELLOW
            } else {
                ch = 'X'
                color = Color.RED
            }

            result.replace(i, i + 1, ch.toString())

            result.setSpan(
                ForegroundColorSpan(color),
                i, i + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        return result
    }

}